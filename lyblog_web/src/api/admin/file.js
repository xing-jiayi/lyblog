import { postAction } from "@/api/manage"

export const uploadFile = (file) => postAction("/admin/file/upload", file)
