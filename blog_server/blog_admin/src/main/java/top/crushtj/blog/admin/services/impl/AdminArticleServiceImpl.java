package top.crushtj.blog.admin.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.crushtj.blog.admin.model.vo.article.PublishArticleReqVO;
import top.crushtj.blog.admin.services.AdminArticleService;
import top.crushtj.blog.common.domain.dos.*;
import top.crushtj.blog.common.domain.mappers.*;
import top.crushtj.blog.common.enums.ResponseCodeEnum;
import top.crushtj.blog.common.exception.BizException;
import top.crushtj.blog.common.utils.IdGenerator;
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

    private final ArticleContentMapper articleContentMapper;

    private final ArticleTagRelMapper articleTagRelMapper;

    private final CategoryMapper categoryMapper;

    private final TagMapper tagMapper;

    public AdminArticleServiceImpl(ArticleMapper articleMapper, ArticleCategoryRelMapper articleCategoryRelMapper,
        ArticleContentMapper articleContentMapper, ArticleTagRelMapper articleTagRelMapper,
        CategoryMapper categoryMapper, TagMapper tagMapper) {
        this.articleMapper = articleMapper;
        this.articleCategoryRelMapper = articleCategoryRelMapper;
        this.articleContentMapper = articleContentMapper;
        this.articleTagRelMapper = articleTagRelMapper;
        this.categoryMapper = categoryMapper;
        this.tagMapper = tagMapper;
    }

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
        articleMapper.insert(articleDo);

        // 文章内容
        ArticleContentDo articleContentDo = ArticleContentDo.builder()
            .articleId(articleDo.getArticleId())
            .content(reqVO.getContent())
            .contentId(idGenerator.nextId())
            .build();
        articleContentMapper.insert(articleContentDo);

        // 文章分类关联
        for (String id : reqVO.getCategoryId()) {
            CategoryDo category = categoryMapper.selectById(id);
            if (Objects.isNull(category)) {
                log.error("文章分类不存在，分类ID：{}", id);
                throw new BizException(ResponseCodeEnum.CATEGORY_IS_NOT_EXISTED);
            }
        }
        // List<CategoryDo> categoryList = categoryMapper.selectBatchIds(reqVO.getCategoryId());
        // if (categoryList == null || categoryList.isEmpty()) {
        //     log.error("文章分类不存在，分类ID列表：{}", reqVO.getCategoryId());
        //     throw new BizException(ResponseCodeEnum.CATEGORY_IS_NOT_EXISTED);
        //     // return Response.failure(String.format("文章分类不存在，分类ID列表：%s", reqVO.getCategoryId()));
        // }

        for (String categoryId : reqVO.getCategoryId()) {
            ArticleCategoryRelDo articleCategoryRelDo = ArticleCategoryRelDo.builder()
                .id(idGenerator.nextId())
                .articleId(articleDo.getArticleId())
                .categoryId(Long.valueOf(categoryId))
                .build();
            articleCategoryRelMapper.insert(articleCategoryRelDo);
        }

        // 文章标签关联
        List<Long> tagIds = new ArrayList<>();
        for (String tagName : reqVO.getTagNames()) {
            TagDo tag = tagMapper.selectByName(tagName);
            if (Objects.isNull(tag)) {
                log.info("文章标签不存在，即将创建，标签名：{}", tagName);
                TagDo tagDo = TagDo.builder()
                    .tagId(idGenerator.nextId())
                    .tagName(tagName)
                    .createTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .isDeleted(0)
                    .build();
                tagMapper.insert(tagDo);
                tagIds.add(tagDo.getTagId());
            } else {
                tagIds.add(tag.getTagId());
            }
        }
        for (Long tagId : tagIds) {
            ArticleTagRelDo articleTagRelDo = ArticleTagRelDo.builder()
                .id(idGenerator.nextId())
                .articleId(articleDo.getArticleId())
                .tagId(tagId)
                .build();
            articleTagRelMapper.insert(articleTagRelDo);
        }
        return Response.success();
    }

    @Override
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

        // 逻辑删除文章内容
        ArticleContentDo articleContentDo = articleContentMapper.selectByArticleId(articleId);
        articleContentDo.setIsDeleted(1);
        articleContentMapper.updateById(articleContentDo);

        // 逻辑删除文章分类关联
        List<ArticleCategoryRelDo> categories = articleCategoryRelMapper.selectByArticleId(articleId);
        for (ArticleCategoryRelDo categoryRelDo : categories) {
            categoryRelDo.setIsDeleted(1);
            articleCategoryRelMapper.updateById(categoryRelDo);
        }

        // 逻辑删除文章标签关联
        List<ArticleTagRelDo> tags = articleTagRelMapper.selectByArticleId(articleId);
        for (ArticleTagRelDo tagRelDo : tags) {
            tagRelDo.setIsDeleted(1);
            articleTagRelMapper.updateById(tagRelDo);
        }
        return Response.success("文章删除成功");
    }
}
