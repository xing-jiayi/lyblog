package top.crushtj.blog.admin.services.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.crushtj.blog.admin.model.vo.article.ArticleDetailVo;
import top.crushtj.blog.admin.model.vo.article.ArticleUpdateVo;
import top.crushtj.blog.admin.model.vo.article.PublishArticleReqVO;
import top.crushtj.blog.admin.services.AdminArticleService;
import top.crushtj.blog.common.domain.ArticleService;
import top.crushtj.blog.common.domain.dos.ArticleDo;
import top.crushtj.blog.common.domain.dos.vo.article.ArticleListVo;
import top.crushtj.blog.common.domain.dos.vo.article.ArticleSearchVo;
import top.crushtj.blog.common.domain.dos.vo.category.CategoryVo;
import top.crushtj.blog.common.domain.mappers.*;
import top.crushtj.blog.common.enums.ResponseCodeEnum;
import top.crushtj.blog.common.exception.BizException;
import top.crushtj.blog.common.utils.IdGenerator;
import top.crushtj.blog.common.utils.PageResponse;
import top.crushtj.blog.common.utils.Response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/19 00:50
 * @description 文章Service实现类
 */

@Slf4j
@Service
public class AdminArticleServiceImpl implements AdminArticleService {
    private final ArticleMapper articleMapper;

    private final ArticleCategoryRelMapper articleCategoryRelMapper;

    private final ArticleTagRelMapper articleTagRelMapper;

    private final TagMapper tagMapper;

    private final ArticleService articleService;

    public AdminArticleServiceImpl(ArticleMapper articleMapper, ArticleCategoryRelMapper articleCategoryRelMapper,
        ArticleContentMapper articleContentMapper, ArticleTagRelMapper articleTagRelMapper,
        CategoryMapper categoryMapper, TagMapper tagMapper, ArticleService articleService) {
        this.articleMapper = articleMapper;
        this.articleCategoryRelMapper = articleCategoryRelMapper;
        this.articleTagRelMapper = articleTagRelMapper;
        this.tagMapper = tagMapper;
        this.articleService = articleService;
    }

    /**
     * 发布文章
     *
     * @param reqVO 发布文章请求VO
     * @return 发布文章响应VO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<PublishArticleReqVO> publishArticle(PublishArticleReqVO reqVO) {

        IdGenerator idGenerator = IdGenerator.getInstance();

        // 文章
        ArticleDo articleDo = ArticleDo.builder()
            .articleId(idGenerator.nextId())
            .title(reqVO.getTitle())
            .cover(reqVO.getCover())
            .summary(reqVO.getSummary())
            .createTime(LocalDateTime.now())
            .updateTime(LocalDateTime.now())
            .build();
        articleService.addArticle(articleDo);
        articleService.relContent(articleDo.getArticleId(), reqVO.getContent());
        articleService.relCategories(articleDo.getArticleId(), reqVO.getCategoryId());
        articleService.relTags(articleDo.getArticleId(), reqVO.getCategoryId());

        return Response.success();
    }

    /**
     * 删除文章
     *
     * @param articleId 文章ID
     * @return 删除文章响应
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> deleteArticle(String articleId) {
        if (articleId == null || articleId.isEmpty()) {
            log.error("文章ID不能为空");
            throw new BizException(ResponseCodeEnum.PARAM_NULL);
        }
        ArticleDo articleDo = articleMapper.selectById(articleId);
        if (Objects.isNull(articleDo)) {
            log.error("文章不存在，文章ID：{}", articleId);
            throw new BizException(ResponseCodeEnum.ARTICLE_IS_NOT_EXISTED);
        }
        // 逻辑删除文章
        articleDo.setIsDeleted(1);
        articleMapper.updateById(articleDo);
        articleService.logicDelArticleContent(articleId);
        articleService.logicDelArticleCategory(articleId);
        articleService.logicDelArticleTag(articleId);

        return Response.success("文章删除成功");
    }

    /**
     * 获取文章分页列表
     *
     * @param searchVo 文章搜索VO
     * @return 文章分页列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageResponse<ArticleListVo> getArticlePage(ArticleSearchVo searchVo) {
        long current = searchVo.getCurrent();
        long size = searchVo.getSize();
        Page<ArticleListVo> page = new Page<>(current, size);
        page = articleMapper.getArticlePage(page, searchVo);
        articleService.joinCategory(page.getRecords());
        articleService.joinTag(page.getRecords());
        return PageResponse.success(page, page.getRecords());
    }

    /**
     * 获取文章详情
     *
     * @param articleId 文章ID
     * @return 文章详情
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<ArticleDetailVo> getArticleDetail(String articleId) {
        ArticleDo article = articleMapper.selectById(articleId);
        if (Objects.isNull(article)) {
            log.error("文章不存在，文章ID：{}", articleId);
            throw new BizException(ResponseCodeEnum.ARTICLE_IS_NOT_EXISTED);
        }
        ArticleDetailVo articleDetail = new ArticleDetailVo();
        BeanUtils.copyProperties(article, articleDetail);
        articleService.joinCategory(articleDetail);
        articleService.joinTag(articleDetail);
        articleService.joinContent(articleDetail);
        return Response.success(articleDetail);
    }

    /**
     * 更新文章
     *
     * @param reqVO 文章更新请求VO
     * @return 文章详情响应VO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> updateArticle(ArticleUpdateVo reqVO) {
        IdGenerator idGenerator = IdGenerator.getInstance();
        if (Objects.isNull(reqVO)) {
            log.error("请求参数不能为空");
            throw new BizException(ResponseCodeEnum.PARAM_NULL);
        }
        if (reqVO.getArticleId() == null) {
            log.error("文章ID不能为空");
            throw new BizException(ResponseCodeEnum.PARAM_NULL);
        }
        //更新文章信息
        ArticleDo article = new  ArticleDo();
        BeanUtils.copyProperties(reqVO, article);
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.updateById(article);
        //更新文章内容
        articleService.updateContent(String.valueOf(reqVO.getArticleId()), reqVO.getContent());
        // 逻辑删除文章分类关联
        articleService.logicDelArticleCategory(String.valueOf(reqVO.getArticleId()));
        // 逻辑删除文章标签关联
        articleService.logicDelArticleTag(String.valueOf(reqVO.getArticleId()));
        // 关联新的分类
        List<String> categoryIds = new ArrayList<>();
        for (CategoryVo category : reqVO.getCategories()) {
            categoryIds.add(String.valueOf(category.getCategoryId()));
        }
        articleService.relCategories(reqVO.getArticleId(), categoryIds);
        // 关联新的标签
        articleService.relTags(reqVO.getArticleId(), reqVO.getTagNames());
        return Response.success("文章更新成功");
    }
}
