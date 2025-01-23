import axios from 'axios';

// 设置基础API地址
const apiClient = axios.create({
  baseURL: 'http://localhost:8085/api',
  headers: {
    'Content-Type': 'application/json'
  
  }
});
// 用户登录 -C
export const userlogin = (data) => {
  return apiClient.post('/user/login', data);
};
// 用户注册 -C
export const userRegister = (data) => {
  return apiClient.post('/user/register', data);
};
//获取当前用户信息 -C
export const getUserRole = (username) => {
  return apiClient.get('/user/getRole',{params:{username}} );
};

// 获取商家（管理员）-M
export const getAllMerchants = () => {
  return apiClient.get('/admin/merchants');
};

//添加商家 -M
export const addMerchant = (newMerchant) => {
 return apiClient.post('/admin/merchants', newMerchant);
};

//删除商家 -M
export const deleteMerchant = (merchantId) => {
 return apiClient.delete(`/admin/merchants/${merchantId}`);
};

//查询分类 -M
export const getAllCategories = () => {
 return apiClient.get('/admin/categories');
};

//添加分类 -M
export const addCategory = (newCategory) => {
 return apiClient.post('/admin/categories', newCategory);
};

//删除分类 -M
export const deleteCategory = (categoryId) => {
 return apiClient.delete(`/admin/categories/${categoryId}`);
};

//查询公告 -M
export const getAllAnnouncements = () => {
 return apiClient.get('/admin/announcements');
};

// 添加公告 -M
export const addAnnouncement = (newAnnouncement) => {
 return apiClient.post('/admin/announcements', newAnnouncement);
};

//删除公告 -M
export const deleteAnnouncement = (announcementId) => {
 return apiClient.delete(`/admin/announcements/${announcementId}`);
};
//L_ 获取收藏
export const getFavoritesByUser= (userId)=> {
  return apiClient.get('/user/favorites',{params:{userId}});
};
//L_ 添加收藏
export const addFavorite =( favorite)=>{
  return apiClient.post('/user/favorites',favorite)
};
//L_根据商品ID获取商品信息
export const getProductById=(id) =>{
  return apiClient.post('user/products/getProduct',id)
};

// 获取商家商品 -l
export const getMerchantProducts = (merchantId) => {
  return apiClient.get('/merchant/products',{ params: { merchantId } });
};

// 添加商品 -l
export const addProduct = (product) => {
  return apiClient.post('/merchant/products',{product});
};


// 删除商品 -l
export const deleteProduct = (id) => {
  return apiClient.delete(`/merchant/products/${id}`);
};
//查询订单 -l
export const getOrdersByMerchant = (merchantId) => {
     return apiClient.get('/merchant/orders',{ params: { merchantId }});
   };

// 备注订单 -l
export const updateOrderRemark = (id, remark) => {
  return apiClient.put(`/merchant/orders/${id}/remark?remark=${encodeURIComponent(remark)}`);
};

  //订单状态更新 -l
  export const updateOrderStatus = (id, status) => {
    return apiClient.put(`/merchant/orders/${id}/status?status=${encodeURIComponent(status)}`);
  };




// // 获取商家商品
// export const getMerchantProducts = () => {
//   return apiClient.get('/merchant/products');
// };

// // 添加商品
// export const addProduct = (product) => {
//   return apiClient.post('/merchant/products/add', product);
// };

// 获取全部商品列表
export const getAllProducts = (categoryId) => {
  return apiClient.get(`/user/products`, { params: { categoryId } });
};

// // 获取商家列表（管理员）
// export const getMerchantList = () => {
//   return apiClient.get('/admin/merchants');
// };

// // 发布公告
// export const addAnnouncement = (content) => {
//   return apiClient.post('/admin/announcements/add', { content });
// };

// // 获取用户订单
// export const getUserOrders = () => {
//   return apiClient.get('/user/orders/my');
// };
//获取商家订单

export const getMerchantOrders = (id) => {
  return apiClient.get('/merchant/orders'),{ params: { id } };
};