<template>
	<div>
		<!-- 卡片组件， shadow="never" 指定 card 卡片组件没有阴影 -->
		<el-card shadow="never">
			<el-form ref="settingFormRef" :model="blogSetting" label-width="160px" :rules="rules">
				<el-form-item label="博客名称" prop="blogName">
					<el-input v-model="blogSetting.blogName" clearable />
				</el-form-item>
				<el-form-item label="作者名" prop="author">
					<el-input v-model="blogSetting.author" clearable />
				</el-form-item>
				<el-form-item label="博客 LOGO" prop="logo">
					<el-upload
						class="avatar-uploader"
						action="#"
						:on-change="(file) => handleImageChange(file, 'logo')"
						:auto-upload="false"
						:show-file-list="false">
						<img v-if="blogSetting.logo" :src="blogSetting.logo" class="avatar" />
						<el-icon v-else class="avatar-uploader-icon">
							<Plus />
						</el-icon>
					</el-upload>
				</el-form-item>
				<el-form-item label="作者头像" prop="avatar">
					<el-upload
						class="avatar-uploader"
						action="#"
						:on-change="(file) => handleImageChange(file, 'avatar')"
						:auto-upload="false"
						:show-file-list="false">
						<img v-if="blogSetting.avatar" :src="blogSetting.avatar" class="avatar" />
						<el-icon v-else class="avatar-uploader-icon">
							<Plus />
						</el-icon>
					</el-upload>
				</el-form-item>
				<el-form-item label="介绍语" prop="introduction">
					<el-input v-model="blogSetting.introduction" type="textarea" />
				</el-form-item>
				<!-- 开启 Github 访问 -->
				<el-form-item label="开启 GihHub 访问">
					<el-switch
						v-model="isGithubChecked"
						inline-prompt
						:active-icon="Check"
						:inactive-icon="Close"
						@change="githubSwitchChange" />
				</el-form-item>
				<el-form-item label="GitHub 主页访问地址" v-if="isGithubChecked">
					<el-input v-model="blogSetting.githubHomepage" clearable placeholder="请输入 GitHub 主页访问的 URL" />
				</el-form-item>
				<!-- 开启 gitee 访问 -->
				<el-form-item label="开启 Gitee 访问">
					<el-switch
						v-model="isGiteeChecked"
						inline-prompt
						:active-icon="Check"
						:inactive-icon="Close"
						@change="giteeSwitchChange" />
				</el-form-item>
				<el-form-item label="Gitee 主页访问地址" v-if="isGiteeChecked">
					<el-input v-model="blogSetting.giteeHomepage" clearable placeholder="请输入 Gitee 主页访问的 URL" />
				</el-form-item>
				<!-- 开启 知乎 访问 -->
				<el-form-item label="开启 知乎 访问">
					<el-switch
						v-model="isZhihuChecked"
						inline-prompt
						:active-icon="Check"
						:inactive-icon="Close"
						@change="zhihuSwitchChange" />
				</el-form-item>
				<el-form-item label="知乎 主页访问地址" v-if="isZhihuChecked">
					<el-input v-model="blogSetting.zhihuHomepage" clearable placeholder="请输入 知乎 主页访问的 URL" />
				</el-form-item>
				<!-- 开启 CSDN 访问 -->
				<el-form-item label="开启 CSDN 访问">
					<el-switch
						v-model="isCSDNChecked"
						inline-prompt
						:active-icon="Check"
						:inactive-icon="Close"
						@change="csdnSwitchChange" />
				</el-form-item>
				<el-form-item label="CSDN 主页访问地址" v-if="isCSDNChecked">
					<el-input v-model="blogSetting.csdnHomepage" clearable placeholder="请输入 CSDN 主页访问的 URL" />
				</el-form-item>
				<!-- 开启 自定义访问 -->
				<el-form-item label="开启 自定义访问">
					<el-switch
						v-model="isCustomChecked"
						inline-prompt
						:active-icon="Check"
						:inactive-icon="Close"
						@change="customSwitchChange" />
				</el-form-item>
				<el-form-item label="自定义访问地址" v-if="isCustomChecked">
					<el-input v-model="blogSetting.customUrl" clearable placeholder="请输入自定义访问地址" />
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="onSubmit" :loading="btnLoading">保存</el-button>
				</el-form-item>
			</el-form>
		</el-card>
	</div>
</template>

