package top.crushtj.blog.admin.utils;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.StatObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.crushtj.blog.admin.config.MinioProperties;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.util.Objects;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/18 20:22
 * @description minio工具类
 */

@Slf4j
@Component
public class MinioUtil {

    private final MinioProperties minioProperties;

    private final MinioClient minioClient;

    @Autowired
    public MinioUtil(MinioProperties minioProperties, MinioClient minioClient) {
        this.minioProperties = minioProperties;
        this.minioClient = minioClient;
    }

    public String uploadFile(MultipartFile file) throws Exception {
        // 判断上传文件是否为空
        if (Objects.isNull(file) || file.isEmpty()) {
            log.error("上传文件为空");
            throw new RuntimeException("上传文件为空");
        }
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            log.error("文件名为空！");
            throw new RuntimeException("文件名为空！");
        }
        String contentType = file.getContentType();
        byte[] fileBytes = file.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(fileBytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        String hash = sb.toString();
        int idx = originalFilename.lastIndexOf(".");
        String suffix = idx == -1 ? "" : originalFilename.substring(idx);
        String filename = hash + suffix;

        // 使用UUID生成唯一文件名，现已改为使用文件内容的SHA-256哈希值，避免重复上传相同文件
        // String key = UUID.randomUUID()
        //     .toString()
        //     .replace("-", "");
        // String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // String filename = String.format("%s_%s", key, suffix);

        // 检查文件是否已存在，若不存在则上传
        try {
            minioClient.statObject(StatObjectArgs.builder()
                .bucket(minioProperties.getBucketName())
                .object(filename)
                .build());
            log.info("文件已存在，跳过上传，文件名：{}", filename);
            return String.format("%s/%s/%s", minioProperties.getEndpoint(), minioProperties.getBucketName(), filename);
        } catch (Exception e) {
            minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioProperties.getBucketName())
                .object(filename)
                .stream(new ByteArrayInputStream(fileBytes), fileBytes.length, -1)
                .contentType(contentType)
                .build());
            return String.format("%s/%s/%s", minioProperties.getEndpoint(), minioProperties.getBucketName(), filename);
        }
    }
}
