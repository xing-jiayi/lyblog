package top.crushtj.blog.admin.services;

import top.crushtj.blog.common.domain.dos.TagDo;
import top.crushtj.blog.common.domain.dos.vo.tag.TagSearchVo;
import top.crushtj.blog.common.utils.PageResponse;
import top.crushtj.blog.common.utils.Response;

import java.util.List;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/15 17:57
 * @description 标签service
 */

public interface TagService {

    Response<TagDo> addTag(TagDo tagDo);

    Response<String> deleteTag(String tagId);

    Response<TagDo> queryTagByName(String name);

    Response<List<TagDo>> queryAllTags();

    PageResponse<TagDo> queryTagPage(TagSearchVo tagSearchVo);
}
