import "@/assets/main.css"

import { createApp } from "vue"
import App from "@/App.vue"
import router from "@/router"
import * as ElementPlusIconsVue from "@element-plus/icons-vue"
import "animate.css"
import "@/permission"
import "nprogress/nprogress.css"
import { createPinia } from "pinia"

// Create the app instance and store it in a variable
const app = createApp(App)

// Use router
app.use(router)

// Register all Element Plus icons
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
	app.component(key, component)
}

// Mount the app after all configurations
app.mount("#app")
const pinia = createPinia()
app.use(pinia)
