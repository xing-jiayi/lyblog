import nprogress from "nprogress"

// 显示页面加载 Loading
export function showPageLoading() {
	nprogress.start()
}

// 隐藏页面加载 Loading
export function hidePageLoading() {
	nprogress.done()
}
