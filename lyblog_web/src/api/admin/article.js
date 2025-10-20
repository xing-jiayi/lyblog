import { postAction } from "@/api/manage"

export const publishArticle = (param) => postAction("/admin/article/publish", param)
export const deleteArticle = (articleId) => postAction("/admin/article/delete", { articleId })
export const getArticlePage = (param) => postAction("/admin/article/page", param)
export const getArticleDetail = (articleId) => postAction("/admin/article/detail", { articleId })
export const updateArticle = (param) => postAction("/admin/article/update", param)
