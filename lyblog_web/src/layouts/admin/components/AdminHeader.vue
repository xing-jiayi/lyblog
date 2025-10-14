<template>
  <!-- 通过 flex 指定水平布局 -->
  <div class="bg-white h-[64px] flex pr-4 border-b border-slate-100">
    <!-- 左边栏收缩、展开 -->
    <div
        class="w-[42px] h-[64px] cursor-pointer flex items-center justify-center text-gray-700 hover:bg-gray-200"
        @click="handleMenuWidth">
      <el-icon>
        <Fold v-if="menuStore.menuWidth === '250px'"/>
        <Expand v-else/>
      </el-icon>
    </div>

    <!-- 右边容器，通过 ml-auto 让其在父容器的右边 -->
    <div class="ml-auto flex">
      <el-tooltip class="box-item" effect="dark" content="刷新" placement="bottom">
        <div
            class="w-[42px] h-[64px] cursor-pointer flex items-center justify-center text-gray-700 hover:bg-gray-200"
            @click="handleRefresh">
          <el-icon>
            <Refresh/>
          </el-icon>
        </div>
      </el-tooltip>
      <el-tooltip class="box-item" effect="dark" content="全屏" placement="bottom">
        <div
            class="w-[42px] h-[64px] cursor-pointer flex items-center justify-center text-gray-700 mr-2 hover:bg-gray-200"
            @click="toggle">
          <el-icon>
            <FullScreen v-if="!isFullscreen"/>
            <Aim v-else/>
          </el-icon>
        </div>
      </el-tooltip>

      <!-- 登录用户头像 -->
      <el-dropdown class="flex items-center justify-center" @command="handleCommand">
				<span class="el-dropdown-link flex items-center justify-center text-gray-700 text-xs">
					<!-- 头像 Avatar -->
					<el-avatar
              class="mr-2"
              :size="25"
              src="https://img.quanxiaoha.com/quanxiaoha/f97361c0429d4bb1bc276ab835843065.jpg"/>
					{{ userStore.userInfo.username }}
					<el-icon class="el-icon--right">
						<arrow-down/>
					</el-icon>
				</span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item command="updatePassword">修改密码</el-dropdown-item>
            <el-dropdown-item command="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>

  <!-- 修改密码 -->
  <el-dialog v-model="dialogVisible" title="修改密码" width="40%" :draggable="true" :close-on-click-modal="false"
             :close-on-press-escape="false">
    <el-form ref="formRef" :rules="rules" :model="user">
      <el-form-item label="用户名" prop="username" label-width="120px">
        <!-- 输入框组件 -->
        <el-input size="large" v-model="user.username" placeholder="请输入用户名" clearable disabled/>
      </el-form-item>
      <el-form-item label="新密码" prop="password" label-width="120px">
        <el-input size="large" type="password" v-model="user.password" placeholder="请输入新密码"
                  clearable show-password/>
      </el-form-item>
      <el-form-item label="确认新密码" prop="rePassword" label-width="120px">
        <el-input size="large" type="password" v-model="user.rePassword" placeholder="请确认新密码"
                  clearable show-password/>
      </el-form-item>
    </el-form>
    <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="onSubmit">
                    提交
                </el-button>
            </span>
    </template>
  </el-dialog>
</template>

<script setup>
import {ref, reactive, watch} from 'vue'
import {useMenuStore} from "@/stores/menu"
import {useFullscreen} from "@vueuse/core"
import {useUserStore} from "@/stores/user"
import {useRouter} from 'vue-router'
import {showModel} from "@/composables/utils";
import {updateUserPassword} from "@/api/admin/user";

const menuStore = useMenuStore()
const userStore = useUserStore()
const router = useRouter()
const handleMenuWidth = () => {
  // 动态设置菜单的宽度大小
  menuStore.handleMenuWidth()
}
const {isFullscreen, toggle} = useFullscreen()
const handleRefresh = () => location.reload()


// 对话框是否显示
const dialogVisible = ref(false)

const handleCommand = (command) => {
  // 更新密码
  if (command === 'updatePassword') {
    dialogVisible.value = true
  } else if (command === 'logout') { // 退出登录
    logout()
  }
}

function logout() {
  showModel('是否确认要退出登录？').then(() => {
    userStore.logout()
    ElMessage.success('退出登录成功！')
    // 跳转登录页
    router.push('/login')
  })
}

const user = reactive({
  username: userStore.userInfo.username || '',
  password: '',
  rePassword: ''
})

watch(() => userStore.userInfo.username, (newValue, oldValue) => {
  user.username = newValue
});
const formRef = ref(null)
const rules = reactive({
  username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
  password: [
    {required: true, message: '请输入新密码', trigger: 'blur'},
    {min: 6, message: '密码长度不能小于6位', trigger: 'blur'}
  ],
  rePassword: [
    {required: true, message: '请确认新密码', trigger: 'blur'},
    {
      validator: (rule, value, callback) => {
        if (value !== user.password) {
          callback(new Error('两次输入的密码不一致'))
        } else {
          callback()
        }
      },
      trigger: 'blur'
    }
  ]
})

const onSubmit = () => {
  formRef.value.validate((valid) => {
    if (!valid) {
      ElMessage.error('请完善表单信息')
      return
    }
    updateUserPassword(user).then(res => {
      if (res.success) {
        ElMessage.success('密码修改成功，请重新登录')
        dialogVisible.value = false
        userStore.logout()
        router.push('/login')
      } else {
        ElMessage.error(res.errorMessage)
      }
    })
  })

}

</script>

<style scoped>
.el-header {
  padding: 0 !important;
}
</style>
