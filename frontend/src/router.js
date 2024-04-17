import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from '@/components/LoginPage.vue';
import MyHomePage from '@/components/MyHomePage.vue';
import store from '@/store'

const routes = [
  { path: '/login', name: 'LoginPage', component: LoginPage },
  { path: '/homepage', name: 'homepage', component: MyHomePage},
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

// 导航守卫
router.beforeEach((to, from, next) => {
  const isLoggedIn = store.state.user.isLoggedIn; // 检查用户是否已登录
  if (to.path !== '/login' && !isLoggedIn) {
    next('/login'); // 如果用户未登录且访问非登录页面，则重定向至登录页面
  } else {
    next(); // 其他情况不做重定向，继续路由导航
  }
});

export default router;