

<template>
	<el-config-provider :locale="locale">
      <router-view></router-view>
   </el-config-provider>
</template>

<script setup>
import zhCn from 'element-plus/es/locale/lang/zh-cn'
	const locale = zhCn
</script>

<style scoped>
	#nprogress .bar {
		background: #cc43d4 !important;
	}

	@font-face {
		font-family: "Yunmobei enfont";
		src: url("/fonts/Yunmobei_enfont.ttf") format("truetype");
	}
</style>
