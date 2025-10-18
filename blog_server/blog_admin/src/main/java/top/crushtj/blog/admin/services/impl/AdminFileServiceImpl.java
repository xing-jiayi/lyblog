package top.crushtj.blog.admin.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.crushtj.blog.admin.model.vo.file.UploadFileRspVO;
import top.crushtj.blog.admin.services.AdminFileService;
import top.crushtj.blog.admin.utils.MinioUtil;
import top.crushtj.blog.common.enums.ResponseCodeEnum;
import top.crushtj.blog.common.exception.BizException;
import top.crushtj.blog.common.utils.Response;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/18 20:50
 * @description 文件service实现类
 */

@Slf4j
@Service
public class AdminFileServiceImpl implements AdminFileService {

    private final MinioUtil minioUtil;

    public AdminFileServiceImpl(MinioUtil minioUtil) {
        this.minioUtil = minioUtil;
    }

    @Override
    public Response<UploadFileRspVO> uploadFile(MultipartFile file) {

        try {
            String url = minioUtil.uploadFile(file);
            return Response.success(UploadFileRspVO.builder()
                .url(url)
                .build());
        } catch (Exception e) {
            log.error("文件上传失败：", e);
            throw new BizException(ResponseCodeEnum.FILE_UPLOAD_ERROR);
        }

    }
}
