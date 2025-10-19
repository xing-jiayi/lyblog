package top.crushtj.blog.common.domain.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.crushtj.blog.common.domain.dos.ArticleCategoryRelDo;

import java.io.Serializable;
import java.util.List;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/19 00:41
 * @description 文章所属分类关联表(t_article_category_rel)表数据库访问层
 **/

public interface ArticleCategoryRelMapper extends BaseMapper<ArticleCategoryRelDo> {

    List<ArticleCategoryRelDo> selectByArticleId(Serializable articleId);
}

