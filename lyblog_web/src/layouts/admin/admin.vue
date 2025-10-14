<template>
  <el-container>
    <!--侧边菜单-->
    <el-aside :width="menuStore.menuWidth" class="transition-all">
      <AdminMenu></AdminMenu>
    </el-aside>

    <!--顶部-->
    <el-container>
      <el-header>
        <AdminHeader></AdminHeader>
      </el-header>

      <!--主体-->
      <el-main>
        <AdminTagList></AdminTagList>
        <router-view v-slot="{ Component  }">
          <Transition name="fade">
            <KeepAlive :max="10">
              <component :is="Component"></component>
            </KeepAlive>
          </Transition>

        </router-view>
      </el-main>

      <!--页脚-->
      <el-footer>
        <AdminFooter></AdminFooter>
      </el-footer>
    </el-container>
  </el-container>
</template>

<script setup>
// 引入组件
import AdminFooter from "./components/AdminFooter.vue"
import AdminHeader from "./components/AdminHeader.vue"
import AdminMenu from "./components/AdminMenu.vue"
import AdminTagList from "./components/AdminTagList.vue"
import {useMenuStore} from "@/stores/menu"

const menuStore = useMenuStore()
</script>

<style scoped>
.el-header {
  padding: 0 !important;
}

.el-footer {
  padding: 0 !important;
}

/* 内容区域过渡动画：淡入淡出效果 */
/* 刚开始进入时 */
.fade-enter-from {
  /* 透明度 */
  opacity: 0;
}

/* 刚开始结束 */
.fade-enter-to {
  opacity: 1;
}

/* 刚开始离开 */
.fade-leave-from {
  opacity: 1;
}

/* 离开已结束 */
.fade-leave-to {
  opacity: 0;
}

/* 离开进行中 */
.fade-leave-active {
  transition: all 0.3s;
}

/* 进入进行中 */
.fade-enter-active {
  transition: all 0.3s;
  transition-delay: 0.3s;
}
</style>
