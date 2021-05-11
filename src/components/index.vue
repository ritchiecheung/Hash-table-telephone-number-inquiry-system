<template>
  <el-container style="height: 800px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu router :default-openeds="['1']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-menu"></i>菜单</template>
          <el-menu-item index="/main" class="el-icon-house">首页</el-menu-item>
          <el-menu-item index="/add" class="el-icon-circle-plus-outline">添加数据</el-menu-item>
          <el-menu-item index="/search" class="el-icon-search">查询数据</el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      activeIndex: "1",
      tableData: null,
    };
  },
};
</script>
