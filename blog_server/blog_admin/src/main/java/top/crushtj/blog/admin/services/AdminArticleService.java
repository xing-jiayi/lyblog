package top.crushtj.blog.admin.services;

import top.crushtj.blog.admin.model.vo.article.PublishArticleReqVO;
import top.crushtj.blog.common.utils.Response;

/**
 * @author 刑加一
 * @url www.crushtj.top
 * @date 2025/10/19
 * @description 文章Service
 */

public interface AdminArticleService {

    Response<PublishArticleReqVO> publishArticle(PublishArticleReqVO reqVO);
}
