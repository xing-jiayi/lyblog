package top.crushtj.blog.common.domain.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.crushtj.blog.common.domain.dos.TagDo;
import top.crushtj.blog.common.domain.dos.vo.tag.TagSearchVo;
import top.crushtj.blog.common.domain.dos.vo.tag.TagVo;

import java.io.Serializable;
import java.util.List;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/15 14:27
 * @description 文章标签表(t_tag)表数据库访问层
 **/

public interface TagMapper extends BaseMapper<TagDo> {

    TagDo selectByName(String tagName);

    List<TagDo> queryAllTags();

    Page<TagDo> queryTagPage(Page<TagDo> page, TagSearchVo searchVo);

    List<TagVo> selectByArticleId(Serializable articleId);
}

