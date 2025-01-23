<template>
  <el-container class="layout-container-demo" style="height: 600px">
    <el-aside width="200px">
      <el-scrollbar>
        <el-menu :default-openeds="['1', '3']">
          <el-sub-menu index="1">
            <template #title>
              <el-icon><message /></el-icon>商品信息管理
            </template>
            <el-menu-item-group>
              <template #title>操作</template>
              <el-menu-item index="1-1" @click="viewGoods">查看商品信息</el-menu-item>
              <el-menu-item index="1-2" @click="addGoods">添加商品</el-menu-item>
              <el-menu-item index="1-3" @click="deleteGoods">删除商品</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="2">
            <template #title>
              <el-icon><icon-menu /></el-icon>订单管理
            </template>
            <el-menu-item-group>
              <template #title>操作</template>
              <el-menu-item index="2-1" @click="viewOrders">查看订单信息</el-menu-item>
              <el-menu-item index="2-2" @click="addOrderNote">订单备注</el-menu-item>
              <el-menu-item index="2-3" @click="updateOrderStatus">更新订单状态</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
        </el-menu>
      </el-scrollbar>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <div class="toolbar">
          <el-dropdown>
            <el-icon style="margin-right: 8px; margin-top: 1px">
              <setting />
            </el-icon>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>系统设置</el-dropdown-item>
                <el-dropdown-item>退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
          <span>商家</span>
        </div>
      </el-header>

      <el-main>
        <el-scrollbar>
          <div v-if="activeTab === 'goods'">
            <!-- 商品信息展示、添加、删除的相关表单和表格 -->
            <div v-if="viewMode === 'viewGoods'">
              <h3>查看商品信息</h3>
              <el-table :data="goodsList">
                <el-table-column prop="id" label="商品ID" width="140" />
                <el-table-column prop="name" label="商品名称" width="120" />
                <el-table-column prop="price" label="价格" />
                <el-table-column prop="stock" label="库存" />
                <el-table-column prop="description" label="商品描述" />
                <!-- 删除创建时间列 -->
              </el-table>
            </div>
            <div v-if="viewMode === 'addGoods'">
              <h3>添加商品</h3>
              <el-form :model="newGoods">
                <el-form-item label="商品ID">
                  <el-input v-model="newGoods.id" />
                </el-form-item>
                <el-form-item label="商品名称">
                  <el-input v-model="newGoods.name" />
                </el-form-item>
                <el-form-item label="价格">
                  <el-input v-model="newGoods.price" />
                </el-form-item>
                <el-form-item label="库存">
                  <el-input v-model="newGoods.stock" />
                </el-form-item>
                <el-form-item label="商品描述">
                  <el-input v-model="newGoods.description" />
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="saveNewGoods">保存</el-button>
                </el-form-item>
              </el-form>
            </div>
            <div v-if="viewMode === 'deleteGoods'">
              <h3>删除商品</h3>
              <el-input placeholder="输入商品ID" v-model="deleteGoodsId">
              </el-input>
              <el-button type="danger" @click="confirmDeleteGoods">删除</el-button>
            </div>
          </div>
          <div v-if="activeTab === 'orders'">
            <!-- 订单信息展示、备注、发货的相关表单和表格 -->
            <div v-if="orderViewMode === 'viewOrders'">
              <h3>查看订单信息</h3>
              <el-table :data="orderList">
                <el-table-column prop="id" label="订单ID" width="140" />
                <!-- 删除总价列 -->
                <el-table-column prop="status" label="订单状态" />
                <el-table-column prop="remark" label="备注" />
                <!-- 删除创建时间列 -->
              </el-table>
            </div>
            <div v-if="orderViewMode === 'addOrderNote'">
              <h3>订单备注</h3>
              <el-input placeholder="输入订单ID" v-model="orderNoteId">
              </el-input>
              <el-input placeholder="输入订单备注" v-model="newOrderNote">
              </el-input>
              <el-button type="primary" @click="saveOrderNote">保存</el-button>
            </div>
            <div v-if="orderViewMode === 'updateOrderStatus'">
    <h3>更新订单状态</h3>
    <el-input placeholder="输入订单ID" v-model="orderStatusId"></el-input>
    <el-select v-model="newOrderStatus">
      <el-option label="待处理" value="PENDING"></el-option>
      <el-option label="已发货" value="SHIPPED"></el-option>
      <el-option label="已完成" value="COMPLETED"></el-option>
    </el-select>
    <el-button type="primary" @click="saveOrderStatus">更新</el-button>
  </div>
          </div>
        </el-scrollbar>
      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import { ElMessage } from 'element-plus';
