<template>
	<div class="grid grid-cols-2 h-screen">
		<div class="col-span-2 order-2 p-10 md:col-span-1 md:order-1 bg-slate-900">
			<!-- 指定为 flex 布局，并设置为屏幕垂直水平居中，高度为 100% -->
			<div
				class="flex justify-center items-center h-full flex-col animate__animated animate__bounceInLeft animate__fast">
				<h2 class="font-bold text-4xl mb-7 text-white">LyBlog 博客登录</h2>
				<p class="text-white">一款由 Spring Boot + Mybaits Plus + Vue 3.2 + Vite 4 开发的前后端分离博客。</p>
				<!-- 指定图片宽度为父级元素的 1/2 -->
				<img src="@/assets/img/dev_background.png" class="w-1/2" />
			</div>
		</div>
		<div class="col-span-2 order-1 md:col-span-1 md:order-2 bg-white">
			<!-- flex-col 用于指定子元素垂直排列 -->
			<div
				class="flex justify-center items-center h-full flex-col animate__animated animate__bounceInRight animate__fast">
				<h1 class="font-bold text-4xl mb-5">欢迎回来</h1>
				<!-- 设置 flex 布局，内容垂直水平居中，文字颜色，以及子内容水平方向 x 轴间距 -->
				<div class="flex items-center justify-center mb-7 text-gray-400 space-x-2">
					<!-- 左边横线，高度为 1px, 宽度为 16，背景色设置 -->
					<span class="h-[1px] w-16 bg-gray-200"></span>
					<span>账号密码登录</span>
					<!-- 右边横线 -->
					<span class="h-[1px] w-16 bg-gray-200"></span>
				</div>
				<!-- 引入 Element Plus 表单组件，移动端设置宽度为 5/6，PC 端设置为 2/5 -->
				<el-form class="w-5/6 md:w-2/5" ref="form" :rules="rules" :model="user">
					<el-form-item prop="username">
						<!-- 输入框组件 -->
						<el-input size="large" v-model="user.username" placeholder="请输入用户名" :prefix-icon="User" clearable />
					</el-form-item>
					<el-form-item prop="password">
						<!-- 密码框组件 -->
						<el-input
							size="large"
							v-model="user.password"
							type="password"
							placeholder="请输入密码"
							:prefix-icon="Lock"
							clearable />
					</el-form-item>
					<el-form-item>
						<!-- 登录按钮，宽度设置为 100% -->
						<el-button class="w-full" size="large" type="primary" @click="onSubmit" :loading="loading">登录</el-button>
					</el-form-item>
				</el-form>
			</div>
		</div>
	</div>
</template>

<script setup>
	import { User, Lock } from "@element-plus/icons-vue"
	import { login } from "@/api/admin/user"
	import { onBeforeUnmount, onMounted, reactive, ref } from "vue"
	import { useRouter } from "vue-router"
	import { setToken } from "../../composables/auth"
	// import { ElMessage } from "element-plus"

	const router = useRouter()
	const form = ref(null)
	const loading = ref(false)
	const rules = reactive({
		username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
		password: [{ required: true, message: "请输入密码", trigger: "blur" }],
	})

	const user = reactive({
		username: "",
		password: "",
	})

	const onSubmit = () => {
		form.value.validate((valid) => {
			if (!valid) {
				ElMessage.error("用户名和密码不能为空")
				return
			}
		})
		loading.value = true
		login(user.username, user.password)
			.then((res) => {
				if (res.success) {
					ElMessage.success("登录成功")
					router.push("/admin/index")
					let token = res.data.token
					setToken(token)
				} else {
					ElMessage.error(res.errorMessage)
				}
			})
			.finally(() => {
				loading.value = false
			})
	}

	function onKeyUp(e) {
		if (e.key == "Enter") {
			onSubmit()
		}
	}

	// 添加键盘监听
	onMounted(() => {
		document.addEventListener("keyup", onKeyUp)
	})

	// 移除键盘监听
	onBeforeUnmount(() => {
		document.removeEventListener("keyup", onKeyUp)
	})
</script>
