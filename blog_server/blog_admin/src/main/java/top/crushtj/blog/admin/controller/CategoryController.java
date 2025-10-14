package top.crushtj.blog.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.crushtj.blog.admin.services.CategoryService;
import top.crushtj.blog.common.aspect.ApiOperationLog;
import top.crushtj.blog.common.domain.dos.CategoryDo;
import top.crushtj.blog.common.domain.dos.vo.category.CategorySearchVo;
import top.crushtj.blog.common.utils.PageResponse;
import top.crushtj.blog.common.utils.Response;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/14 15:05
 * @description 分类controller
 */

@Slf4j
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin 分类模块")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @PostMapping("/category/queryByName")
    @ApiOperation(value = "通过名称查询分类")
    @ApiOperationLog(description = "通过名称查询分类")
    public Response<CategoryDo> selectByName(String name) {
        return categoryService.selectByName(name);
    }

    @PostMapping("/category/add")
    @ApiOperation(value = "新增分类")
    @ApiOperationLog(description = "新增分类")
    public Response<CategoryDo> addCategory(@RequestBody @Validated CategoryDo categoryDo) {
        return categoryService.addCategory(categoryDo);
    }

    @PostMapping("/category/page")
    @ApiOperation(value = "分页查询分类")
    @ApiOperationLog(description = "分页查询分类")
    public PageResponse<CategoryDo> queryCategoryPage(@RequestBody @Validated CategorySearchVo searchVo) {
        return categoryService.queryCategoryPage(searchVo);
    }

    @PostMapping("/category/delete")
    @ApiOperation(value = "删除分类")
    @ApiOperationLog(description = "删除分类")
    public Response<String> deleteCategory(String categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    @PostMapping("/category/list")
    @ApiOperation(value = "获取所有分类")
    @ApiOperationLog(description = "获取所有分类")
    public Response<List<CategoryDo>> queryCategoryList() {
        return categoryService.getAllCategory();
    }

}
