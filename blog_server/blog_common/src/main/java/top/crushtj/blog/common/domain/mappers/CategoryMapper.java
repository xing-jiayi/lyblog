package top.crushtj.blog.common.domain.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.crushtj.blog.common.domain.dos.CategoryDo;
import top.crushtj.blog.common.domain.dos.vo.category.CategorySearchVo;

import java.util.List;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/14 14:56
 * @description 文章分类表(t_category)表数据库访问层
 **/

public interface CategoryMapper extends BaseMapper<CategoryDo> {

    CategoryDo selectByName(@Param("name") String name);

    Page<CategoryDo> queryCategoryPage(@Param("page") Page<CategoryDo> page,
        @Param("searchVo") CategorySearchVo searchVo);

    List<CategoryDo> getAllCategory();

    CategoryDo selectNormalById(String categoryId);
}

