<template>
  <div class="e-commerce">
    <el-carousel :interval="2000" type="card" height="270px">
      <el-carousel-item v-for="item in product" :key="item">
        <h3 text="2xl" justify="center">
          <el-image
            @click="goToProductDetails(item.id)"
            style="width: 600px; height: 270px"
            :src="item.image"
            fit="fill"
          ></el-image>
        </h3>
      </el-carousel-item>
    </el-carousel>
    <nav class="navbar">
      <div class="nav-right">
        <search-bar />
        <login-register />
      </div>
    </nav>

    <!-- 商品展示区域 -->
    <div class="hero-banner">
      <!-- 顶部导航栏 -->
      <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
        <el-tab-pane label="男装" name="first">
          <div>
            <!-- 包裹前三张图片的容器 -->
            <div v-if="pic2.length > 0" class="image-row">
              <div v-for="item in pic2.slice(0, 3)" :key="item.id">
                <el-image
                  @click="goToProductDetails(item.id)"
                  style="width: 300px; height: 220px"
                  :src="item.image"
                  fit="fill"
                  loading
                ></el-image>
              </div>
            </div>
            <!-- 剩余的图片 -->
            <div v-for="item in pic2.slice(3)" :key="item.id" class="remaining-images">
              <el-image
                @click="goToProductDetails(item.id)"
                style="width: 300px; height: 220px"
                :src="item.image"
                fit="fill"
                loading
              ></el-image>
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane label="女装" name="second">
          <div>
            <!-- 包裹前三张图片的容器 -->
            <div v-if="pic3.length > 0" class="image-row">
              <div v-for="item in pic3.slice(0, 3)" :key="item.id">
                <el-image
                  @click="goToProductDetails(item.id)"
                  style="width: 300px; height: 220px"
                  :src="item.image"
                  fit="fill"
                  loading
                ></el-image>
              </div>
            </div>
            <!-- 剩余的图片 -->
            <div v-for="item in pic3.slice(3)" :key="item.id" class="remaining-images">
              <el-image
                @click="goToProductDetails(item.id)"
                style="width: 300px; height: 220px"
                :src="item.image"
                fit="fill"
                loading
              ></el-image>
            </div>
          </div>
        </el-tab-pane>
        <el-tab-pane label="童装" name="third">
          <div>
            <!-- 包裹前三张图片的容器 -->
            <div v-if="pic1.length > 0" class="image-row">
              <div v-for="item in pic1.slice(0, 3)" :key="item.id">
                <el-image
                  @click="goToProductDetails(item.id)"
                  style="width: 300px; height: 220px"
                  :src="item.image"
                  fit="fill"
                  loading
                ></el-image>
              </div>
            </div>
            <!-- 剩余的图片 -->
            <div v-for="item in pic1.slice(3)" :key="item.id" class="remaining-images">
              <el-image
                @click="goToProductDetails(item.id)"
                style="width: 300px; height: 220px"
                :src="item.image"
                fit="fill"
                loading
              ></el-image>
            </div>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>

    <!-- 商品分类展示 -->
    <div class="category-grid">
      <!-- <div v-for="category in categories" :key="category.id" class="category-item">
        <img :src="category.image" :alt="category.name" @click="goToProductDetails(category.id)" style="cursor: pointer;">
      </div> -->
    </div>

    <!-- 精选商品 -->
    <section class="featured-products">
      <h2>Featured Products</h2>
      <div v-if="pic4.length > 0" class="image-row">
        <div v-for="item in pic4.slice(0, 5)" :key="item.id">
          <el-image
            @click="goToProductDetails(item.id)"
            style="width: 180px; height: 120px"
            :src="item.image"
            fit="fill"
            loading
          ></el-image>
        </div>
      </div>
    </section>

    <!-- 夏季精选 -->
    <section class="summer-essentials">
      <h2>Summer Essentials</h2>
      <button class="shop-now">Shop Now</button>
      <div class="product-grid">
        <product-card
          v-for="product in summerProducts"
          :key="product.id"
          :product="product"
          @click.native="goToProductDetails(product.id)"
        />
      </div>
    </section>
  </div>
</template>

<script>
import { ref } from 'vue';
import { useRouter } from 'vue-router'; // 引入 useRouter

const activeName = ref('first');

