
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'element-plus/dist/index.css'
import ElementPlus from 'element-plus'
import { createI18n } from 'vue-i18n';
import en from '../locales/en.json';
import zh from '../locales/zh.json';

const app = createApp(App)

const messages = {
    en,
    zh,
  };
  
 const i18n = createI18n({
    locale: 'en', // 默认语言
    fallbackLocale: 'en',
    messages,
  });

app.use(router)
app.use(ElementPlus, { size: 'small', zIndex: 3000 })
app.use(i18n);


app.mount('#app')
