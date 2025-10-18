import { defineStore } from "pinia"
import { ref, reactive } from "vue"
import { useUserStore } from "@/stores/user"

export const useBlogStore = defineStore(
	"blog",
	() => {
		// 用户ID（供接口使用）
		const userStore = useUserStore()
		const userId = userStore.userInfo?.userId || ""

		// 博客设置信息（与 blog-setting.vue 中字段保持一致）
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

		// 设置用户ID
		function setUserId(id) {
			userId.value = id || ""
		}

		// 批量更新博客设置信息
		function setBlogSettings(data = {}) {
			Object.assign(blogSetting, {
				blogName: data.blogName ?? blogSetting.blogName,
				author: data.author ?? blogSetting.author,
				logo: data.logo ?? blogSetting.logo,
				avatar: data.avatar ?? blogSetting.avatar,
				introduction: data.introduction ?? blogSetting.introduction,
				githubHomepage: data.githubHomepage ?? blogSetting.githubHomepage,
				giteeHomepage: data.giteeHomepage ?? blogSetting.giteeHomepage,
				zhihuHomepage: data.zhihuHomepage ?? blogSetting.zhihuHomepage,
				csdnHomepage: data.csdnHomepage ?? blogSetting.csdnHomepage,
				customUrl: data.customUrl ?? blogSetting.customUrl,
			})
		}

		// 重置博客设置信息为默认值
		function resetBlogSettings() {
			setBlogSettings({
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
		}

		return { userId, blogSetting, setUserId, setBlogSettings, resetBlogSettings }
	},
	{
		// 开启持久化
		persist: true,
	}
)
