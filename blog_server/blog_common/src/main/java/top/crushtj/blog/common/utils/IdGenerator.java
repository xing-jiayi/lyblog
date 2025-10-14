package top.crushtj.blog.common.utils;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/14 11:40
 * @description 基于雪花算法的ID生成器
 */

public class IdGenerator {
    // 开始时间戳 (2020-01-01 00:00:00)
    private final long startTime = 1577836800000L;

    // 机器ID所占的位数
    private final long workerIdBits = 5L;
    // 数据中心ID所占的位数
    private final long dataCenterIdBits = 5L;
    // 支持的最大机器ID，结果是31 (0b11111)
    private final long maxWorkerId = ~(-1L << workerIdBits);
    // 支持的最大数据中心ID，结果是31 (0b11111)
    private final long maxDataCenterId = ~(-1L << dataCenterIdBits);

    // 序列号所占的位数
    private final long sequenceBits = 12L;
    // 机器ID向左移12位
    private final long workerIdShift = sequenceBits;
    // 数据中心ID向左移17位(12+5)
    private final long dataCenterIdShift = sequenceBits + workerIdBits;
    // 时间戳向左移22位(5+5+12)
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    // 生成序列的掩码，这里为4095 (0b111111111111)
    private final long sequenceMask = ~(-1L << sequenceBits);

    private final long workerId;      // 机器ID
    private final long dataCenterId;  // 数据中心ID
    private long sequence = 0L;       // 序列号
    private long lastTimestamp = -1L; // 上次生成ID的时间戳

    /**
     * 构造函数
     *
     * @param workerId     机器ID (0-31)
     * @param dataCenterId 数据中心ID (0-31)
     */
    public IdGenerator(long workerId, long dataCenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(
                String.format("dataCenter Id can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    public static IdGenerator getInstance() {
        return getInstance(1L, 1L);
    }

    public static IdGenerator getInstance(long workerId, long dataCenterId) {
        return new IdGenerator(workerId, dataCenterId);
    }

    /**
     * 生成下一个ID
     *
     * @return 唯一ID
     */
    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过，这时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        // 如果是同一时间生成的，则进行序列号自增
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 序列号溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒，获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳改变，序列号重置
            sequence = 0L;
        }

        // 上次生成ID的时间戳
        lastTimestamp = timestamp;

        // 移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - startTime) << timestampLeftShift) | (dataCenterId << dataCenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间戳
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

}

