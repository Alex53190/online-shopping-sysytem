<template>
  <div id="app" style="padding-top: 30px;">
    <el-descriptions title="用户信息">
      <el-descriptions-item label="用户ID">{{ userId }}</el-descriptions-item>
      <el-descriptions-item>
        <div>
          <el-button type="primary" @click="goToFavorites">收藏夹</el-button>
          <el-button type="success" @click="goToCart">购物车</el-button>
          <el-button type="warning" @click="goToOrders">订单</el-button>
        </div>
      </el-descriptions-item>
    </el-descriptions>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userId: '123456789' // 示例用户ID
    };
  },
  methods: {
    goToFavorites() {
      this.$router.push('/favorite');
    },
    goToCart() {
      this.$router.push({ name: 'Cart' });
    },
    goToOrders() {
      this.$router.push({ name: 'Orders' });
    }
  }
};
</script>

<style>

</style>