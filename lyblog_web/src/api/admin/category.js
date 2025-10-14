import { postAction } from "@/api/manage"

export const queryCategoryByName = (name) => postAction("/admin/category/queryByName", { name })
export const getAllCategoryList = () => postAction("/admin/category/list")
export const queryCategoryPage = (param) => postAction("/admin/category/page", param)
export const addCategory = (category) => postAction("/admin/category/add", category)
export const deleteCategory = (id) => postAction("/admin/category/delete", { id })
