package top.crushtj.blog.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.crushtj.blog.admin.services.TagService;
import top.crushtj.blog.common.aspect.ApiOperationLog;
import top.crushtj.blog.common.domain.dos.TagDo;
import top.crushtj.blog.common.domain.dos.vo.tag.TagSearchVo;
import top.crushtj.blog.common.utils.PageResponse;
import top.crushtj.blog.common.utils.Response;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/15 18:15
 * @description 标签controller
 */

@Slf4j
@RestController
@RequestMapping("/admin/tag")
@Api(tags = "Admin 标签模块")
public class TagController {

    @Resource
    private TagService tagService;

    @PostMapping("/add")
    @ApiOperation(value = "新增标签")
    @ApiOperationLog(description = "新增标签")
    public Response<TagDo> addTag(@RequestBody @Validated TagDo tagDo) {
        return tagService.addTag(tagDo);
    }

    @PostMapping("/delete")
    @ApiOperationLog(description = "删除标签")
    @ApiOperation(value = "删除标签")
    public Response<String> deleteTag(@RequestBody Map<String, String> param) {
        String tagId = param.get("id");
        return tagService.deleteTag(tagId);
    }

    @PostMapping("/getByName")
    @ApiOperationLog(description = "通过名称查询标签")
    @ApiOperation(value = "通过名称查询标签")
    public Response<TagDo> queryTagByName(@RequestBody Map<String, String> param) {
        String name = param.get("name");
        return tagService.queryTagByName(name);
    }

    @PostMapping("/list")
    @ApiOperation(value = "获取所有标签列表")
    @ApiOperationLog(description = "获取所有标签列表")
    public Response<List<TagDo>> queryTagList() {
        return tagService.queryAllTags();
    }

    @PostMapping("/page")
    @ApiOperation(value = "分页查询标签")
    @ApiOperationLog(description = "分页查询标签")
    public PageResponse<TagDo> queryTagPage(@RequestBody @Validated TagSearchVo searchVo) {
        return tagService.queryTagPage(searchVo);
    }

}
