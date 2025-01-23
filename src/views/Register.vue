<template>
    <div class="container">
      <div class="header">
        <div class="logo">Shopper</div>
        <div class="search-bar">
          <input type="text" placeholder="Search" />
        </div>
        <div class="icons">
          <button @click="toggleLanguage">
            {{ currentLanguage === 'en' ? '中文' : 'EN' }}
          </button>
          <i class="fas fa-heart"></i>
          <i class="fas fa-shopping-cart"></i>
          <img src="/profile.png" alt="Profile" class="profile-img" />
        </div>
      </div>
  
      <div class="registration-form">
        <h2>{{ $t('createAccount') }}</h2>
        <form @submit.prevent="register">
          <div class="form-group">
            <label for="username">{{ $t('username') }}</label>
            <input type="tel" id="phone" v-model="form.username" required />
          </div>
          <div class="form-group">
            <label for="password">{{ $t('password') }}</label>
            <input type="password" id="password" v-model="form.password" required />
          </div>
          <div class="form-group">
            <label for="confirm-password">{{ $t('confirmPassword') }}</label>
            <input
              type="password"
              id="confirm-password"
              v-model="confirmPassword"
              required
            />
          </div>
          <div class="form-group">
            <input type="checkbox" id="keep-updated" v-model="keepUpdated" />
            <label for="keep-updated">{{ $t('keepUpdated') }}</label>
          </div>
          <button type="submit" class="register-button">
            {{ $t('register') }}
          </button>
        </form>
      </div>
  
      <div class="footer">
        <a href="#">{{ $t('aboutUs') }}</a>
        <a href="#">{{ $t('contactUs') }}</a>
        <a href="#">{{ $t('privacyPolicy') }}</a>
        <div class="social-icons">
          <i class="fab fa-instagram"></i>
          <i class="fab fa-facebook-f"></i>
          <i class="fab fa-twitter"></i>
        </div>
        <p>&copy; 2025 Shopper. {{ $t('allRightsReserved') }}</p>
      </div>
    </div>
  </template>
  
  <script>
  // Assuming you've imported and configured i18n in your main.js
  // import i18n from './i18n'; // You might need to adjust the path
  import { userRegister } from '@/api';
  import { ElMessage } from 'element-plus'
  
  export default {
    name: 'RegistrationPage',
    data() {
      return {
        form:{
        id:'',
        username: '',
        password: '',
        email:'15508421234@qq.com',
        role:'USER',

        },
        
        confirmPassword: '',
        keepUpdated: true,
        currentLanguage: 'en', // Default language
      };
    },
    methods: {
      register() {
        // Handle registration logic here
        console.log('Registration data:', this.form);
        // You would typically send this data to your backend API
       userRegister(this.form).then(
          (res)=>{ 
            if(res.data==='注册成功'){
              ElMessage.success("注册成功，请登录");
              setInterval(()=>{this.$router.push('/login')},2000);
              
              
            }
            else{
              console.log(res.data);
              
              ElMessage({message: res.data,type: 'error',})
            }
             
           }
        )

      },
      toggleLanguage() {
        this.currentLanguage = this.currentLanguage === 'en' ? 'zh' : 'en';
        this.$i18n.locale = this.currentLanguage; // Use this.$i18n to access the i18n instance
      },
    },
    created() {
      // Set initial language from i18n configuration or user's preference
      this.currentLanguage = this.$i18n.locale;
    },
  };
  </script>
  
  <style scoped>
  .container {
    font-family: Arial, sans-serif;
    display: flex;
    flex-direction: column;
    min-height: 100vh;
  }
  
  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 20px;
    background-color: #fff;
  }
  
  .logo {
    font-weight: bold;
    font-size: 20px;
  }
  
  .search-bar input {
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 20px;
    width: 200px;
  }
  
  .icons {
    display: flex;
    align-items: center;
  }
  
  .icons i,
  .icons button {
    margin-left: 15px;
    font-size: 20px;
    cursor: pointer;
    border: none;
    background: none;
  }
  
  .profile-img {
    width: 30px;
    height: 30px;
    border-radius: 50%;
    margin-left: 15px;
  }
  
  .registration-form {
    padding: 20px;
    max-width: 400px;
    margin: 50px auto;
  }
  
  .form-group {
    margin-bottom: 15px;
  }
  
  .form-group label {
    display: block;
    margin-bottom: 5px;
  }
  
  .form-group input[type='tel'],
  .form-group input[type='password'] {
    width: 95%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  .register-button {
    background-color: #6200ee;
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    width: 100%;
  }
  
  .footer {
    margin-top: auto;
    background-color: #f8f8f8;
    padding: 20px;
    text-align: center;
  }
  
  .footer a {
    margin: 0 10px;
    text-decoration: none;
    color: #333;
  }
  
  .social-icons i {
    margin: 0 10px;
    font-size: 20px;
  }
  </style>