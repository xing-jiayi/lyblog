import {defineStore} from 'pinia'
import {ref} from 'vue'
import {getUserInfo} from '@/api/admin/user'
import {removeToken} from '@/composables/cookie'

export const useUserStore = defineStore('user', () => {
        const userInfo = ref({})

        function setUserInfo() {
            getUserInfo().then(res => {
                if (res.success === true) {
                    userInfo.value = res.data
                }
            })
        }

        function logout() {
            // 删除 cookie 中的 token 令牌
            removeToken()
            // 删除登录用户信息
            userInfo.value = {}
        }

        return {userInfo, setUserInfo, logout}
    },
    {
        // 开启持久化
        persist: true,
    })