export default {
  name: 'HomePage',
  components: {
    // 假设 search-bar, login-register, product-card 是已注册的组件
    'search-bar': { template: '<div>Search Bar</div>' },
    'login-register': { template: '<div>Login/Register</div>' },
    'product-card': {
      props: ['product'],
      template: '<div class="product-card" @click="$emit(\'click\')">{{ product.name }}</div>',
    },
  },
  setup() {
    const router = useRouter(); // 获取 router 实例

    const productData = [
      { id: 1, image: 'http://img.alicdn.com/img/i2/4857989373/O1CN01KMc8lk2J6tKQiPdib_!!0-saturn_solar.jpg_.webp' },
      { id: 2, image: 'http://img.alicdn.com/img/i3/16718750/O1CN01rXAqgY2EVYZVbNEsR_!!0-saturn_solar.jpg_.webp' },
      { id: 3, image: 'http://img.alicdn.com/img/i2/44964746/O1CN01PTzKPn1kviip8a1aM_!!0-saturn_solar.jpg_.webp' },
      { id: 4, image: 'http://img.alicdn.com/img/i4/1200390117/O1CN01QXnSG61CjdKx3vbF8_!!0-saturn_solar.jpg_.webp' },
      { id: 5, image: 'http://img.alicdn.com/img/i2/27518121/O1CN01Sx63BI29rTRV9YnAr_!!0-saturn_solar.jpg_.webp' },
    ];

    const categoryData = [
      { id: 'c1', name: 'Category 1', image: '/category1.jpg' },
      { id: 'c2', name: 'Category 2', image: '/category2.jpg' },
      // ... more categories
    ];

    const pic1Data = [
      { id: 'child1', image: '/child/pic1.webp' },
      { id: 'child2', image: '/child/pic2.webp' },
      { id: 'child3', image: './child/pic3.webp' },
      { id: 'child4', image: '/child/pic4.webp' },
    ];
    const pic2Data = [
      { id: 'man1', image: '/man/p1.webp' },
      { id: 'man2', image: '/man/p2.webp' },
      { id: 'man3', image: '/man/p3.webp' },
      { id: 'man4', image: '/man/p4.webp' },
    ];
    const pic3Data = [
      { id: 'women1', image: '/women/pic1.webp' },
      { id: 'women2', image: '/women/pic2.webp' },
      { id: 'women3', image: '/women/pic3.webp' },
      { id: 'women4', image: '/women/pic4.webp' },
    ];
    const pic4Data = [
      { id: 'special1', image: '/special/p1.webp' },
      { id: 'special2', image: '/special/p2.webp' },
      { id: 'special3', image: '/special/p3.webp' },
      { id: 'special4', image: '/special/p4.webp' },
      { id: 'special5', image: '/special/p5.webp' },
    ];

    const featuredProductsData = [
      { id: 'fp1', name: 'Leather Jacket', price: 359, image: 'path/to/image' },
      // ... more featured products
    ];

    const summerProductsData = [
      { id: 'sp1', name: 'One-piece Swimsuit', price: 85, image: 'path/to/image' },
      // ... more summer products
    ];

    const goToProductDetails = (productId) => {
      console.log('Clicked product ID:', productId);
      router.push(`/productdetails/${productId}`);
    };

    const handleClick = (tab, event) => {
      console.log(tab, event);
    };

    return {
      activeName,
      product: productData,
      categories: categoryData,
      pic1: pic1Data,
      pic2: pic2Data,
      pic3: pic3Data,
      pic4: pic4Data,
      featuredProducts: featuredProductsData,
      summerProducts: summerProductsData,
      goToProductDetails,
      handleClick,
    };
  },
  methods: {
    addToCart(product) {
      // 添加到购物车逻辑
      this.$store.dispatch('cart/addItem', product);
    },
    addToWishlist(product) {
      // 添加到收藏夹逻辑
      this.$store.dispatch('wishlist/addItem', product);
    },
  },
};
</script>

<style scoped>
.e-commerce {
  max-width: 1200px;
  margin: 0 auto;
}

.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
}

.hero-banner {
  position: relative;
  height: 400px;
  background-size: cover;
  color: white;
  display: flex;
  flex-direction: column;
  justify-content: top;
  padding: 2rem;
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.category-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 1rem;
  padding: 2rem 0;
}

.product-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 2rem;
  padding: 2rem 0;
}

.shop-now {
  background: #6366f1;
  color: white;
  padding: 0.5rem 2rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}

.image-row {
  display: flex;
  justify-content: space-between; /* 根据需要调整图片间的间距 */
}

.remaining-images {
  margin-top: 10px; /* 添加上边距以分隔两行 */
}

.product-card {
  border: 1px solid #ccc;
  padding: 10px;
  text-align: center;
  cursor: pointer;
}
</style>