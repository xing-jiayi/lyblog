package top.crushtj.blog.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/14 15:33
 * @description 基础分页参数
 */

@Data
public class BasePageQuery implements Serializable {
    private static final long serialVersionUID = 1575670807100378427L;
    /**
     * 当前页码, 默认第一页
     */
    private Long current = 1L;
    /**
     * 每页展示的数据数量，默认每页展示 10 条数据
     */
    private Long size = 10L;
}
