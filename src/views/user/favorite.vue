<template>
  <div v-if="loading">加载中...</div>
  <div v-else>
    <el-row :gutter="20">
      <el-col v-model="favoriteItem" :span="12">
        <el-card class="favorite-card">
          <img :src="favoriteItem.url" class="image" alt="商品图片">
          <div class="info">
            <h3>{{ favoriteItem.name }}</h3>
            <p>价格: ¥{{ favoriteItem.price}}</p>
            <p>卖家: {{ favoriteItem.merchant_id }}</p>
          </div>
          <div class="action">
            <el-button type="danger" size="mini" @click="unfavorite(favoriteItem.id)">取消收藏</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>

import { getFavoritesByUser } from '@/api';
import { getProductById } from '@/api';
export default {
  data() {
    return {
      
      favoriteItem: {
        id:0,
        merchant_id:100,
        price:0.0,
        stock:0,
        category_id:0,
        description:"",
        create_time:"2024-02-23",
        url:''

      },
      merchants: [
        { id: 101, name: '商家A' },
        { id: 102, name: '商家B' },
        // ...更多商家信息
      ],
      loading: true,
      userId: 100 ,// 假设这是当前用户的ID，需要根据实际情况获取
      product_id:1
    };
  },
   created() {
    this.fetchFavorites();
    this.loading=false;
  },
  methods: {
    fetchFavorites(){
      const response = getFavoritesByUser(this.userId);
     
      response.then(res=>{
        console.log(res);
        const {productId}=res.data[0]
        console.log(productId);
        
        if(productId)
        this.Viewproduct(productId);
       
      })
      
         
         
    },
    Viewproduct(proid){
      getProductById(proid).then(res=>{
        console.log(res.data);
         this.favoriteItem=res.data;
         console.log(this.favoriteItem);
         
         
      })
    
      
     
    },
    // async fetchFavorites() {
    //   try {
    //     const response = await axios.get('/api/favorites', {
    //       params: { userId: this.userId }
    //     });
    //     this.favoriteItems = response.data;
    //   } catch (error) {
    //     console.error('Failed to fetch favorites:', error);
    //   } finally {
    //     this.loading = false;
    //   }
    // },
    unfavorite(itemId) {
      this.favoriteItems = this.favoriteItems.filter(item => item.id !== itemId);
      // 这里可以添加将取消收藏的请求发送到后端的逻辑
      this.$message.success('已取消收藏');
    },
    getMerchantName(merchantId) {
      const merchant = this.merchants.find(m => m.id === merchantId);
      return merchant ? merchant.name : '未知商家';
    }
  }
};
</script>

<style scoped>
  .favorite-card {
    position: relative;
    margin-bottom: 20px;
  }
  
  .image {
    width: 100%;
    height: auto;
  }
  
  .info {
    padding: 16px;
  }
  
  .info h3 {
    margin: 0 0 16px;
    font-size: 18px;
  }
  
  .info p {
    margin: 0 0 8px;
    color: #666;
  }
  
  .action {
    position: absolute;
    top: 16px;
    right: 16px;
  }
</style>