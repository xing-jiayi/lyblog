import router from "@/router/index"
import { getToken } from "@/composables/cookie"
import { hidePageLoading, showPageLoading } from "@/composables/utils"

// 全局路由前置守卫
router.beforeEach((to, from, next) => {
	showPageLoading()
	let token = getToken()
	if (!token && to.path.startsWith("/admin")) {
		ElMessage.warning("请先登录")
		next({ path: "/login" })
	} else if (token && to.path === "/login") {
		ElMessage.warning("请勿重复登录！")
		next({ path: "/admin/index" })
	} else {
		next()
	}
})

// 全局路由后置守卫
router.afterEach((to, from) => {
	let title = (to.meta.title ? to.meta.title : "") + " - LyBlog"
	document.title = title
	hidePageLoading()
})
