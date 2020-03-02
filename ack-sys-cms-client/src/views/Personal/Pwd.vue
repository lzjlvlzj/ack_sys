<template>
    <div class="page-container">
      <el-form :model="dataForm" status-icon :rules="rules" :size="size" style="width: 40%;"
               ref="dataForm" label-width="100px" class="demo-dataForm">
        <el-form-item label="username" prop="username" v-if="false">
          <el-input v-model="dataForm.username" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="原密码" prop="password">
          <el-input type="password" v-model="dataForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input type="password" v-model="dataForm.newPassword" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认新密码" prop="checkPass">
          <el-input type="password" v-model="dataForm.checkPass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item style="align:left">
          <el-button :size="size" @click.native="resetForm('dataForm')">{{$t('action.reset')}}</el-button>
          <el-button :size="size" type="primary" @click.native="submitForm('dataForm')" :loading="editLoading">{{$t('action.submit')}}</el-button>
        </el-form-item>
      </el-form>
    </div>
</template>

<script>
  export default {
    data() {
      var validateOldPass = (rule, value, callback) => {
        if (value === '') {
          return callback(new Error('请输入原密码'));
        }else{
          callback();
        }
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.dataForm.checkPass !== '') {
            this.$refs.dataForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.dataForm.newPassword) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        size:"small",
        editLoading: false,
        dataForm: {
          username:'',
          newPassword: '',
          checkPass: '',
          password: ''
        },
        rules: {
          password: [
            { min: 1, max: 12, message: '长度在 1 到 12 个字符', trigger: 'blur' },
            { required: true, message: '请输入原密码', trigger: 'blur' },
            { validator: validateOldPass, trigger: 'blur' }
          ],
          newPassword: [
            { min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur' },
            { required: true, message: '请输入新密码', trigger: 'blur' },
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur' },
            { required: true, message: '请确认新密码', trigger: 'blur' },
            { validator: validatePass2, trigger: 'blur' }
          ]

        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.editLoading = true
              let params = Object.assign({}, this.dataForm)
              this.$api.personal.changePassword(params).then((res) => {
                this.editLoading = false
                if(res.code == 200) {
                  this.$message({ message: '操作成功', type: 'success' })
                  this.dialogVisible = false
                  this.$refs['dataForm'].resetFields()
                } else {
                  this.$message({message: '操作失败, ' + res.msg, type: 'error'})
                }
              })
            }).catch(_ => {})
          } else {
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      getUserName() {
        let username = sessionStorage.getItem("user")
        this.dataForm.username = username
      }

    },
    mounted() {
      this.getUserName()
    }
  }
</script>

<style scoped>

</style>
