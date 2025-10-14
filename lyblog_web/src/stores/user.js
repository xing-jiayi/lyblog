import {defineStore} from 'pinia'
import {ref} from 'vue'
import {getUserInfo} from '@/api/admin/user'

export const useUserStore = defineStore('user', () => {
        const userInfo = ref({})

        function setUserInfo() {
            getUserInfo().then(res => {
                if (res.success === true) {
                    userInfo.value = res.data
                }
            })
        }

        return {userInfo, setUserInfo}
    },
    {
        // 开启持久化
        persist: true,
    })
