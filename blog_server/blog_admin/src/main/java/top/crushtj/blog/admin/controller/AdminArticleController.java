package top.crushtj.blog.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.crushtj.blog.admin.model.vo.article.ArticleDetailVo;
import top.crushtj.blog.admin.model.vo.article.ArticleUpdateVo;
import top.crushtj.blog.common.domain.dos.vo.article.ArticleListVo;
import top.crushtj.blog.common.domain.dos.vo.article.ArticleSearchVo;
import top.crushtj.blog.admin.model.vo.article.PublishArticleReqVO;
import top.crushtj.blog.admin.services.impl.AdminArticleServiceImpl;
import top.crushtj.blog.common.aspect.ApiOperationLog;
import top.crushtj.blog.common.utils.PageResponse;
import top.crushtj.blog.common.utils.Response;

import java.util.Map;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/19 01:29
 * @description 文章Controller
 */

@RestController
@RequestMapping("/admin/article")
@Api(tags = "Admin 文章模块")
public class AdminArticleController {

    private final AdminArticleServiceImpl articleService;

    public AdminArticleController(AdminArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/publish")
    @ApiOperation(value = "发布文章")
    @ApiOperationLog(description = "发布文章")
    public Response<PublishArticleReqVO> publishArticle(@RequestBody @Validated PublishArticleReqVO reqVO) {
        return articleService.publishArticle(reqVO);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除文章")
    @ApiOperationLog(description = "删除文章")
    public Response<String> deleteArticle(@RequestBody Map<String, String> param) {
        String articleId = param.get("articleId");
        return articleService.deleteArticle(articleId);
    }

    @PostMapping("/page")
    @ApiOperation(value = "文章分页列表")
    @ApiOperationLog(description = "文章分页列表")
    public PageResponse<ArticleListVo> getArticlePage(@RequestBody ArticleSearchVo searchVo) {
        return articleService.getArticlePage(searchVo);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "文章详情")
    @ApiOperationLog(description = "文章详情")
    public Response<ArticleDetailVo> getArticleDetail(@RequestBody Map<String,String> param) {
        String articleId = param.get("articleId");
        return articleService.getArticleDetail(articleId);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新文章")
    @ApiOperationLog(description = "更新文章")
    public Response<String> updateArticle(@RequestBody @Validated ArticleUpdateVo reqVO) {
        return articleService.updateArticle(reqVO);
    }
}
