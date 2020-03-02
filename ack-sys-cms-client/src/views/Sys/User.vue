<template>
  <div class="page-container">
	<!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="filters" :size="size">
        <el-form-item>
          <el-input v-model="filters.username" placeholder="登录名"></el-input>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" perms="sys:user:view" type="primary" @click="findPage(null)"/>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-plus" :label="$t('action.add')" perms="sys:user:add" type="primary" @click="handleAdd" />
        </el-form-item>
      </el-form>
    </div>
    <div class="toolbar" style="float:right;padding-top:10px;padding-right:15px;">
      <el-form :inline="true" :size="size">
        <el-form-item>
          <el-button-group>
          <el-tooltip content="刷新" placement="top">
            <el-button icon="fa fa-refresh" @click="findPage(null)"></el-button>
          </el-tooltip>
          <el-tooltip content="列显示" placement="top">
            <el-button icon="fa fa-filter" @click="displayFilterColumnsDialog"></el-button>
          </el-tooltip>
          <!-- <el-tooltip content="导出" placement="top">
            <el-button icon="fa fa-file-excel-o"></el-button>
          </el-tooltip> -->
          </el-button-group>
        </el-form-item>
      </el-form>
      <!--表格显示列界面-->
      <table-column-filter-dialog ref="tableColumnFilterDialog" :columns="columns"
        @handleFilterColumns="handleFilterColumns">
      </table-column-filter-dialog>
    </div>
    <!--表格内容栏-->
    <kt-table :height="350" :showBatchDelete="false"
      :data="pageResult" :columns="filterColumns" :btns="btns" :btnWidth="btnWidth"
       @handleAbleUser="handleAbleUser" @getHoverLable="getHoverLable"
       @findPage="findPage" @handleEdit="handleEdit" @handleDelete="handleDelete"
       @handleAuthorization="handleAuthorization" @handleRestPassword="handleRestPassword">
    </kt-table>
    <!--新增编辑界面-->
    <el-dialog :title="operation?'新增':'编辑'" width="40%"  style="text-align: left;"
               :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="dataForm" label-width="80px" :rules="dataFormRules" ref="dataForm" :size="size"
        label-position="right">
        <el-form-item label="ID" prop="id" v-if="false">
          <el-input v-model="dataForm.id" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="登录名" prop="username">
          <el-input v-model="dataForm.username" auto-complete="off" v-if="operation"></el-input>
          <el-input v-model="dataForm.username" auto-complete="off" v-else disabled></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" v-if="false">
          <el-input v-model="dataForm.password" auto-complete="off" :disabled="true"></el-input>
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
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :size="size" @click.native="dialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click.native="submitForm" :loading="editLoading">{{$t('action.submit')}}</el-button>
      </div>
    </el-dialog>
    <!-- 授权页面-->
    <el-dialog
      title="授权"
      :visible.sync="authDialog.visible"
      width="40%" style="text-align: left">
      <el-form :model="roleForm" label-width="80px" :rules="dataFormRules"
               ref="roleForm" :size="size">
        <el-form-item label="ID" prop="id" v-if="false">
          <el-input v-model="roleForm.id" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-transfer v-model="roleForm.userRoles"
            :titles="authDialog.titles"
            :data="roles"></el-transfer>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="authDialog.visible = false">{{$t('action.cancel')}}</el-button>
        <el-button type="primary" @click="submitRoleForm">{{$t('action.submit')}}</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import PopupTreeInput from "@/components/PopupTreeInput"
