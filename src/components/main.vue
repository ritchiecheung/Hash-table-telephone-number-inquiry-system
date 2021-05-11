<template>
  <el-table v-loading="loading" :data="tableData" style="width: 100%">
    <el-table-column label="Key" prop="key"> </el-table-column>
    <el-table-column label="用户名" prop="name"> </el-table-column>
    <el-table-column label="手机号" prop="phoneNumber"> </el-table-column>
    <el-table-column label="家庭地址" prop="address"> </el-table-column>
    <el-table-column align="right">
      <template slot-scope="scope">
        <el-button size="mini" @click="goToUpdate(scope.row)"
        >修改</el-button
        >
        <el-button size="mini" type="danger" @click="del(scope.row)"
        >删除</el-button
        >
      </template>
    </el-table-column>
  </el-table>
</template>

<style>
body {
  margin: 0;
}
</style>

<script>
import axios from "axios";
export default {
  created() {
    axios.get("http://localhost:8001/findAll").then((res) => {
      console.log(res);
      this.tableData = res.data;
    });
  },
  data() {
    return {
      activeIndex: "1",
      tableData: null,
      loading: true,
      loading: setTimeout(() => {
        this.loading = false;
      }, 1000),
    };
  },

  methods: {
    del(row) {
      axios.delete("http://localhost:8001/delete/" + row.key).then((res) => {
        this.$alert("数据删除成功！", "消息", {
          confirmButtonText: "确定",
          callback: (action) => {
            window.location.reload();
          },
        });
      });
    },
    goToUpdate(row){
      this.$router.push({
        path:'/update',
        query: {
          key:row.key,
        }
      })
    }
  }
};
</script>
