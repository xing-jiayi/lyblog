import { postAction } from "@/api/manage"

export const getBlogSettingsDetail = (userId) => postAction("/admin/blog/settings/detail", { userId })
export const updateSettings = (param) => postAction("/admin/blog/settings/update ", param)
