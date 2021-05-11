<template>
  <div>
    用户名：<el-input v-model="input" placeholder="请输入内容" style="width:25%"></el-input>
    <el-button type="primary" style="margin-right:100px" @click="searchByName()">搜索</el-button>

    手机号：<el-input v-model="input1" placeholder="请输入内容" style="width:25%" ></el-input>
    <el-button type="primary" @click="searchByPhoneNumber()">搜索</el-button>

    <el-table
        :data="tableData"
        style="width: 100%"
    >
      <el-table-column label="Key" prop="key"> </el-table-column>
      <el-table-column label="用户名" prop="name"> </el-table-column>
      <el-table-column label="手机号" prop="phoneNumber"> </el-table-column>
      <el-table-column label="家庭地址" prop="address"> </el-table-column>

    </el-table>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      input:'',
      input1:'',
      tableData:null,
    }
  },

  methods:{
    searchByName() {
      axios.get('http://localhost:8001/findByName/' + this.input).then(res=>{
        this.tableData = res.data;
      });
    },
    searchByPhoneNumber() {
      axios.get('http://localhost:8001/findByPhoneNumber/' + this.input1).then(res=>{
        this.tableData = res.data;
      });
    }
  }
}
</script>
