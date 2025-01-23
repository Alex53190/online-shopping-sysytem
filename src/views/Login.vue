<template>
  <div class="login-container">
    <!-- <div>
       <el-button type="primary" round @click="getOrders">Primary</el-button>
    </div> -->
    <div class="language-switcher">
      <button @click="switchLanguage('en')">English</button>
      <button @click="switchLanguage('zh')">中文</button>
    </div>
    <div class="login-header">
      <h1>{{ $t('welcome_back') }}</h1>
      <p>
        <a href="/register">{{ $t('new_to_fashionest') }}</a>
      </p>
    </div>
    <form @submit="submitLogin" class="login-form">
      <div class="form-group">
        <label for="email">{{ $t('email_or_phone') }}</label>
        <input
          v-model="form.username"
          type="text"
          id="email"
          placeholder="example@mail.com"
          class="input-field"
        />
      </div>
      <div class="form-group">
        <label for="password">{{ $t('password') }}</label>
        <input
          v-model="form.password"
          type="password"
          id="password"
          placeholder="••••••••"
          class="input-field"
        />
      </div>
      <div class="checkbox-group">
        <label>
          <input type="checkbox" v-model="keepSignedIn" />
          {{ $t('keep_signed_in') }}
        </label>
        <label>
          <input type="checkbox" v-model="agreeToPrivacy" />
          {{ $t('privacy_policy_agreement') }}
        </label>
      </div>
      <el-button
      @click="userslogin"
        type="primary"
        round
        :disabled="!agreeToPrivacy"
        class="sign-in-button"
      >
        {{ $t('sign_in') }}
      </el-button>
      <div class="or-divider">{{ $t('or') }}</div>
      <button type="button" class="google-button">
        <span class="google-icon">G</span> {{ $t('continue_with_google') }}
      </button>
      <div class="forgot-password">
        <a href="/forgot-password">{{ $t('forgot_password') }}</a>
      </div>
      <div v-if="errorMessage" class="error-message">
        {{ errorMessage }}
      </div>
    </form>
  </div>
</template>

<script>
import { getMerchantOrders } from '../api/index';
import { userlogin,getUserRole } from "@/api";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { ElMessage } from 'element-plus'
export default {
  data() {
    return {
      form:{
        username: "",
        password: "",
      },

      role:"",
      keepSignedIn: false,
      agreeToPrivacy: false,
      errorMessage: "",
      merchantId:100
    };
  },
  setup() {
    const router = useRouter();
    const store = useStore();

    return {
      router,
      store,
    };
  },
  methods: {
   getOrders(){
    getMerchantOrders(this.merchantId).then(res=>{
      console.log(res);
      
    })
},
   submitLogin() {
      if (!this.agreeToPrivacy) {
        this.errorMessage = this.$t('must_agree_privacy');
        return;
      }        
      } ,
  userslogin(){
        userlogin(this.form).then(
       res=>{
        console.log(res);
        if(res.data==='登录成功'){
          ElMessage({ message: '登陆成功',type: 'success'})
      //根据权限选择界面
   getUserRole(this.form.username).then(res=>{
      console.log(res);
      this.role=res.data;     
      if (this.role === "ADMIN") {
        ElMessage.success('欢迎!管理员')
          this.$router.push("/admin");
        } else if (this.role === "MERCHANT") {
          ElMessage.success('欢迎!商户')
          this.$router.push("/merchant");
        } else {
          ElMessage.success('欢迎!用户')
          this.$router.push("/user");
        }
      

   })
    
    
  }
  else {
    ElMessage({message: res.data,type: 'error',})
    
    
  
}
  }
        
       
       
       
        )
      },
      
      //切换语言
  switchLanguage(lang) {
      this.$i18n.locale = lang;
    },
    },

  
  
};
</script>

<style scoped>
/* Container styling */
.login-container {
  max-width: 400px;
  margin: 50px auto;
  font-family: Arial, sans-serif;
}

.login-header {
  text-align: center;
  margin-bottom: 20px;
}

.login-header h1 {
  font-size: 24px;
  margin-bottom: 5px;
}

.login-header p {
  font-size: 14px;
  margin: 0;
}

.login-header a {
  color: #6366f1;
  text-decoration: none;
}

.login-header a:hover {
  text-decoration: underline;
}

/* Form styling */
.login-form {
  background: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 15px;
}

.label {
  font-size: 14px;
  color: #333;
}

.input-field {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.input-field:focus {
  outline: none;
  border-color: #6366f1;
}

.checkbox-group {
  margin-bottom: 20px;
  font-size: 14px;
}

.checkbox-group label {
  display: block;
  margin-bottom: 5px;
}

.sign-in-button {
  width: 100%;
  background-color: #6366f1;
  color: white;
  font-size: 16px;
  padding: 10px;
  border: none;
  cursor: pointer;
}

.sign-in-button:disabled {
  background-color: #ccc;
}

.or-divider {
  text-align: center;
  margin: 15px 0;
  font-size: 14px;
  color: #888;
}

.google-button {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  background-color: white;
  font-size: 14px;
  cursor: pointer;
}

.google-button .google-icon {
  font-size: 18px;
  margin-right: 10px;
}

.forgot-password {
  text-align: center;
  margin-top: 15px;
  font-size: 14px;
}

.forgot-password a {
  color: #6366f1;
  text-decoration: none;
}

.forgot-password a:hover {
  text-decoration: underline;
}

/* Error message styling */
.error-message {
  color: red;
  font-size: 14px;
  margin-top: 10px;
  text-align: center;
}

.language-switcher {
  text-align: right;
  margin-bottom: 10px;
}

.language-switcher button {
  background: none;
  border: 1px solid #ccc;
  padding: 5px 10px;
  margin-left: 10px;
  cursor: pointer;
}

.language-switcher button:hover {
  background-color: #f0f0f0;
}
</style>