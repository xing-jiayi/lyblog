package top.crushtj.blog.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.crushtj.blog.admin.model.vo.file.UploadFileRspVO;
import top.crushtj.blog.admin.services.AdminFileService;
import top.crushtj.blog.common.aspect.ApiOperationLog;
import top.crushtj.blog.common.utils.Response;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/18 20:57
 * @description 文件controller
 */

@RestController
@RequestMapping("/admin/file")
@Api(tags = "Admin 文件模块")
public class AdminFileController {

    private final AdminFileService fileService;

    @Autowired
    public AdminFileController(AdminFileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传")
    @ApiOperationLog(description = "文件上传")
    public Response<UploadFileRspVO> uploadFile(@RequestParam MultipartFile file) {
        return fileService.uploadFile(file);
    }
}
