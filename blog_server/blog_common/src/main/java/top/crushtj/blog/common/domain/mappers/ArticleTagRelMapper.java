package top.crushtj.blog.common.domain.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.crushtj.blog.common.domain.dos.ArticleTagRelDo;

import java.io.Serializable;
import java.util.List;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/19 00:42
 * @description 文章对应标签关联表(t_article_tag_rel)表数据库访问层
 **/

public interface ArticleTagRelMapper extends BaseMapper<ArticleTagRelDo> {

    List<ArticleTagRelDo> selectByArticleId(Serializable articleId);
}

