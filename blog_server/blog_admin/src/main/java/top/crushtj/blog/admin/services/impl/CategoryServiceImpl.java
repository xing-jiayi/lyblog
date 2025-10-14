package top.crushtj.blog.admin.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.crushtj.blog.admin.services.CategoryService;
import top.crushtj.blog.common.domain.dos.CategoryDo;
import top.crushtj.blog.common.domain.mappers.CategoryMapper;
import top.crushtj.blog.common.enums.ResponseCodeEnum;
import top.crushtj.blog.common.utils.IdGenerator;
import top.crushtj.blog.common.utils.Response;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/14 15:01
 * @description 分类Service实现类
 */

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Response<CategoryDo> selectByName(String name) {
        CategoryDo categoryDo = categoryMapper.selectByName(name);
        return categoryDo == null ? Response.failure("分类" + name + "不存在") : Response.success(categoryDo);
    }

    @Override
    public Response<CategoryDo> addCategory(CategoryDo categoryDo) {
        CategoryDo categoryDo1 = categoryMapper.selectByName(categoryDo.getName());
        if (Objects.nonNull(categoryDo1)) {
            log.warn("分类{}已存在", categoryDo.getName());
            return Response.failure(ResponseCodeEnum.CATEGORY_NAME_IS_EXISTED);
        }
        Long id = IdGenerator.getInstance()
            .nextId();
        categoryDo.setCategoryId(id);
        categoryDo.setCreateTime(LocalDateTime.now());
        categoryDo.setUpdateTime(LocalDateTime.now());
        categoryMapper.insert(categoryDo);
        return Response.success(categoryDo);
    }
}
