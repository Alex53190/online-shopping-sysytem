import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/views/Home.vue';
import Login from '@/views/Login.vue';
import Register from '@/views/Register.vue';
import UserDashboard from '@/views/user/userDashBoard.vue';
import MerchantDashboard from '@/views/merchant/merchantDashBoard.vue';
import AdminDashboard from '@/views/admin/adminDashBoard.vue';
import Cart from '../views/user/cart.vue';
import Productdetails from '../views/user/productdetails.vue';
import Favorite from '@/views/user/favorite.vue';

// 基础路由配置
const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/register',
    name: 'Register',
    component:Register 
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/user',
    name: 'UserDashboard',
    component: UserDashboard,
    meta: { requiresAuth: true, role: 'USER' }
  },
  {
    path: '/merchant',
    name: 'MerchantDashboard',
    component: MerchantDashboard,
    meta: { requiresAuth: true, role: 'MERCHANT' }
  },
  {
    path: '/admin',
    name: 'AdminDashboard',
    component: AdminDashboard,
    meta: { requiresAuth: true, role: 'ADMIN' }
  },
  {
    path:'/productdetails/:id',
    name:'productDetail',
    component:Productdetails

  },
  {
    path: '/favorite',
    name: 'favorite',
    component: Favorite
  },
  {
    path: '/cart',
    name: 'cart',
    component: Cart
  },
];

// 创建 router 对象
const router = createRouter({
  history: createWebHistory(),
  routes
});

// // 路由导航守卫，验证角色权限
// router.beforeEach((to, from, next) => {
//   const isAuthenticated = store.state.isAuthenticated;
//   const userRole = store.state.role;

//   if (to.matched.some(record => record.meta.requiresAuth)) {
//     if (!isAuthenticated) {
//       next({ name: 'Login' });
//     } else if (to.meta.role && to.meta.role !== userRole) {
//       next({ name: 'Home' });
//     } else {
//       next();
//     }
//   } else {
//     next();
//   }
// });

export default router;