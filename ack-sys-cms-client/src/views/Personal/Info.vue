<template>
    <div class="page-container">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form :model="dataForm" label-width="80px" class="data-form" :rules="dataFormRules" ref="dataForm" :size="size"
                   label-position="right">
            <el-form-item label="ID" prop="id" v-if="false">
              <el-input v-model="dataForm.id" :disabled="true" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="登录名" prop="username">
              <el-input v-model="dataForm.username" auto-complete="off" disabled></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="realName">
              <el-input v-model="dataForm.realName" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="sex" >
              <el-radio-group v-model="dataForm.sex" style="display: inline-flex;">
                <el-radio :label="0">女</el-radio>
                <el-radio :label="1">男</el-radio>
                <el-radio :label="2">保密</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="出生日期" prop="birthday" >
              <el-date-picker
                v-model="dataForm.birthday"
                type="datetime" style="display: flex"
                placeholder="选择日期时间">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="qq" prop="qq">
              <el-input v-model="dataForm.qq" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="机构" prop="deptName">
              <popup-tree-input
                :data="deptData"
                :props="deptTreeProps"
                :prop="dataForm.deptName"
                :nodeKey="''+dataForm.departmentId"
                :currentChangeHandle="deptTreeCurrentChangeHandle">
              </popup-tree-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="dataForm.email" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="手机" prop="mobile">
              <el-input v-model="dataForm.mobile" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="地址" prop="address">
              <el-input v-model="dataForm.address" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item style="align:left">
              <!--
              <el-button :size="size" @click.native="">{{$t('action.cancel')}}</el-button>
              -->
              <el-button :size="size" type="primary" @click.native="submitForm" :loading="editLoading">{{$t('action.submit')}}</el-button>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="12" >
          <el-upload
            class="pc-avatar-uploader"
            name = "avatarFile"
            :with-credentials = "true"
            :headers="avatar.headers"
            :action="avatar.action"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :on-error="handleAvatarError"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>

        </el-col>
      </el-row>

    </div>
</template>

<style scoped>
  .data-form {
    width: 80%;
    text-align: left;
  }
  .pc-avatar-uploader {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    width: 178px;
    height: 178px;
  }
  .pc-avatar-uploader:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>

<script>
  import PopupTreeInput from "@/components/PopupTreeInput"
  import { format,unixTimeFormat } from "@/utils/datetime"
  import { baseUrl } from '@/utils/global'
  import bus from '@/utils/bus'
  import Cookies from "js-cookie";
  export default {
    components:{
      PopupTreeInput,

    },
    name: "Info",
    data(){
      return {
        imageUrl: '',
        avatar:{
          action: '',
          headers:{}
        },

        size: 'small',
        filters: {
          name: ''
        },
        editLoading: false,
        dataFormRules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ],
          realName: [
            { required: true, message: '请输入姓名', trigger: 'blur' }
          ]
          ,
          email : [
            { required: true, message: '请输入邮箱', trigger: 'blur' }
          ]
          ,
          mobile: [
            { required: true, message: '请输入手机号', trigger: 'blur' }
          ]
        },
        // 新增编辑界面数据
        dataForm: {
          id: 0,
          username: '',
          password: '123456',
          realName:'',
          sex: 0,
          birthday: '',
          departmentId: 0,
          deptName: '',
          address:'',
          email: '',
          mobile: '',
          qq:'',
        },
        deptData: [],
        deptTreeProps: {
          label: 'name',
          children: 'children'
        }
      };
    },
    methods :{
      handleAvatarError(err){
        this.$message.error('上传头像图片失败')
      },
      handleAvatarSuccess(res, file) {
        if(!res.data){
          return
        }
        let avatarUrl = baseUrl.substring(0, baseUrl.length - 1) + res.data
        //更新缓存头像信息
        var userInfo = sessionStorage.getItem("userInfo")
        let user = JSON.parse(userInfo)
        user.avatar = res.data
        let jsonData = JSON.stringify(user)
        sessionStorage.setItem('userInfo', jsonData) // 保存用户到本地会话
        bus.$emit('avatarUrl', user)
        //this.imageUrl = URL.createObjectURL(file.raw)
        this.imageUrl =  avatarUrl
        this.$message({ message: '头像上传成功', type: 'success' })
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg'
        const isLt2M = file.size / 1024 / 1024 < 2

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!')
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!')
        }
        return isJPG && isLt2M
      },
      // 菜单树选中
      deptTreeCurrentChangeHandle (data, node) {
        this.dataForm.departmentId = data.id
        this.dataForm.deptName = data.name
      },
      // 获取部门列表
      findDeptTree: function () {
        this.$api.dept.findDeptTree().then((res) => {
          this.deptData = res.data
        })
      },
      // 编辑
      submitForm: function () {
        this.$refs.dataForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.editLoading = true
              let params = Object.assign({}, this.dataForm)
              this.$api.personal.edit(params).then((res) => {
                this.editLoading = false
                if(res.code == 200) {
                  this.$message({ message: '操作成功', type: 'success' })
                  this.dialogVisible = false
                  this.$refs['dataForm'].resetFields()
                } else {
                  this.$message({message: '操作失败, ' + res.msg, type: 'error'})
                }
                this.findUserByName()
              })
            }).catch(_ => {})
          }
        })
      },
      findUserByName:function(){
        let username = sessionStorage.getItem("user")
        let data = {"username" : username}
        //console.log(username)
        this.$api.personal.findUserByName(data).then((res) => {
         // console.log(res)
          let user = res.data
          this.imageUrl = baseUrl.substring(0, baseUrl.length - 1) + user.avatar
          this.dataForm.avatar = baseUrl.substring(0, baseUrl.length - 1) + user.avatar
          this.dataForm.id = user.id
          this.dataForm.username = user.username
          this.dataForm.realName = user.realName
          this.dataForm.sex = user.sex
          this.dataForm.birthday = user.birthday
          this.dataForm.departmentId = user.departmentId
          this.dataForm.deptName = user.department.name
          this.dataForm.address = user.address
          this.dataForm.email = user.email
          this.dataForm.qq = user.qq
          this.dataForm.mobile = user.mobile
        })
      },
      initAvatarData:function(){
        let url = baseUrl + "personal/uploadAvatar"
        this.avatar.action = url
        let token = Cookies.get("token")
        this.avatar.headers = {"token":token}
      },
    },

    created(){

    },
    beforeCreate () {
      //console.log('在实例初始化之前调用')
    },
    watch:{

    },
    mounted() {
      this.initAvatarData()
      this.findDeptTree()
      this.findUserByName()
    }
  }
</script>

