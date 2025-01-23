<template>
  <el-container class="layout-container-demo" style="height: 600px">
    <el-aside width="200px">
      <el-scrollbar>
        <el-menu :default-openeds="['1', '3', '4']">
          <el-sub-menu index="1">
            <template #title>
              <el-icon><message /></el-icon>商家信息管理
            </template>
            <el-menu-item-group>
              <template #title>操作</template>
              <el-menu-item index="1-1" @click="viewMerchants">查看商家信息</el-menu-item>
              <el-menu-item index="1-2" @click="addMerchant">添加商家</el-menu-item>
              <el-menu-item index="1-3" @click="deleteMerchant">删除商家</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="3">
            <template #title>
              <el-icon><icon-menu /></el-icon>商品分类管理
            </template>
            <el-menu-item-group>
              <template #title>操作</template>
              <el-menu-item index="3-1" @click="viewCategories">查看分类信息</el-menu-item>
              <el-menu-item index="3-2" @click="addCategory">添加分类</el-menu-item>
              <el-menu-item index="3-3" @click="deleteCategory">删除分类</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="4">
            <template #title>
              <el-icon><setting /></el-icon>公告管理
            </template>
            <el-menu-item-group>
              <template #title>操作</template>
              <el-menu-item index="4-1" @click="viewNotices">查看公告信息</el-menu-item>
              <el-menu-item index="4-2" @click="addNotice">发布公告</el-menu-item>
              <el-menu-item index="4-3" @click="deleteNotice">删除公告</el-menu-item>
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
          <span>管理员</span>
        </div>
      </el-header>

      <el-main>
        <el-scrollbar>
          <div v-if="activeTab ==='merchants'">
            <!-- 商家信息展示、添加、删除的相关表单和表格 -->
            <div v-if="viewMode === 'viewMerchants'">
              <h3>查看商家信息</h3>
              <el-table :data="merchantList">
                <el-table-column prop="id" label="商家ID" width="140" />
                <el-table-column prop="userId" label="用户ID" />
                <el-table-column prop="merchantName" label="商家名称" width="120" />
                <el-table-column prop="contactInfo" label="联系信息" />
                <el-table-column prop="status" label="状态" />
              </el-table>
            </div>
            <div v-if="viewMode === 'addMerchant'">
              <h3>添加商家</h3>
              <el-form :model="newMerchant">
                <el-form-item label="商家ID">
                  <el-input v-model="newMerchant.id" />
                </el-form-item>
                <el-form-item label="用户ID" :rules="[{ required: true, message: '用户ID不能为空', trigger: 'blur' }]">
                  <el-input v-model="newMerchant.user_id" />
                </el-form-item>
                <el-form-item label="商家名称">
                  <el-input v-model="newMerchant.merchant_name" />
                </el-form-item>
                <el-form-item label="联系信息">
                  <el-input v-model="newMerchant.contact_info" />
                </el-form-item>
                <el-form-item label="状态">
                  <el-select v-model="newMerchant.status">
                    <el-option label="PENDING" value="PENDING"></el-option>
                    <el-option label="APPROVED" value="APPROVED"></el-option>
                    <el-option label="REJECTED" value="REJECTED"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="saveNewMerchant">保存</el-button>
                </el-form-item>
              </el-form>
            </div>
            <div v-if="viewMode === 'deleteMerchant'">
              <h3>删除商家</h3>
              <el-input placeholder="输入商家ID" v-model="deleteMerchantId">
              </el-input>
              <el-button type="danger" @click="confirmDeleteMerchant">删除</el-button>
            </div>
          </div>
          <div v-if="activeTab === 'categories'">
            <!-- 商品分类信息展示、添加、删除的相关表单和表格 -->
            <div v-if="categoryViewMode === 'viewCategories'">
              <h3>查看分类信息</h3>
              <el-table :data="categoryList">
                <el-table-column prop="id" label="分类ID" width="140" />
                <el-table-column prop="name" label="分类名称" width="120" />
                <el-table-column prop="description" label="描述" />
              </el-table>
            </div>
            <div v-if="categoryViewMode === 'addCategory'">
              <h3>添加分类</h3>
              <el-form :model="newCategory">
                <el-form-item label="分类ID">
                  <el-input v-model="newCategory.id" />
                </el-form-item>
                <el-form-item label="分类名称">
                  <el-input v-model="newCategory.name" />
                </el-form-item>
                <el-form-item label="描述">
                  <el-input v-model="newCategory.description" />
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="saveNewCategory">保存</el-button>
                </el-form-item>
              </el-form>
            </div>
            <div v-if="categoryViewMode === 'deleteCategory'">
              <h3>删除分类</h3>
              <el-input placeholder="输入分类ID" v-model="deleteCategoryId">
              </el-input>
              <el-button type="danger" @click="confirmDeleteCategory">删除</el-button>
            </div>
          </div>
          <div v-if="activeTab === 'notices'">
            <!-- 公告信息展示、添加、删除的相关表单和表格 -->
            <div v-if="noticeViewMode === 'viewNotices'">
              <h3>查看公告信息</h3>
              <el-table :data="noticeList">
                <el-table-column prop="id" label="公告ID" width="140" />
                <el-table-column prop="content" label="公告内容" />
                <el-table-column prop="createTime" label="创建时间" />
              </el-table>
            </div>
            <div v-if="noticeViewMode === 'addNotice'">
              <h3>发布公告</h3>
              <el-form :model="newNotice">
                <el-form-item label="公告ID">
                  <el-input v-model="newNotice.id" />
                </el-form-item>
                <el-form-item label="公告内容">
                  <el-input v-model="newNotice.content" />
                </el-form-item>
                <el-form-item label="创建时间">
                  <el-input v-model="newNotice.createTime" />
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="saveNewNotice">保存</el-button>
                </el-form-item>
              </el-form>
            </div>
            <div v-if="noticeViewMode === 'deleteNotice'">
              <h3>删除公告</h3>
              <el-input placeholder="输入公告ID" v-model="deleteNoticeId">
              </el-input>
              <el-button type="danger" @click="confirmDeleteNotice">删除</el-button>
            </div>
          </div>
        </el-scrollbar>
      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import { Menu as IconMenu, Message, Setting } from '@element-plus/icons-vue';