import { getMerchantProducts, addProduct, deleteProduct, getOrdersByMerchant, updateOrderRemark, updateOrderStatus as apiUpdateOrderStatus } from '../../api/index'; // 使用别名导入
import Decimal from 'decimal.js';

interface Good {
  id: number;
  merchant_id: number;
  category_id: number;
  name: string;
  price: Decimal;
  stock: number;
  description: string;
  // create_time: string; // 删除创建时间属性
}

interface Order {
  id: number;
  user_id: number;
  merchant_id: number;
  // total_price: number; // 删除总价属性
  status: string;
  remark: string;
  // create_time: string; // 删除创建时间属性
}

const goodsList = ref<Good[]>([]);
const newGoods = ref<Good>({ id: 0, merchant_id: 1, category_id: 1, name: '', price: new Decimal(0.2), stock: 0, description: '' });
const viewMode = ref('viewGoods');
const deleteGoodsId = ref('');

const orderList = ref<Order[]>([]);
const newOrderNote = ref('');
const orderViewMode = ref('viewOrders');
const activeTab = ref('goods');
const orderNoteId = ref('');
const newOrderStatus = ref('待发货');
const orderStatusId = ref('');

const merchantId = ref(1); // 商家 ID

const viewGoods = async () => {
  try {
    const response = await getMerchantProducts(merchantId.value);
    goodsList.value = response.data;
  } catch (error) {
    ElMessage.error('获取商品信息失败');
  }
  viewMode.value = 'viewGoods';
  activeTab.value = 'goods';
};

const addGoods = async () => {
  viewMode.value = 'addGoods';
  activeTab.value = 'goods';
};

const deleteGoods = async () => {
  viewMode.value = 'deleteGoods';
  activeTab.value = 'goods';
};

const saveNewGoods = async () => {
  try {
    const response = await addProduct(newGoods.value);
    console.log(response);
    ElMessage.success('商品添加成功');
    await viewGoods();
  } catch (error) {
    ElMessage.error('商品添加失败');
  }
};

const confirmDeleteGoods = async () => {
  try {
    await deleteProduct(deleteGoodsId.value);
    ElMessage.success('商品删除成功');
    await viewGoods();
  } catch (error) {
    ElMessage.error('商品删除失败');
  }
};

const viewOrders = async () => {
  try {
    const response = await getOrdersByMerchant(merchantId.value);
    orderList.value = response.data;
  } catch (error) {
    ElMessage.error('获取订单信息失败');
  }
  orderViewMode.value = 'viewOrders';
  activeTab.value = 'orders';
};

const addOrderNote = async () => {
  orderViewMode.value ='addOrderNote';
  activeTab.value = 'orders';
};

const saveOrderNote = async () => {
try {
await updateOrderRemark(orderNoteId.value, newOrderNote.value);
ElMessage.success('订单备注更新成功');
await viewOrders();
} catch (error) {
ElMessage.error('订单备注更新失败');
}
};

const updateOrderStatus = async () => {
orderViewMode.value = 'updateOrderStatus';
activeTab.value = 'orders';
};

const saveOrderStatus = async () => {
  try {
    await apiUpdateOrderStatus(orderStatusId.value, newOrderStatus.value);
    ElMessage.success('订单状态更新成功');
    await viewOrders();
  } catch (error) {
    ElMessage.error('订单状态更新失败');
  }
};
</script>