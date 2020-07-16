<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="filters" :size="size">
        <!--
        <el-form-item>
          <el-input v-model="filters.name" placeholder="名称"></el-input>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" perms="sys:dept:view" type="primary" @click="findTreeData(null)"/>
        </el-form-item>
        -->
        <el-form-item>
          <kt-button icon="fa fa-plus" :label="$t('action.add')" perms="sys:dept:add" type="primary"
                     @click="handleAdd"/>
        </el-form-item>
      </el-form>
    </div>
    <!--表格树内容栏-->
    <el-table
      :data="tableTreeDdata"
      style="width: 100%;margin-bottom: 20px;"
      row-key="id"
      stripe size="mini"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column
        prop="id" header-align="center" align="center" width="80" label="ID">
      </el-table-column>
      <el-table-column
        prop="name"
        label="名称"
        sortable
        width="180">
      </el-table-column>
      <el-table-column
        prop="parentName" header-align="center" align="center" width="120" label="上级机构">
      </el-table-column>
      <el-table-column
        prop="type" header-align="center" align="center" label="类型" :formatter="getType">
      </el-table-column>
      <el-table-column
        prop="img" header-align="center" align="center" label="背景大图" :formatter="getImg">
      </el-table-column>
      <el-table-column
        prop="creator" header-align="center" align="center" label="创建人" :formatter="getCreateName">
      </el-table-column>

      <el-table-column
        prop="createTime" header-align="center" align="center" label="创建时间" :formatter="dateFormat">
      </el-table-column>
      <el-table-column
        fixed="right" header-align="center" align="center" width="185" :label="$t('action.operation')">
        <template slot-scope="scope">
          <kt-button icon="fa fa-edit" :label="$t('action.edit')" perms="sys:dept:edit" @click="handleEdit(scope.row)"/>
          <kt-button icon="fa fa-trash" :label="$t('action.delete')" perms="sys:dept:delete" type="danger"
                     @click="handleDelete(scope.row)"/>
        </template>
      </el-table-column>
    </el-table>

    <!-- 新增修改界面 -->
    <el-dialog :title="!dataForm.id ? '新增' : '修改'" width="40%" :visible.sync="dialogVisible"
               :close-on-click-modal="false">
      <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="submitForm()"
               label-width="80px" :size="size" style="text-align:left;">
        <el-form-item label="名称" prop="name">
          <el-input v-model="dataForm.name" placeholder="名称"></el-input>
        </el-form-item>
        <el-form-item label="上级机构" prop="parentName">
          <popup-tree-input
            :data="popupTreeData" :props="popupTreeProps" :prop="dataForm.parentName==null?'顶级菜单':dataForm.parentName"
            :nodeKey="''+dataForm.parentId" :currentChangeHandle="handleTreeSelectChange">
          </popup-tree-input>
        </el-form-item>
        <el-form-item label="类型" prop="图标">
          <el-select v-model="dataForm.type" placeholder="请选择类型">
            <el-option
              v-for="sub in options"
              :key="sub.value"
              :label="sub.label"
              :value="sub.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="小图标" prop="icon">
          <el-input v-model="dataForm.icon" placeholder="小图标"></el-input>
        </el-form-item>
        <el-form-item label="大图标" prop="img">
          <el-input v-model="dataForm.img" placeholder="大图图标" v-if="imgShow"></el-input>
          <el-upload
            class="upload-demo"
            ref="upload"
            :multiple="uploadFile.multiple"
            :limit="uploadFile.limit"
            :with-credentials="uploadFile.withCredentials"
            :headers="uploadFile.headers"
            :action="uploadFile.url"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :on-success="handleSuccess"
            :file-list="fileList"
            :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item v-if="dataForm.type !== 2" label="排序编号" prop="orderNum">
          <el-input-number v-model="dataForm.orderNum" controls-position="right" :min="0"
                           label="排序编号"></el-input-number>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button :size="size" @click="dialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="primary" @click="submitForm()">{{$t('action.comfirm')}}</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import KtButton from "@/views/Core/KtButton"
  import TableTreeColumn from '@/views/Core/TableTreeColumn'
  import PopupTreeInput from "@/components/PopupTreeInput"
  import FaIconTooltip from "@/components/FaIconTooltip"
  import {format} from "@/utils/datetime"
  import {baseUrl} from '@/utils/global'
  import Cookies from "js-cookie";

  export default {
    components: {
      PopupTreeInput,
      KtButton,
      TableTreeColumn,
      FaIconTooltip
    },
    data() {
      return {
        size: 'small',
        loading: false,
        imgShow: false,
        uploadFile: {
          url: '',
          withCredentials: true,
          multiple: false,
          limit: 1
        },
        fileList: [],
        filters: {
          name: ''
        },
        tableTreeDdata: [],
        dialogVisible: false,
        options: [
          {
            value: 0,
            label: '后台显示'
          }, {
            value: 1,
            label: "内科"
          }, {
            value: 2,
            label: '外科'
          }, {
            value: 3,
            label: '医技'
          },
        ],
        dataForm: {
          id: 0,
          name: '',
          icon: '',
          type: '',
          img: '',
          parentId: 0,
          parentName: '',
          orderNum: 0,

        },
        dataRule: {
          name: [
            {required: true, message: '机构名称不能为空', trigger: 'blur'}
          ],
          type:[
            {required: true, message: '类型不能为空', trigger: 'blur'}
          ],
          img: [
            {required: true, message: '大图不能为空', trigger: 'blur'}
          ],
          parentName: [
            {required: true, message: '上级机构不能为空', trigger: 'change'}
          ]
        },
        popupTreeData: [],
        popupTreeProps: {
          label: 'name',
          children: 'children'
        }
      }
    },
    methods: {
      // 获取数据
      findTreeData: function () {
        this.loading = true
        this.$api.dept.findDeptTree().then((res) => {
          this.tableTreeDdata = res.data
          this.popupTreeData = this.getParentMenuTree(res.data)
          this.loading = false
        })
      },
      // 获取上级机构树
      getParentMenuTree: function (tableTreeDdata) {
        let parent = {
          parentId: 0,
          name: '顶级菜单',
          children: tableTreeDdata
        }
        return [parent]
      },
      getType:function(row, column, cellValue, index){
        let type = row.type
        console.log("type======")
        console.log(type)
        let rt = ""
        if(type == 0){
          rt = "后台显示"
        }
        if(type == 1){
          rt = "内科"
        }
        if(type == 2){
          rt = "外科"
        }
        if(type == 3){
          rt = "医技"
        }

        return rt
      },
      getImg:function(row, column, cellValue, index){
        return row.img
      },
      //用户状态格式化
      getCreateName: function (row, column, cellValue, index) {

        return row.createName
      },
      // 显示新增界面
      handleAdd: function () {
        this.dialogVisible = true
        this.dataForm = {
          id: 0,
          name: '',
          parentId: 0,
          parentName: '',
          orderNum: 0
        }
      },
      // 显示编辑界面
      handleEdit: function (row) {
        this.dialogVisible = true
        Object.assign(this.dataForm, row);
      },
      // 删除
      handleDelete(row) {
        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        }).then(() => {
          let params = this.getDeleteIds([], row)
          this.$api.dept.batchDelete(params).then(res => {
            this.findTreeData()
            this.$message({message: '删除成功', type: 'success'})
          })
        })
      },
      // 获取删除的包含子机构的id列表
      getDeleteIds(ids, row) {
        ids.push({id: row.id})
        if (row.children != null) {
          for (let i = 0, len = row.children.length; i < len; i++) {
            this.getDeleteIds(ids, row.children[i])
          }
        }
        return ids
      },
      // 机构树选中
      handleTreeSelectChange(data, node) {
        this.dataForm.parentId = data.id
        this.dataForm.parentName = data.name
      },
      /*---上传图片---*/
      handleRemove(file, fileList) {
        //console.log(file, fileList);
      },
      handlePreview(file) {
        //console.log(file);
      },
      handleSuccess(response, file, fileList) {
        if (response.code == 200) {
          this.dataForm.img = response.data
          this.$message({message: '上传成功', type: 'success'})
        } else {
          this.$message({message: '上传失败', type: 'error'})
        }
        //console.log(response)
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${file.name}？`);
      },
      initUpload() {
        this.uploadFile.url = baseUrl + "dept/upload"
        let token = Cookies.get("token")
        this.uploadFile.headers = {"token": token}
      },
      submitUpload() {
        this.$refs.upload.submit();
      },
      /*---上传图片---*/
      // 表单提交
      submitForm() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.editLoading = true
              let params = Object.assign({}, this.dataForm)
              if (!this.dataForm.id) {
                this.$api.dept.save(params).then((res) => {
                  this.editLoading = false
                  if (res.code == 200) {
                    this.$message({message: '操作成功', type: 'success'})
                    this.dialogVisible = false
                    this.$refs['dataForm'].resetFields()
                  } else {
                    this.$message({message: '操作失败, ' + res.msg, type: 'error'})
                  }
                  this.findTreeData()
                })
              } else {
                this.$api.dept.edit(params).then((res) => {
                  this.editLoading = false
                  if (res.code == 200) {
                    this.$message({message: '操作成功', type: 'success'})
                    this.dialogVisible = false
                    this.$refs['dataForm'].resetFields()
                  } else {
                    this.$message({message: '操作失败, ' + res.msg, type: 'error'})
                  }
                  this.findTreeData()
                })
              }

            })
          }
        })
      },
      // 时间格式化
      dateFormat: function (row, column, cellValue, index) {
        return format(row[column.property])
      }

    },
    mounted() {
      this.initUpload()
      this.findTreeData()
    }
  }
</script>

<style scoped>

</style>
