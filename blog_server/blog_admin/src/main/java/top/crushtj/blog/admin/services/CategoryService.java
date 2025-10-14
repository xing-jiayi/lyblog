package top.crushtj.blog.admin.services;

import top.crushtj.blog.common.domain.dos.vo.category.CategorySearchVo;
import top.crushtj.blog.common.domain.dos.CategoryDo;
import top.crushtj.blog.common.utils.PageResponse;
import top.crushtj.blog.common.utils.Response;

import java.util.List;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/14 15:01
 * @description 分类Service
 */

public interface CategoryService {

    Response<CategoryDo> selectByName(String name);

    Response<CategoryDo> addCategory(CategoryDo categoryDo);

    PageResponse<CategoryDo> queryCategoryPage(CategorySearchVo categorySearchVo);

    Response<String> deleteCategory(String categoryId);

    Response<List<CategoryDo>> getAllCategory();
}
