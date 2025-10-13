import router from "@/router/index"
import { getToken } from "@/composables/auth"

// 全局路由前置守卫
router.beforeEach((to, from, next) => {
	let token = getToken()
	if (!token && to.path.startsWith("/admin")) {
		ElMessage.warning("请先登录")
		next({ path: "/login" })
	} else {
		next()
	}
})

// 全局路由后置守卫
router.afterEach((to, from) => {
	let title = (to.meta.title ? to.meta.title : "") + " - LyBlog"
	document.title = title
})
