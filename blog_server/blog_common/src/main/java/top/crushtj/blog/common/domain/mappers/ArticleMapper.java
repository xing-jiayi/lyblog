package top.crushtj.blog.common.domain.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.crushtj.blog.common.domain.dos.ArticleDo;
import top.crushtj.blog.common.domain.dos.vo.article.ArticleListVo;
import top.crushtj.blog.common.domain.dos.vo.article.ArticleSearchVo;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/19 00:41
 * @description 文章表(t_article)表数据库访问层
 **/

public interface ArticleMapper extends BaseMapper<ArticleDo> {
    Page<ArticleListVo> getArticlePage(@Param("page") Page<ArticleListVo> page,
        @Param("searchVo") ArticleSearchVo searchVo);
}

