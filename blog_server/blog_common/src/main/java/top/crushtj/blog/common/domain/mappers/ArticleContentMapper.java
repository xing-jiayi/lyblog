package top.crushtj.blog.common.domain.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.crushtj.blog.common.domain.dos.ArticleContentDo;

import java.io.Serializable;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/19 00:41
 * @description 文章内容表(t_article_content)表数据库访问层
 **/

public interface ArticleContentMapper extends BaseMapper<ArticleContentDo> {

    ArticleContentDo selectByArticleId(Serializable articleId);
}

