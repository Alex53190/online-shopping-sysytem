<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
    @select="handleSelect"
  >
    <el-menu-item index="0">
      <img
        style="width: 200px"
        src="/logo.png"
        alt="Element logo"
      />
    </el-menu-item>
    <el-menu-item index="1">
      <el-link :icon="Promotion" href="/login">Sign in</el-link> 
    
    </el-menu-item>
    <el-sub-menu index="2">
      <template #title>About us</template>
      <el-menu-item index="2-1">
        <el-link :icon="HomeFilled" href="/">Back Home</el-link> 
      </el-menu-item>
      <el-menu-item index="2-2">
        <el-link :icon="UserFilled" href="/user">Back Personal Page</el-link>
       
      </el-menu-item>
      <el-menu-item index="2-3">item three</el-menu-item>
      <el-sub-menu index="2-4">
        <template #title>item four</template>
        <el-menu-item index="2-4-1">item one</el-menu-item>
        <el-menu-item index="2-4-2">item two</el-menu-item>
        <el-menu-item index="2-4-3">item three</el-menu-item>
      </el-sub-menu>
    </el-sub-menu>
  </el-menu>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { Edit, View as IconView,Promotion,HomeFilled ,UserFilled} from '@element-plus/icons-vue'
const activeIndex = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

</script>

<style>
.el-menu--horizontal > .el-menu-item:nth-child(1) {
  margin-right: auto;
}
.el-link {
  margin-right: 8px;
}
</style>