import { ElMessage } from 'element-plus';
import {
  getAllMerchants,
  addMerchant as apiAddMerchant,
  deleteMerchant as apiDeleteMerchant,
  getAllCategories,
  addCategory as apiAddCategory,
  deleteCategory as apiDeleteCategory,
  getAllAnnouncements,
  addAnnouncement as apiAddAnnouncement,
  deleteAnnouncement as apiDeleteAnnouncement
} from '../../api/index'; // 请将 './yourApiFile.js' 修改为你实际的文件路径

// 商家列表相关
const merchantList = ref([]);
const newMerchant = ref({ id: 0, user_id: 0, merchant_name: '', contact_info: '', status: '' });
const viewMode = ref('viewMerchants');
const deleteMerchantId = ref('');
const activeTab = ref('merchants');

const viewMerchants = async () => {
  viewMode.value = 'viewMerchants';
  activeTab.value ='merchants';
  try {
    // 从后端获取商家列表数据
    const response = await getAllMerchants();
    console.log("Received response from getAllMerchants:", response); // 打印后端返回的数据
    merchantList.value = response.data;
  } catch (error) {
    ElMessage.error('获取商家列表失败');
  }
};
const addMerchant = async () => {
  viewMode.value = 'addMerchant';
  activeTab.value ='merchants';
};
const deleteMerchant = async () => {
  viewMode.value = 'deleteMerchant';
  activeTab.value ='merchants';
};

const saveNewMerchant = async () => {
  if (newMerchant.value.id && newMerchant.value.user_id && newMerchant.value.merchant_name && newMerchant.value.contact_info && newMerchant.value.status) {
    try {
      // 向后端添加新的商家数据
      const result = await apiAddMerchant(newMerchant.value);
      console.log("Result of adding merchant:", result); // 打印添加商家的结果
      newMerchant.value = { id: 0, user_id: 0, merchant_name: '', contact_info: '', status: '' };
      viewMode.value = 'viewMerchants';
      viewMerchants();
    } catch (error) {
      ElMessage.error('添加商家失败');
    }
  } else {
    ElMessage.error('请输入完整的商家信息');
  }
};

