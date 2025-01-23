import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
  server:{
    port:8086,
    // proxy:{
    //   '/api': {
    //     // 当请求的路径以 '/api' 开头时，会触发代理
    //     target: 'http://localhost:8085', // 目标服务器的地址
    //     changeOrigin: true, // 是否改变请求的 origin 头部
    //     rewrite: (path) => path.replace(/^\/api/, '') // 重写请求的路径
    //   },
    // }
  }
  
})
