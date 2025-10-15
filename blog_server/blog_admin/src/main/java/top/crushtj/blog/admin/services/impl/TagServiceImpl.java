package top.crushtj.blog.admin.services.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import top.crushtj.blog.admin.services.TagService;
import top.crushtj.blog.common.domain.dos.TagDo;
import top.crushtj.blog.common.domain.dos.vo.tag.TagSearchVo;
import top.crushtj.blog.common.domain.mappers.TagMapper;
import top.crushtj.blog.common.enums.ResponseCodeEnum;
import top.crushtj.blog.common.utils.IdGenerator;
import top.crushtj.blog.common.utils.PageResponse;
import top.crushtj.blog.common.utils.Response;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/15 17:57
 * @description 标签service实现
 */

@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public Response<TagDo> addTag(TagDo tagDo) {
        TagDo existingTag = tagMapper.selectByName(tagDo.getTagName());
        if (Objects.nonNull(existingTag)) {
            return Response.failure("标签" + tagDo.getTagName() + "已存在");
        }
        tagDo.setCreateTime(LocalDateTime.now());
        tagDo.setUpdateTime(LocalDateTime.now());
        IdGenerator generator = IdGenerator.getInstance();
        tagDo.setTagId(generator.nextId());
        tagMapper.insert(tagDo);
        return Response.success(tagDo);
    }

    @Override
    public Response<String> deleteTag(String tagId) {
        TagDo tagDo = tagMapper.selectById(tagId);
        if (Objects.isNull(tagDo)) {
            return Response.failure(ResponseCodeEnum.TAG_IS_NOT_EXIST);
        }
        tagDo.setUpdateTime(LocalDateTime.now());
        tagDo.setIsDeleted(1);
        tagMapper.updateById(tagDo);
        return Response.success("删除标签" + tagDo.getTagName() + "成功");
    }

    @Override
    public Response<TagDo> queryTagByName(String name) {
        TagDo tagDo = tagMapper.selectByName(name);
        return Objects.isNull(tagDo) ? Response.failure("标签" + name + "不存在") : Response.success(tagDo);
    }

    @Override
    public Response<List<TagDo>> queryAllTags() {
        List<TagDo> tagDos = tagMapper.queryAllTags();
        return Response.success(tagDos);
    }

    @Override
    public PageResponse<TagDo> queryTagPage(TagSearchVo tagSearchVo) {
        long current = tagSearchVo.getCurrent();
        long size = tagSearchVo.getSize();
        Page<TagDo> page = new Page<>(current, size);
        page = tagMapper.queryTagPage(page, tagSearchVo);

        return PageResponse.success(page, page.getRecords());
    }
}
