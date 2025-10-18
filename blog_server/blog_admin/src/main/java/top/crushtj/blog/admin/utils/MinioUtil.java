package top.crushtj.blog.admin.utils;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.crushtj.blog.admin.config.MinioProperties;

import java.util.Objects;
import java.util.UUID;

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
        String key = UUID.randomUUID()
            .toString()
            .replace("-", "");
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = String.format("%s_%s", key, suffix);
        minioClient.putObject(PutObjectArgs.builder()
            .bucket(minioProperties.getBucketName())
            .object(filename)
            .stream(file.getInputStream(), file.getSize(), -1)
            .contentType(contentType)
            .build());
        return String.format("%s/%s/%s", minioProperties.getEndpoint(), minioProperties.getBucketName(), filename);
    }
}
