import { postAction } from "@/api/manage"

export const login = (param) => postAction("/login", param)
export const getUserInfo = () => postAction("/admin/user/info")
export const updateUserPassword = (user) => postAction("/admin/password/update", user)
