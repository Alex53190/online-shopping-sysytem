<template>
  <div>
  <div>
  <div v-if="loading" style="padding-top: 30px; font-size: larger;">Loading product details...</div>
  <div v-else-if="error" style="padding-top: 60px; font-size: larger;">Error loading product details: {{ error }}</div>
  <div v-else-if="product">
    <h1>{{ product.name }}</h1>
    <img :src="product.image" alt="...">
    <p>Price: ${{ product.price }}</p>
    <p>Description: {{ product.description }}</p>
    <button @click="addToCart(product)">Add to Cart</button>
  </div>
  <div v-else style="padding-top: 30px;">
    Product not found.
  </div>
</div>
<div>
  <!-- Popup Layer for Cart -->
  <div v-if="showCart" class="cart-popup">
    <div class="cart-popup-content">
      <h2>Your Cart</h2>
      <button class="close-button" @click="closeCart">Close</button>
      <ul>
        <li v-for="item in cart.items" :key="item.id">
          <p>{{ item.name }} - ${{ item.price }} (Qty: {{ item.quantity }})</p>
        </li>
      </ul>
      <p v-if="cart.items.length === 0" style="padding-top: 30px; font-size: larger;">Your cart is empty.</p>
      <p v-else><strong>Total Amount:</strong> ${{ cart.totalAmount }}</p>
      <button v-if="cart.items.length > 0" @click="clearCart">Clear Cart</button>
    </div>
  </div>
</div>
</div>
</template>

<script>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';

export default {
  name: 'ProductDetails',
  setup() {
    const route = useRoute();
    const productId = ref(null);
    const product = ref(null);
    const loading = ref(true);
    const error = ref(null);

    // Cart state
    const cart = ref({
      items: [], // 用于保存购物车中的商品
      totalAmount: 0 // 用于保存总金额
    });
    const showCart = ref(false);

    onMounted(async () => {
      productId.value = route.params.id;
      loading.value = true;
      error.value = null;

      try {
        setTimeout(() => {
          const mockProducts = {
          '1': { id: 1, name: 'Awesome T-Shirt', price: 25, description: 'This is an awesome t-shirt.', image: 'http://img.alicdn.com/img/i2/4857989373/O1CN01KMc8lk2J6tKQiPdib_!!0-saturn_solar.jpg_.webp' },
            '2': { id: 2, name: 'down coat', price: 50, description: 'These are some down coat.', image: 'http://img.alicdn.com/img/i3/16718750/O1CN01rXAqgY2EVYZVbNEsR_!!0-saturn_solar.jpg_.webp'},
            '3': { id: 3, name: 'down jacket ', price: 75, description: 'These are down jacket.',  image: 'http://img.alicdn.com/img/i2/44964746/O1CN01PTzKPn1kviip8a1aM_!!0-saturn_solar.jpg_.webp' },
            '4': { id: 4, name: 'cool jacket ', price: 100, description: 'This is an cool jacket.',  image: 'http://img.alicdn.com/img/i4/1200390117/O1CN01QXnSG61CjdKx3vbF8_!!0-saturn_solar.jpg_.webp' },
            '5': { id: 5, name: 'Cozy Sweater', price: 40, description: 'This is a Cozy Sweater.', image: 'http://img.alicdn.com/img/i2/27518121/O1CN01Sx63BI29rTRV9YnAr_!!0-saturn_solar.jpg_.webp' },
            'man1':{id: 6, name: 'down coat', price: 400, description: 'This is a down coat.', image: '/man/p1.webp'},
            'women1':{id: 7, name: 'Cozy Sweater', price: 40, description: 'This is a Cozy Sweater.', image: '/women/pic1.webp'},
            'child1':{id: 8, name: 'down coat', price: 600, description: 'This is a down coat.', image: '/child/pic1.webp'},
            'special1':{id: 9, name: 'down coat', price: 500, description: 'This is a down coat.', image: '/special/p1.webp'},
          };

          if (mockProducts[productId.value]) {
            product.value = mockProducts[productId.value];
          } else {
            error.value = 'Product not found';
          }
          loading.value = false;
        }, 1000);
      } catch (err) {
        error.value = err.message || 'Failed to fetch product details';
        loading.value = false;
      }
    });

    const addToCart = (productToAdd) => {
      const existingProduct = cart.value.items.find(item => item.id === productToAdd.id);

      if (existingProduct) {
        existingProduct.quantity += 1;
      } else {
        cart.value.items.push({ ...productToAdd, quantity: 1 });
      }

      cart.value.totalAmount += productToAdd.price;
      showCart.value = true;
    };

    const closeCart = () => {
      showCart.value = false;
    };

    const clearCart = () => {
      cart.value.items = [];
      cart.value.totalAmount = 0;
    };

    return {
      productId,
      product,
      loading,
      error,
      addToCart,
      cart,
      showCart,
      closeCart,
      clearCart,
    };
  },
};
</script>

<style scoped>
img {
  max-width: 300px;
  height: auto;
}

.cart-popup {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.cart-popup-content {
  background: white;
  padding: 20px;
  border-radius: 8px;
  width: 80%;
  max-width: 500px;
  text-align: center;
}

.close-button {
  background: red;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
}

.close-button:hover {
  background: darkred;
}
</style>