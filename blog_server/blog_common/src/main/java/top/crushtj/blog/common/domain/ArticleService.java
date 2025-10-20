package top.crushtj.blog.common.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.crushtj.blog.common.domain.dos.*;
import top.crushtj.blog.common.domain.dos.vo.article.ArticleVo;
import top.crushtj.blog.common.domain.dos.vo.category.CategoryVo;
import top.crushtj.blog.common.domain.dos.vo.tag.TagVo;
import top.crushtj.blog.common.domain.mappers.*;
import top.crushtj.blog.common.enums.ResponseCodeEnum;
import top.crushtj.blog.common.exception.BizException;
import top.crushtj.blog.common.utils.IdGenerator;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/19 14:00
 * @description 文章service
 */

@Service
@Slf4j
public class ArticleService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private TagMapper tagMapper;

    @Resource
    private ArticleContentMapper contentMapper;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleCategoryRelMapper categoryRelMapper;

    @Resource
    private ArticleTagRelMapper tagRelMapper;

    private IdGenerator idGenerator = IdGenerator.getInstance();

    /**
     * 新增文章
     *
     * @param article 文章DO
     */
    public void addArticle(ArticleDo article) {
        try {
            articleMapper.insert(article);
        } catch (Exception e) {
            log.error("新增文章失败，文章信息：{}", article, e);
            throw new RuntimeException("新增文章失败");
        }
    }

    /**
     * 关联文章内容
     *
     * @param articleId 文章ID
     * @param content   文章内容
     */
    public void relContent(Long articleId, String content) {
        try {
            // 文章内容
            ArticleContentDo articleContentDo = ArticleContentDo.builder()
                .articleId(articleId)
                .content(content)
                .contentId(idGenerator.nextId())
                .build();
            contentMapper.insert(articleContentDo);
        } catch (Exception e) {
            log.error("新增文章内容失败，文章信息：{}", articleId, e);
            throw new RuntimeException("新增文章内容失败");
        }
    }

    public void updateContent(String articleId, String content) {
        try {
            // 文章内容
            ArticleContentDo articleContentDo = contentMapper.selectByArticleId(articleId);
            articleContentDo.setContent(content);
            contentMapper.updateById(articleContentDo);
        } catch (Exception e) {
            log.error("更新文章内容失败，文章信息：{}", articleId, e);
            throw new RuntimeException("更新文章内容失败");
        }
    }

    /**
     * 关联文章分类
     *
     * @param articleId   文章ID
     * @param categoryIds 分类ID列表
     */
    public void relCategories(Long articleId, List<String> categoryIds) {
        try {
            // 文章分类关联
            for (String id : categoryIds) {
                CategoryDo category = categoryMapper.selectById(id);
                if (Objects.isNull(category)) {
                    log.error("文章分类不存在，分类ID：{}", id);
                    throw new BizException(ResponseCodeEnum.CATEGORY_IS_NOT_EXISTED);
                }
            }
            for (String categoryId : categoryIds) {
                ArticleCategoryRelDo articleCategoryRelDo = ArticleCategoryRelDo.builder()
                    .id(idGenerator.nextId())
                    .articleId(articleId)
                    .categoryId(Long.valueOf(categoryId))
                    .build();
                categoryRelMapper.insert(articleCategoryRelDo);
            }
        } catch (Exception e) {
            log.error("关联文章分类失败，文章ID：{}，分类ID列表：{}", articleId, categoryIds, e);
            throw new RuntimeException("关联文章分类失败");
        }
    }

    /**
     * 关联文章标签
     *
     * @param articleId 文章ID
     * @param tagNames  标签名称列表
     */
    public void relTags(Long articleId, List<String> tagNames) {
        try {
            // 文章标签关联
            List<Long> tagIds = new ArrayList<>();
            for (String tagName : tagNames) {
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
                    .articleId(articleId)
                    .tagId(tagId)
                    .build();
                tagRelMapper.insert(articleTagRelDo);
            }
        } catch (Exception e) {
            log.error("关联文章标签失败，文章ID：{}，标签ID列表：{}", articleId, tagNames, e);
            throw new RuntimeException("关联文章标签失败");
        }
    }

    /**
     * 逻辑删除文章内容
     *
     * @param articleId 文章ID
     */
    public void logicDelArticleContent(String articleId) {
        // 逻辑删除文章内容
        try {
            ArticleContentDo articleContentDo = contentMapper.selectByArticleId(articleId);
            articleContentDo.setIsDeleted(1);
            contentMapper.updateById(articleContentDo);
        } catch (Exception e) {
            log.error("删除文章内容失败，文章ID：{}", articleId, e);
            throw new RuntimeException("删除文章内容失败");
        }
    }

    /**
     * 逻辑删除文章标签关联
     *
     * @param articleId 文章ID
     */
    public void logicDelArticleCategory(String articleId) {
        // 逻辑删除文章分类关联
        try {
            // 逻辑删除文章分类关联
            List<ArticleCategoryRelDo> categories = categoryRelMapper.selectByArticleId(articleId);
            for (ArticleCategoryRelDo categoryRelDo : categories) {
                categoryRelDo.setIsDeleted(1);
                categoryRelMapper.updateById(categoryRelDo);
            }
        } catch (Exception e) {
            log.error("删除文章分类关联失败，文章ID：{}", articleId, e);
            throw new RuntimeException("删除文章分类关联失败");
        }
    }

    /**
     * 逻辑删除文章标签关联
     *
     * @param articleId 文章ID
     */
    public void logicDelArticleTag(String articleId) {
        // 逻辑删除文章标签关联
        try {
            // 逻辑删除文章标签关联
            List<ArticleTagRelDo> tags = tagRelMapper.selectByArticleId(articleId);
            for (ArticleTagRelDo tagRelDo : tags) {
                tagRelDo.setIsDeleted(1);
                tagRelMapper.updateById(tagRelDo);
            }
        } catch (Exception e) {
            log.error("删除文章标签关联失败，文章ID：{}", articleId, e);
            throw new RuntimeException("删除文章标签关联失败");
        }
    }

    /**
     * 为文章Vo加入分类信息
     *
     * @param articleVo 文章Vo
     */
    public void joinCategory(ArticleVo articleVo) {
        List<CategoryVo> categoryVos = categoryMapper.selectByArticleId(articleVo.getArticleId());
        articleVo.setCategories(categoryVos);
    }

    /**
     * 为文章Vo列表加入分类信息
     *
     * @param articleVoList 文章Vo列表
     */
    public void joinCategory(List<? extends ArticleVo> articleVoList) {
        for (ArticleVo articleVo : articleVoList) {
            joinCategory(articleVo);
        }
    }

    /**
     * 为文章Vo加入标签信息
     *
     * @param articleVo 文章Vo
     */
    public void joinTag(ArticleVo articleVo) {
        List<TagVo> tagVos = tagMapper.selectByArticleId(articleVo.getArticleId());
        articleVo.setTags(tagVos);
    }

    /**
     * 为文章Vo列表加入标签信息
     *
     * @param articleVos 文章Vo列表
     */
    public void joinTag(List<? extends ArticleVo> articleVos) {
        for (ArticleVo articleVo : articleVos) {
            joinTag(articleVo);
        }
    }

    /**
     * 为文章Vo加入正文内容
     *
     * @param articleVo 文章Vo
     */
    public void joinContent(ArticleVo articleVo) {
        String content = contentMapper.getContentByArticleId(articleVo.getArticleId());
        articleVo.setContent(content);
    }

    /**
     * 为文章Vo列表加入正文内容
     *
     * @param articleVos 文章Vo列表
     */
    public void joinContent(List<? extends ArticleVo> articleVos) {
        for (ArticleVo articleVo : articleVos) {
            joinContent(articleVo);
        }
    }
}