import KtTable from "@/views/Core/KtTable"
import KtButton from "@/views/Core/KtButton"
import TableColumnFilterDialog from "@/views/Core/TableColumnFilterDialog"
import { format,unixTimeFormat } from "@/utils/datetime"
export default {
	components:{
		PopupTreeInput,
		KtTable,
		KtButton,
		TableColumnFilterDialog
	},
	data() {
    const generateData = _ => {
      const data = [];
      for (let i = 1; i <= 15; i++) {
        data.push({
          key: i,
          label: '备选项 '+i,
          disabled: i % 4 === 0
        });
      }
      return data;
    };
		return {
			size: 'small',
			filters: {
				name: ''
			},
      authDialog :{
        titles:["所有角色","已有角色"],
        visible:false,
      },
      allRoles:generateData(),
			columns: [],
			filterColumns: [],
			pageRequest: { currentPage: 1, pageSize: 10 },
			pageResult: {},
      btnWidth : 460,
      btns: [
        {
          icon :"fa fa-adjust",
          label : "",
          perms : "sys:user:pwd",
          type : "primary",
          fun : "handleAbleUser",
          isDelete: false
        },
        {
          icon :"fa fa-key",
          label : "action.restPassword",
          perms : "sys:user:pwd",
          type : "info",
          fun : "handleRestPassword",
          isDelete: false
        },
        {
          icon :"fa fa-address-book-o",
          label : "action.auth",
          perms : "sys:user:auth",
          type : "warning",
          fun : "handleAuthorization",
          isDelete: false
        },
        {
          icon :"fa fa-edit",
          label : "action.edit",
          perms : "sys:user:edit",
          type : "",
          fun : "handleEdit",
          isDelete: false
        },
        {
          icon :"fa fa-trash",
          label : "action.delete",
          perms : "sys:user:delete",
          type : "danger",
          fun : "handleDelete",
          isDelete: true
        }
      ],/*所需按钮*/
			operation: false, // true:新增, false:编辑
			dialogVisible: false, // 新增编辑界面是否显示
      authDialogVisible: false, /* 授权默认不显示*/
			editLoading: false,
			dataFormRules: {
				username: [
					{ required: true, message: '请输入用户名', trigger: 'blur' }
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
      roleForm:{
			  id: 0,
        username:'',
        userRoles: []
      },
			// 新增编辑界面数据
			dataForm: {
				id: 0,
				username: '',
				password: '123456',
        newPassword: '123456',
        realName:'',
        sex:0,
        birthday:'',
				departmentId: 0,
				deptName: '',
        address:'xxxx',
				email: 'test@qq.com',
				mobile: '13889700023',
				status: 1,
				userRoles: []
			},
			deptData: [],
			deptTreeProps: {
				label: 'name',
				children: 'children'
			},
			roles: []
		}
	},
	methods: {
	  getHoverLable:function(data){
	    if(data === 1){
	      return "action.unLock"
      }
	    return "action.lock"
    },
    handleAbleUser:function(params){
	    let data = {}
	    data.id = params.row.id
      data.username = params.row.username
      data.state = -1
      if(params.row.state === 1 ){
        data.state = 0
      } else{
        data.state = 1
      }
      this.$api.user.ableUser(data).then((res) => {
        this.editLoading = false
        if(res.code == 200) {
          this.$message({ message: '操作成功', type: 'success' })
        } else {
          this.$message({message: '操作失败, ' + res.msg, type: 'error'})
        }
        this.findPage(null)
      })
    },
		// 获取分页数据
		findPage: function (data) {
			if(data !== null) {
				this.pageRequest = data.pageRequest
			}
			this.pageRequest.columnFilters = {username: {name:'username', value:this.filters.username}}
			this.$api.user.findPage(this.pageRequest).then((res) => {
				this.pageResult = res.data
			}).then(data!=null?data.callback:'')
		},
		// 加载用户角色信息
    findAllRoles: function () {
		  //清空当前roles
      this.roles = []
			this.$api.role.findAll().then((res) => {
				// 加载角色集合
        let obj = res.data
        for(let i = 0; i < obj.length; i++){
          let role = {}
          let key = obj[i].id
          let label= obj[i].name
          role.key = key
          role.label = label
          this.roles.push(role)
        }
			})
		},
    handleAuthorization :function(params){
		  //查询所有角色
      this.findAllRoles()
		  //查询当前用户所有角色
      let id = params.row.id
      this.roleForm.id = id
      let username = params.row.username
      this.roleForm.username = username
      if(id =="" || id == undefined || id == null){
        this.$message({message: '授权出错', type: 'error'})
        return
      }
      let data = {'id':id, 'username':username}
      this.$api.user.findUserRoles(data).then((res)=>{
        this.roleForm.userRoles = []
        let roles = res.data
        for(let i = 0 ; i < roles.length; i++){
          let roleId = roles[i].id
          this.roleForm.userRoles.push(roleId)
        }
      })

		  this.authDialog.visible = true

    },
    submitRoleForm: function(){
      let params = Object.assign({}, this.roleForm)
      let len = params.userRoles.length
      if(len <=0 ){
        this.$message({message: '用户角色不能为空' , type: 'error'})
        return
      }
      let userRoles = []
      for(let i=0; i < len; i++) {
        let userRole = {
          userId: params.id,
          roleId: params.userRoles[i]
        }
        userRoles.push(userRole)
      }
      params.userRoles = userRoles
      this.$api.user.grant(params).then((res) => {
        this.editLoading = false
        if(res.code == 200) {
          this.$message({ message: '操作成功', type: 'success' })
          this.dialogVisible = false
          this.$refs['roleForm'].resetFields()
        } else {
          this.$message({message: '操作失败, ' + res.msg, type: 'error'})
        }
        this.findPage(null)
      })
    },
    //重置密码
    handleRestPassword: function(params){
		  let realName = params.row.realName
      let msg = "是否将用户：<span style='color: red'>" + realName
        +"</span>的密码重置为:<span style='color: green'>123456</span>"
      this.$confirm(msg, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        dangerouslyUseHTMLString:true
      }).then(() => {
        let data = {}
        data.id = params.row.id
        data.username = params.row.username
        this.$api.user.restPassword(data).then((res) => {
          this.editLoading = false
          if(res.code == 200) {
            this.$message({ message: '操作成功', type: 'success' })
            this.dialogVisible = false
          } else {
            this.$message({message: '操作失败, ' + res.msg, type: 'error'})
          }
        })
      }).catch(() => {

      });
    },
		// 批量删除
		handleDelete: function (data) {
      let obj = {}
      if(data.length <= 0){
		    return
      }
      obj.id = data.params[0].id
			this.$api.user.deleteById(obj).then(data!=null?data.callback:'')
		},
		// 显示新增界面
		handleAdd: function () {
			this.dialogVisible = true
			this.operation = true
			this.dataForm = {
				id: 0,
				name: '',
        password:'123',
        newPassword:'123',
        sex:0,
        birthday:'',
				departmentId: 1,
				deptName: '',
        address:'',
				email: '',
				mobile: '',
				status: 1,
        deleteStatus: 0,
				userRoles: []
			}
		},
		// 显示编辑界面
		handleEdit: function (params) {
      this.operation = false
      this.dataForm = Object.assign({}, params.row)
			this.dialogVisible = true
		},
		// 编辑
		submitForm: function () {
			this.$refs.dataForm.validate((valid) => {
				if (valid) {
					this.$confirm('确认提交吗？', '提示', {}).then(() => {
						this.editLoading = true
						let params = Object.assign({}, this.dataForm)
            if(this.operation){
              this.$api.user.save(params).then((res) => {
                this.editLoading = false
                if(res.code == 200) {
                  this.$message({ message: '操作成功', type: 'success' })
                  this.dialogVisible = false
                  this.$refs['dataForm'].resetFields()
                } else {
                  this.$message({message: '操作失败, ' + res.msg, type: 'error'})
                }
                this.findPage(null)
              })
            } else{
              this.$api.user.edit(params).then((res) => {
                this.editLoading = false
                if(res.code == 200) {
                  this.$message({ message: '操作成功', type: 'success' })
                  this.dialogVisible = false
                  this.$refs['dataForm'].resetFields()
                } else {
                  this.$message({message: '操作失败, ' + res.msg, type: 'error'})
                }
                this.findPage(null)
              })
            }

					})
				}
			})
		},
		// 获取部门列表
		findDeptTree: function () {
			this.$api.dept.findDeptTree().then((res) => {
				this.deptData = res.data
			})
		},
		// 菜单树选中
    deptTreeCurrentChangeHandle (data, node) {
      this.dataForm.departmentId = data.id
      this.dataForm.deptName = data.name
		},
    //用户状态格式化
    userState: function(row, column, cellValue, index){
		  let val = row[column.property]
      var rt = ""
      if (val == 1){
        rt = "已锁定"
      }
      if(val == 0){
		    rt = "已激活"
      }
      return rt
    },
    userSex: function(row, column, cellValue, index){
      let val = row[column.property]
      var rt = ""
      if (val == 1){
        rt = "男"
      }
      if(val == 0){
        rt = "女"
      }
      if(val == 3){
        rt = "保密"
      }
      return rt
    },
		// 时间格式化
    dateFormat: function (row, column, cellValue, index){
      return unixTimeFormat(row[column.property],"yyyy-MM-dd hh:mm:ss")
    },
		// 处理表格列过滤显示
    displayFilterColumnsDialog: function () {
			this.$refs.tableColumnFilterDialog.setDialogVisible(true)
    },
		// 处理表格列过滤显示
    handleFilterColumns: function (data) {
			this.filterColumns = data.filterColumns
			this.$refs.tableColumnFilterDialog.setDialogVisible(false)
    },
		// 处理表格列过滤显示
    initColumns: function () {
		  this.columns = [
				{prop:"id", label:"ID", minWidth:50},
				{prop:"username", label:"登录名", minWidth:120},
        {prop:"realName", label:"姓名", minWidth:120},
        {prop:"sex", label:"性别", minWidth:120, formatter:this.userSex},
				{prop:"deptName", label:"机构", minWidth:120},
				//{prop:"roleNames", label:"角色", minWidth:100},
				{prop:"email", label:"邮箱", minWidth:120},
				{prop:"mobile", label:"手机", minWidth:100},
        {prop:"birthday", label:"出生日期", minWidth:100, formatter:this.dateFormat},
				{prop:"createTime", label:"创建时间", minWidth:120, formatter:this.dateFormat},
        //{prop:"creator", label:"创建人", minWidth:120},
        {prop:"state", label:"状态", minWidth:70, formatter:this.userState}
				// {prop:"lastUpdateBy", label:"更新人", minWidth:100},
				// {prop:"lastUpdateTime", label:"更新时间", minWidth:120, formatter:this.dateFormat}
			]
		  //this.filterColumns = JSON.parse(JSON.stringify(this.columns));
      this.filterColumns =  this.columns
     }
	},
	mounted() {
		this.findDeptTree()
		this.initColumns()
	}
}
</script>

<style scoped>

</style>
