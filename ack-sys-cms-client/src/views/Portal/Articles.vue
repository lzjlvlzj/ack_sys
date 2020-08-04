<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="filters" :size="size">
        <el-form-item>
          <el-input v-model="filters.name" placeholder="名称"></el-input>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" perms="portal:menu:view" type="primary"
                     @click="findPage(null)"/>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-plus" :label="$t('action.add')" perms="portal:menu:add" type="primary"
                     @click="handleAdd"/>
        </el-form-item>
      </el-form>
    </div>
    <!--表格树内容栏-->
    <el-table
      :data="tableTreeDdata" stripe size="mini" style="width: 100%"
      v-loading="loading" :element-loading-text="$t('action.loading')">
      <el-table-column
        prop="id" header-align="center" align="center" width="80" label="ID">
      </el-table-column>
      <table-tree-column
        prop="title" header-align="center" align="center" width="150" label="标题" :show-overflow-tooltip="true">
      </table-tree-column>
      <el-table-column prop="author" header-align="center" width="50" align="center" label="作者">
      </el-table-column>
      <el-table-column
        prop="pageView" header-align="center" align="center"  label="PV">
      </el-table-column>
      <el-table-column
        prop="status" header-align="center" align="center"  label="发布" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 0" size="mini">未发布</el-tag>
          <el-tag v-else-if="scope.row.status === 1" size="mini" type="success">已发布</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="portalMenu.name" header-align="center" align="center" label="分类" >
      </el-table-column>
      <el-table-column
        prop="source" header-align="center" align="center" label="来源" >
      </el-table-column>
      <el-table-column
        prop="remark" header-align="center" align="center" label="备注" :show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column
        prop="createName" header-align="center" align="center" label="创建人" >
      </el-table-column>
      <el-table-column
        prop="createTime" header-align="center" align="center" label="创建时间" :formatter="dateFormat">
      </el-table-column>
      <el-table-column
        fixed="right" header-align="center" align="center" width="185" :label="$t('action.operation')">
        <template slot-scope="scope">
          <kt-button icon="fa fa-edit" :label="$t('action.edit')" perms="portal:menu:edit" @click="handleEdit(scope.row)"/>
          <kt-button icon="fa fa-trash" :label="$t('action.delete')" perms="portal:menu:delete" type="danger"
                     @click="handleDelete(scope.row)"/>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页-->
    <div class="toolbar" style="padding:10px;">
      <el-pagination layout="total, prev, pager, next, jumper" @current-change="refreshPageRequest"
                     :current-page="pageRequest.currentPage" :page-size="data.pageSize" :total="data.totalRecord"
                     style="float:right;">
      </el-pagination>
    </div>
    <!-- 新增修改界面 -->
    <el-dialog :title="!dataForm.id ? '新增' : '修改'" width="40%" :visible.sync="dialogVisible"
               :close-on-click-modal="false">
      <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="submitForm()"
               label-width="80px" :size="size" style="text-align:left;">
        <el-form-item label="url" prop="url">
          <el-input v-model="dataForm.menuId" placeholder="分类"></el-input>
        </el-form-item>
        <el-form-item label="url" prop="url">
          <el-input v-model="dataForm.url" placeholder="标题"></el-input>
        </el-form-item>
        <el-form-item  label="正文" prop="icon">
          <editor api-key="no-api-key" :init="editorConfig"/>
        </el-form-item>
        <el-form-item  label="备注" prop="remark">
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            v-model="dataForm.remark">
          </el-input>
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
  import KtButton from "@/views/Core/KtButton";
  import TableTreeColumn from "@/views/Core/TableTreeColumn";
  import PopupTreeInput from "@/components/PopupTreeInput";
  import FaIconTooltip from "@/components/FaIconTooltip";
  import {format} from "@/utils/datetime"
  import {baseUrl} from '@/utils/global'
  import Cookies from "js-cookie";

  import Editor from '@tinymce/tinymce-vue'


  export default {
    name: "PortalArticles",
    components: {
      PopupTreeInput,
      KtButton,
      TableTreeColumn,
      FaIconTooltip,
      Editor,
    },
    data() {
      return {
        size: "small",
        loading: false,
        uploadFile: {
          url: '',
          withCredentials: true,
          multiple: false,
          limit: 1
        },
        editorConfig:{
          language_url: '/static/tinymce/langs/zh_CN.js',
          language: 'zh_CN',
          //skin_url: '/static/tinymce/skins/lightgray',
          height: 300,
          branding: false,//是否禁用“Powered by TinyMCE”
          menubar: true,//顶部菜单栏显示
          plugins: [
            'advlist autolink lists link image charmap print preview anchor',
            'searchreplace visualblocks code fullscreen',
            'insertdatetime media table paste code help wordcount'
          ],
          toolbar:
            'undo redo | formatselect | bold italic backcolor | \
            alignleft aligncenter alignright alignjustify | \
            bullist numlist outdent indent | removeformat | help'
        },
        filters: {
          name: ""
        },
        data: {},
        pageRequest: {currentPage: 1, pageSize: 10},
        tableTreeDdata: [],
        dialogVisible: false,
        menuTypeList: ["目录", "菜单", "按钮"],
        dataForm: {
          id: 0,
          bgUrl:'',
          name: "",
          parentId: 0,
          menuId:0,
          parentName: "",
          url: "",
          icon: "",
          remark:"",
          deleteStatus: 0
        },
        dataRule: {
          name: [{required: true, message: "菜单名称不能为空", trigger: "blur"}],
        },
        popupTreeData: [],
      };
    },
    methods: {
      /*---上传图片---*/
      handleRemove(file, fileList) {
        //console.log(file, fileList);
      },
      handlePreview(file) {
        //console.log(file);
      },
      handleSuccess(response, file, fileList) {
        if (response.code == 200) {
          this.dataForm.bgUrl = response.data
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
      submitUpload() {
        this.$refs.upload.submit();
      },
      // 换页刷新
      refreshPageRequest: function (currentPage) {
        this.pageRequest.currentPage = currentPage
        this.findPage()
      },
      //用户状态格式化
      getCreateName: function (row, column, cellValue, index) {
        return row.createName
      },
      //url格式化
      urlFormat: function (row, column, cellValue, index) {

        let url = row.bgUrl
        if (url) {
          let po = url.lastIndexOf("/")
          let val = url.substring(po + 1)
          let alink = "<a href='" + url + "'>" + val + "</a>"
          return alink
        }
        return row.bgUrl
      },

      // 时间格式化
      dateFormat: function (row, column, cellValue, index) {
        return format(row[column.property])
      },
      // 获取数据
      findPage: function () {
        this.loading = true;
        this.pageRequest.columnFilters = {name: {name: 'name', value: this.filters.name}}
        this.$api.portalArticle.findPage(this.pageRequest).then(res => {
          this.data = res.data
          this.tableTreeDdata = res.data.result
          this.loading = false;
        });
      },
      // 获取上级菜单树
      getParentMenuTree: function (tableTreeDdata) {
        let parent = {
          parentId: 0,
          name: "顶级菜单",
          children: tableTreeDdata
        };
        return [parent];
      },
      // 显示新增界面
      handleAdd: function () {
        this.dialogVisible = true;
        this.dataForm = {
          id: 0,
          type: 1,
          name: "",
          parentId: 0,
          parentName: "",
          url: "",
          perms: "",
          orderNum: 0,
          icon: "",
          iconList: []
        };
      },
      // 显示编辑界面
      handleEdit: function (row) {
        this.dialogVisible = true;
        Object.assign(this.dataForm, row);
      },
      // 删除
      handleDelete(row) {
        this.$confirm("确认删除选中记录吗？", "提示", {
          type: "warning"
        }).then(() => {
          let params = this.getDeleteIds([], row);
          this.$api.portalMenu.batchDelete(params).then(res => {
            this.findPage();
            this.$message({message: "删除成功", type: "success"});
          });
        });
      },
      // 获取删除的包含子菜单的id列表
      getDeleteIds(ids, row) {
        ids.push({id: row.id});
        if (row.children != null) {
          for (let i = 0, len = row.children.length; i < len; i++) {
            this.getDeleteIds(ids, row.children[i]);
          }
        }
        return ids;
      },
      // 菜单树选中
      handleTreeSelectChange(data, node) {
        this.dataForm.parentId = data.id;
        this.dataForm.parentName = data.name;
      },
      // 图标选中
      iconActiveHandle(iconName) {
        this.dataForm.icon = iconName;
      },
      // 表单提交
      submitForm() {
        this.$refs["dataForm"].validate(valid => {
          if (valid) {
            if(!this.dataForm.bgUrl){
              this.$message({message: "大图不能为空", type: "error"});
              return ;
            }
            this.$confirm("确认提交吗？", "提示", {}).then(() => {
              this.editLoading = true;
              let params = Object.assign({}, this.dataForm);
              if (!this.dataForm.id) {
                this.$api.portalMenu.save(params).then(res => {
                  this.editLoading = false;
                  if (res.code == 200) {
                    this.$message({message: "操作成功", type: "success"});
                    this.$refs["dataForm"].resetFields();
                    this.dialogVisible = false;
                  } else {
                    this.$message({
                      message: "操作失败, " + res.msg,
                      type: "error"
                    });
                  }
                  this.findPage();
                });
              } else {
                this.$api.portalMenu.update(params).then(res => {
                  this.editLoading = false;
                  if (res.code == 200) {
                    this.$message({message: "操作成功", type: "success"});
                    this.$refs["dataForm"].resetFields();
                    this.dialogVisible = false;
                  } else {
                    this.$message({
                      message: "操作失败, " + res.msg,
                      type: "error"
                    });
                  }
                  this.findPage();
                });
              }

            });
          }
        });
      },
      initUpload() {
        this.uploadFile.url = baseUrl + "portal/menu/upload"
        let token = Cookies.get("token")
        this.uploadFile.headers = {"token": token}
      },
    },
    mounted() {
      this.initUpload();
      this.findPage();
    }
  }
</script>

<style scoped>

</style>
