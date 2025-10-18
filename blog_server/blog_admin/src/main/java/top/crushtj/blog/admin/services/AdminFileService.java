package top.crushtj.blog.admin.services;

import org.springframework.web.multipart.MultipartFile;
import top.crushtj.blog.admin.model.vo.file.UploadFileRspVO;
import top.crushtj.blog.common.utils.Response;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/18 20:49
 * @description 文件service
 */

public interface AdminFileService {

    Response<UploadFileRspVO> uploadFile(MultipartFile file);
}
