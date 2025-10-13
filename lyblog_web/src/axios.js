import axios from "axios"
import { getToken } from "@/composables/auth"

// 创建 Axios 实例
const instance = axios.create({
	baseURL: "/api", // 你的 API 基础 URL
	timeout: 10000, // 请求超时时间
})

// 添加请求拦截器
instance.interceptors.request.use(
	function (config) {
		const token = getToken()
		if (token) {
			config.headers["Authorization"] = "Bearer " + token
		}
		return config
	},
	function (error) {
		return Promise.reject(error)
	}
)

// 添加响应拦截器
instance.interceptors.response.use(
	function (response) {
		return response.data
	},
	function (error) {
		let errorMsg = error.response.data.message || "请求失败"
		ElMessage.error(errorMsg)
		return Promise.reject(error)
	}
)

// 暴露出去
export default instance
