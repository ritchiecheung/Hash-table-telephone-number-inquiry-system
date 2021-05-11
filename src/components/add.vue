<template>
  <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
  >
    <el-form-item label="用户名" prop="name">
      <el-input v-model="ruleForm.name"></el-input>
    </el-form-item>
    <el-form-item label="手机号" prop="phoneNumber">
      <el-input v-model="ruleForm.phoneNumber"></el-input>
    </el-form-item>
    <el-form-item label="家庭地址" prop="address">
      <el-input v-model="ruleForm.address"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')"
      >添加数据</el-button
      >
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import axios from "axios";
export default {
  data() {
    const checkPhone = (rule, value, callback) => {
      const age= /^[0-9]*$/
      if (!age.test(value)) {
        callback(new Error('手机号码格式不正确'))
      }else{
        callback()
      }
    }
    return {
      ruleForm: {},
      rules: {
        name: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          { min: 2, max: 4, message: "长度应在 2 到 4 个字符之间", trigger: "blur" },
        ],
        phoneNumber: [
          { required: true, validator:checkPhone, trigger: "blur" },
          {min:11,max:11,message:"手机号码长度应为11",trigger:"blur"},
        ],
        address: [{ required: true, message: "请输入地址", trigger: "blur" }],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const url = "http://localhost:8001/save";
          var params = new URLSearchParams();
          params.append("name", this.ruleForm.name);
          params.append("phoneNumber", this.ruleForm.phoneNumber);
          params.append("address", this.ruleForm.address);
          this.$axios({
            method: "post",
            url: url,
            data: params,
          }).then((result) => {
            console.log(result.data);
            if (result.data == "success") {
              this.$alert("数据添加成功！", "消息", {
                confirmButtonText: "确定",
                callback: action => {
                  this.$router.push('/');
                },
              });
            }else{
              this.$alert("添加的数据已存在!","消息",{
                confirmButtonText:"确定",
                callback: action => {
                  this.ruleForm.name = '';
                  this.ruleForm.phoneNumber = '';
                  this.ruleForm.address = '';
                }
              })
            }
          });
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },

};
</script>
