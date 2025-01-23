<template>
    <div>
      <el-row>
        <el-col v-for="(item, index) in cartItems" :key="item.id" :span="8" :offset="index > 0 && index % 3 === 0 ? 2 : 0">
          <el-card :body-style="{ padding: '0px' }">
            <img :src="item.url" class="url" alt="商品图片">
            <div style="padding: 14px;">
              <span>{{ item.name }}</span>
              <div class="bottom clearfix">
                <el-button type="text" size="mini" @click="decreaseQuantity(index)">-</el-button>
                <span>{{ item.quantity }}</span>
                <el-button type="text" size="mini" @click="increaseQuantity(index)">+</el-button>
                <el-button type="danger" size="mini" @click="removeFromCart(index)">删除</el-button>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <el-button type="primary" @click="createOrder">创建订单</el-button>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        cartItems: [
          { product_id: 1, name: '汉堡', url: 'https://example.com/hamburger.png', quantity: 1 },
          { product_id: 2, name: '薯条', url: 'https://example.com/fries.png', quantity: 1 },
          // ...更多商品
        ]
      };
    },
    methods: {
      increaseQuantity(index) {
        this.cartItems[index].quantity++;
      },
      decreaseQuantity(index) {
        if (this.cartItems[index].quantity > 1) {
          this.cartItems[index].quantity--;
        } else {
          this.$message.warning('商品数量不能少于1');
        }
      },
      removeFromCart(index) {
        this.cartItems.splice(index, 1);
      },
      createOrder() {
        if (this.cartItems.length === 0) {
          this.$message.warning('购物车为空，无法创建订单');
          return;
        }
        // 模拟创建订单
        const order = {
          id: Date.now(), // 使用当前时间戳作为订单ID（仅示例）
          items: [...this.cartItems]
        };
        // 这里可以添加将订单发送到后端的逻辑
        console.log('订单已创建:', order);
        this.$message.success('订单创建成功');
        // 清空购物车（可选）
        // this.cartItems = [];
      }
    }
  };
  </script>
  
 