const confirmDeleteMerchant = async () => {
  const deleteId = Number(deleteMerchantId.value);
  if (!isNaN(deleteId) && deleteId > 0) {
    try {
      // 向后端发送删除商家的请求
      await apiDeleteMerchant(deleteId);
      deleteMerchantId.value = '';
      viewMode.value = 'viewMerchants';
      viewMerchants();
    } catch (error) {
      ElMessage.error('删除商家失败');
    }
  } else {
    ElMessage.error('请输入有效的正整数商家ID');
  }
};

// 商品分类相关
const categoryList = ref([]);
const newCategory = ref({ id: 0, name: '', description: '' });
const categoryViewMode = ref('viewCategories');
const deleteCategoryId = ref('');

const viewCategories = async () => {
  categoryViewMode.value = 'viewCategories';
  activeTab.value = 'categories';
  try {
    // 从后端获取商品分类列表数据
    const response = await getAllCategories();
    categoryList.value = response.data;
  } catch (error) {
    ElMessage.error('获取商品分类列表失败');
  }
};
const addCategory = async () => {
  categoryViewMode.value = 'addCategory';
  activeTab.value = 'categories';
};
const deleteCategory = async () => {
  categoryViewMode.value = 'deleteCategory';
  activeTab.value = 'categories';
};

const saveNewCategory = async () => {
  if (newCategory.value.id && newCategory.value.name && newCategory.value.description) {
    try {
      // 向后端添加新的商品分类数据
      await apiAddCategory(newCategory.value);
      newCategory.value = { id: 0, name: '', description: '' };
      categoryViewMode.value = 'viewCategories';
      viewCategories();
    } catch (error) {
      ElMessage.error('添加商品分类失败');
    }
  } else {
    ElMessage.error('请输入完整的分类信息');
  }
};

const confirmDeleteCategory = async () => {
  const deleteId = Number(deleteCategoryId.value);
  if (!isNaN(deleteId) && deleteId > 0) {
    try {
      // 向后端发送删除商品分类的请求
      await apiDeleteCategory(deleteId);
      deleteCategoryId.value = '';
      categoryViewMode.value = 'viewCategories';
      viewCategories();
    } catch (error) {
      ElMessage.error('删除商品分类失败');
    }
  } else {
    ElMessage.error('请输入有效的正整数分类ID');
  }
};

// 公告相关
const noticeList = ref([]);
const newNotice = ref({ id: 0, content: '', createTime: '' });
const noticeViewMode = ref('viewNotices');
const deleteNoticeId = ref('');

const viewNotices = async () => {
  noticeViewMode.value = 'viewNotices';
  activeTab.value = 'notices';
  try {
    // 从后端获取公告列表数据
    const response = await getAllAnnouncements();
    noticeList.value = response.data;
  } catch (error) {
    ElMessage.error('获取公告列表失败');
  }
};
const addNotice = async () => {
  noticeViewMode.value = 'addNotice';
  activeTab.value = 'notices';
};
const deleteNotice = async () => {
  noticeViewMode.value = 'deleteNotice';
  activeTab.value = 'notices';
};

const saveNewNotice = async () => {
  if (newNotice.value.id && newNotice.value.content && newNotice.value.createTime) {
    try {
      // 向后端添加新的公告数据
      await apiAddAnnouncement(newNotice.value);
      newNotice.value = { id: 0, content: '', createTime: '' };
      noticeViewMode.value = 'viewNotices';
      viewNotices();
    } catch (error) {
      ElMessage.error('发布公告失败');
    }
  } else {
    ElMessage.error('请输入完整的公告信息');
  }
};

const confirmDeleteNotice = async () => {
  const deleteId = Number(deleteNoticeId.value);
  if (!isNaN(deleteId) && deleteId > 0) {
    try {
      // 向后端发送删除公告的请求
      await apiDeleteAnnouncement(deleteId);
      deleteNoticeId.value = '';
      noticeViewMode.value = 'viewNotices';
      viewNotices();
    } catch (error) {
      ElMessage.error('删除公告失败');
    }
  } else {
    ElMessage.error('请输入有效的正整数公告ID');
  }
};
</script>

<style scoped>
.layout-container-demo.el-header {
  position: relative;
  background-color: var(--el-color-primary-light-7);
  color: var(--el-text-color-primary);
}
.layout-container-demo.el-aside {
  color: var(--el-text-color-primary);
  background: var(--el-color-primary-light-8);
}
.layout-container-demo.el-menu {
  border-right: none;
}
.layout-container-demo.el-main {
  padding: 0;
}
.layout-container-demo.toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}
</style>