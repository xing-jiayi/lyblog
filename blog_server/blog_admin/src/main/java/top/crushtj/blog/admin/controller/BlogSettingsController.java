package top.crushtj.blog.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.crushtj.blog.admin.model.vo.settings.SettingsUpdateVo;
import top.crushtj.blog.admin.services.AdminSettingsService;
import top.crushtj.blog.common.aspect.ApiOperationLog;
import top.crushtj.blog.common.domain.dos.BlogSettingsDo;
import top.crushtj.blog.common.utils.Response;

import java.util.Map;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/18 21:23
 * @description 博客设置controller
 */

@Slf4j
@RestController
@RequestMapping("/admin/blog/settings")
@Api(tags = "Admin 博客设置模块")
public class BlogSettingsController {

    private final AdminSettingsService settingsService;

    public BlogSettingsController(AdminSettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @PostMapping("/update")
    @ApiOperation(value = "博客基础信息修改")
    @ApiOperationLog(description = "博客基础信息修改")
    public Response<BlogSettingsDo> updateSettings(@RequestBody @Validated SettingsUpdateVo settingsUpdateVo) {
        return settingsService.updateSettings(settingsUpdateVo);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "获取博客详情")
    @ApiOperationLog(description = "获取博客详情")
    public Response<BlogSettingsDo> getSettingsDetail(@RequestBody Map<String, String> id) {
        String userId = id.get("userId");
        return settingsService.getDetail(userId);
    }
}
