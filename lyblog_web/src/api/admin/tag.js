import { postAction } from "@/api/manage"

export const queryTagByName = (name) => postAction("/admin/tag/queryByName", { name })
export const getAllTagList = () => postAction("/admin/tag/list")
export const queryTagPage = (param) => postAction("/admin/tag/page", param)
export const addTag = (param) => postAction("/admin/tag/add", param)
export const deleteTag = (id) => postAction("/admin/tag/delete", { id })