<script setup>
	import { reactive, ref } from "vue"
	import { getUserInfo } from "@/api/admin/user"
	import { getBlogSettingsDetail, updateSettings } from "@/api/admin/settings"
	import { uploadFile } from "@/api/admin/file"

  const settingFormRef = ref(null)
  const btnLoading = ref(false)
	// 表单对象
	const blogSetting = reactive({
		blogName: "",
		author: "",
		logo: "",
		avatar: "",
		introduction: "",
		githubHomepage: "",
		giteeHomepage: "",
		zhihuHomepage: "",
		csdnHomepage: "",
		customUrl: "",
	})

	// 规则校验
	const rules = {
		blogName: [{ required: true, message: "请输入博客名称", trigger: "blur" }],
		author: [{ required: true, message: "请输入作者名", trigger: "blur" }],
		logo: [{ required: true, message: "请上传博客 LOGO", trigger: "blur" }],
		avatar: [{ required: true, message: "请上传作者头像", trigger: "blur" }],
		introduction: [{ required: false, message: "请输入介绍语", trigger: "blur" }],
	}

	const userId = ref("")
	const initBlogSettings = () => {
		getUserInfo().then((res) => {
			if (res.success) {
				getBlogSettingsDetail(res.data.userId).then((detailRes) => {
					if (detailRes.success) {
						Object.assign(blogSetting, detailRes.data)
						// 设置是否开启 GitHub
						isGithubChecked.value = blogSetting.githubHomepage !== ""
						// 设置是否开启 Gitee
						isGiteeChecked.value = blogSetting.giteeHomepage !== ""
						// 设置是否开启 知乎
						isZhihuChecked.value = blogSetting.zhihuHomepage !== ""
						// 设置是否开启 CSDN
						isCSDNChecked.value = blogSetting.csdnHomepage !== ""
						// 设置是否开启 自定义访问
						isCustomChecked.value = blogSetting.customUrl !== ""

						console.log(blogSetting)
					}
				})
			}
		})
	}
	initBlogSettings()

	// 上传图片
	const handleImageChange = (file, uptype) => {
		let formData = new FormData()
		formData.append("file", file.raw)
		uploadFile(formData).then((res) => {
			if (res.success) {
				if (uptype === "logo") {
					blogSetting.logo = res.data.url
				} else if (uptype === "avatar") {
					blogSetting.avatar = res.data.url
				}
				ElMessage.success("上传成功")
			} else {
				ElMessage.error(res.errorMessage || "上传失败")
				return
			}
		})
	}

	// 是否开启 GitHub
	const isGithubChecked = ref(false)
	// 监听 Github Switch 改变事件
	const githubSwitchChange = (checked) => {
		if (checked == false) {
			blogSetting.githubHomepage = ""
		}
	}

	// 是否开启 Gitee
	const isGiteeChecked = ref(false)
	// 监听 Gitee Switch 改变事件
	const giteeSwitchChange = (checked) => {
		if (checked == false) {
			blogSetting.giteeHomepage = ""
		}
	}

	// 是否开启知乎
	const isZhihuChecked = ref(false)
	// 监听知乎 Switch 改变事件
	const zhihuSwitchChange = (checked) => {
		if (checked == false) {
			blogSetting.zhihuHomepage = ""
		}
	}

	// 是否开启 CSDN
	const isCSDNChecked = ref(false)
	// 监听 CSDN Switch 改变事件
	const csdnSwitchChange = (checked) => {
		if (checked == false) {
			blogSetting.csdnHomepage = ""
		}
	}

	// 是否开启 自定义访问
	const isCustomChecked = ref(false)
	// 监听 自定义访问 Switch 改变事件
	const customSwitchChange = (checked) => {
		if (checked == false) {
			blogSetting.customUrl = ""
		}
	}

	const onSubmit = () => {
    btnLoading.value = true
		settingFormRef.value.validate((valid) => {
			if (valid) {
				updateSettings(blogSetting).then((res) => {
					if (res.success) {
						ElMessage.success("保存成功")
            initBlogSettings()
            btnLoading.value = false
					} else {
						ElMessage.error(res.errorMessage || "保存失败")
						btnLoading.value = false
					}
				})
			}
		})
	}
</script>

<style scoped>
	.avatar-uploader .avatar {
		width: 100px;
		height: 100px;
		display: block;
	}
</style>
<style>
	/* 解决 textarea :focus 状态下，边框消失的问题 */
	.el-textarea__inner:focus {
		outline: 0 !important;
		box-shadow: 0 0 0 1px var(--el-input-focus-border-color) inset !important;
	}

	.avatar-uploader .el-upload {
		border: 1px dashed var(--el-border-color);
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
		transition: var(--el-transition-duration-fast);
	}

	.avatar-uploader .el-upload:hover {
		border-color: var(--el-color-primary);
	}

	.el-icon.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 100px;
		height: 100px;
		text-align: center;
	}
</style>
