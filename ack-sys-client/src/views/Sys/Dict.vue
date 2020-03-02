<template>
  <div class="page-container">
	<!--工具栏-->
	<div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
		<el-form :inline="true" :model="filters" :size="size">
			<el-form-item>
				<el-input v-model="filters.label" placeholder="名称"></el-input>
			</el-form-item>
			<el-form-item>
				<kt-button icon="fa fa-search" :label="$t('action.search')" perms="sys:dict:view" type="primary" @click="findPage(null)"/>
			</el-form-item>
			<el-form-item>
				<kt-button icon="fa fa-plus" :label="$t('action.add')" perms="sys:dict:add" type="primary" @click="handleAdd" />
			</el-form-item>
		</el-form>
	</div>
	<!--表格内容栏-->
	<kt-table :height="350" permsEdit="sys:dict:edit" permsDelete="sys:dict:delete"
		:data="pageResult" :columns="columns" :btns="btns" :btnWidth="btnWidth" :showBatchDelete="false"
		@findPage="findPage" @handleEdit="handleEdit" @handleDelete="handleDelete">
	</kt-table>
	<!--新增编辑界面-->
	<el-dialog :title="operation?'新增':'编辑'" width="40%" style="text-align: left" :visible.sync="editDialogVisible" :close-on-click-modal="false">
		<el-form :model="dataForm" label-width="80px" :rules="dataFormRules" ref="dataForm" :size="size">
			<el-form-item label="ID" prop="id"  v-if="false">
				<el-input v-model="dataForm.id" :disabled="true" auto-complete="off"></el-input>
			</el-form-item>
			<el-form-item label="名称" prop="key">
				<el-input v-model="dataForm.key" auto-complete="off" v-if="operation"></el-input>
        <el-input v-model="dataForm.key" auto-complete="off" :disabled="true" v-else="operation"></el-input>
			</el-form-item>
			<el-form-item label="值" prop="value">
				<el-input v-model="dataForm.value" auto-complete="off"></el-input>
			</el-form-item>
			<el-form-item label="类型" prop="type">
				<el-input v-model="dataForm.type" auto-complete="off"></el-input>
			</el-form-item>
			<el-form-item label="备注" prop="remarks">
				<el-input v-model="dataForm.remark" auto-complete="off" type="textarea"></el-input>
			</el-form-item>
		</el-form>
		<div slot="footer" class="dialog-footer">
			<el-button :size="size" @click.native="editDialogVisible = false">{{$t('action.cancel')}}</el-button>
			<el-button :size="size" type="primary" @click.native="submitForm" :loading="editLoading">{{$t('action.submit')}}</el-button>
		</div>
	</el-dialog>
  </div>
</template>

<script>
import KtTable from "@/views/Core/KtTable"
import KtButton from "@/views/Core/KtButton"
import { format } from "@/utils/datetime"
export default {
	components:{
			KtTable,
			KtButton
	},
	data() {
		return {
			size: 'small',
			filters: {
				key: ''
			},
      btnWidth : 400,
      btns:[
        {
          icon :"fa fa-edit",
          label : "action.edit",
          perms : "sys:dict:edit",
          type : "",
          fun : "handleEdit",
          isDelete: false
        },
        {
          icon :"fa fa-trash",
          label : "action.delete",
          perms : "sys:dict:delete",
          type : "danger",
          fun : "handleDelete",
          isDelete: true
        }
      ],
			columns: [
				{prop:"id", label:"ID", minWidth:50},
				{prop:"key", label:"名称", minWidth:100},
				{prop:"value", label:"值", minWidth:100},
				{prop:"type", label:"类型", minWidth:80},
				{prop:"remark", label:"备注", minWidth:120},
				{prop:"creator", label:"创建人", minWidth:100},
				{prop:"createTime", label:"创建时间", minWidth:120, formatter:this.dateFormat}
				// {prop:"lastUpdateBy", label:"更新人", minWidth:100},
				// {prop:"lastUpdateTime", label:"更新时间", minWidth:120, formatter:this.dateFormat}
			],
			pageRequest: { pageNum: 1, pageSize: 10 },
			pageResult: {},

			operation: false, // true:新增, false:编辑
			editDialogVisible: false, // 新增编辑界面是否显示
			editLoading: false,
			dataFormRules: {
				key: [
					{ required: true, message: '请输入名称', trigger: 'blur' }
				],
        value: [
          { required: true, message: '请输入值', trigger: 'blur' }
        ]
			},
			// 新增编辑界面数据
			dataForm: {
				id: 0,
				key: '',
				value: '',
				type: '',
				remark: ''
			}
		}
	},
	methods: {
		// 获取分页数据
		findPage: function (data) {
			if(data !== null) {
				this.pageRequest = data.pageRequest
			}
			this.pageRequest.columnFilters = {label: {name:'label', value:this.filters.label}}
			this.$api.dict.findPage(this.pageRequest).then((res) => {
				this.pageResult = res.data
			}).then(data!=null?data.callback:'')
		},
		// 批量删除
		handleDelete: function (data) {
      let obj = {}
      if(data.length <= 0){
        return
      }
      obj.id = data.params[0].id
      obj.key = data.params[0].key
			this.$api.dict.deleteById(obj).then(data!=null?data.callback:'')
		},
		// 显示新增界面
		handleAdd: function () {
			this.editDialogVisible = true
			this.operation = true
			this.dataForm = {
				id: 0,
				label: '',
				value: '',
				type: 1,
				remark: ''
			}
		},
		// 显示编辑界面
		handleEdit: function (params) {
			this.editDialogVisible = true
			this.operation = false
			this.dataForm = Object.assign({}, params.row)
		},
		// 编辑
		submitForm: function () {
			this.$refs.dataForm.validate((valid) => {
        if(this.operation){
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.editLoading = true
              let params = Object.assign({}, this.dataForm)
              this.$api.dict.save(params).then((res) => {
                if(res.code == 200) {
                  this.$message({ message: '操作成功', type: 'success' })
                } else {
                  this.$message({message: '操作失败, ' + res.msg, type: 'error'})
                }
                this.editLoading = false
                this.$refs['dataForm'].resetFields()
                this.editDialogVisible = false
                this.findPage(null)
              })
            })
          }
        }else{
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.editLoading = true
              let params = Object.assign({}, this.dataForm)
              this.$api.dict.edit(params).then((res) => {
                if(res.code == 200) {
                  this.$message({ message: '操作成功', type: 'success' })
                } else {
                  this.$message({message: '操作失败, ' + res.msg, type: 'error'})
                }
                this.editLoading = false
                this.$refs['dataForm'].resetFields()
                this.editDialogVisible = false
                this.findPage(null)
              })
            })
          }
        }

			})
		},
		// 时间格式化
      	dateFormat: function (row, column, cellValue, index){
          	return format(row[column.property])
      	}
	},
	mounted() {
	}
}
</script>

<style scoped>

</style>
