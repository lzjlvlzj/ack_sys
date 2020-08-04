<template>
    <div class="page-container">
      <!--表格树内容栏-->
      <el-table
        :data="tableTreeDdata" stripe size="mini" style="width: 100%"
        v-loading="loading" :element-loading-text="$t('action.loading')">
        <el-table-column
          prop="id" header-align="center" align="center" width="80" label="ID">
        </el-table-column>

        <el-table-column
          prop="url" header-align="center" align="center"
          :show-overflow-tooltip="true" label="大图URL" >
          <template slot-scope="scope">
            <a :href="scope.row.url"
               target="_blank"
               class="buttonText">{{scope.row.url}}</a>
          </template>

        </el-table-column>
        <el-table-column
          prop="remark" header-align="center" align="center" label="备注">
        </el-table-column>
        <el-table-column
          prop="createName" header-align="center" align="center" label="创建人"  width="150">
        </el-table-column>
        <el-table-column
          prop="createTime" header-align="center" align="center" label="创建时间" :formatter="dateFormat">
        </el-table-column>
        <el-table-column
          fixed="right" header-align="center" align="center" width="185" :label="$t('action.operation')">
          <template slot-scope="scope">
            <kt-button icon="fa fa-edit" :label="$t('action.edit')" perms="portal:menu:edit" @click="handleEdit(scope.row)"/>
          </template>
        </el-table-column>
      </el-table>
      <!-- 修改界面 -->
      <el-dialog :title="'修改'" width="40%" :visible.sync="dialogVisible"
                 :close-on-click-modal="false">
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="submitForm()"
                 label-width="80px" :size="size" style="text-align:left;">
          <el-form-item label="level" prop="level" v-if="false">
            <el-input v-model="dataForm.level" :disabled="true" auto-complete="off" value="1"></el-input>
          </el-form-item>
          <el-form-item label="url" prop="url">
            <el-input v-model="dataForm.url" placeholder="url" disabled></el-input>
          </el-form-item>
          <el-form-item  label="图片" prop="url">
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
              :auto-upload="false">
              <el-button slot="trigger" size="mini" type="primary">选取文件</el-button>
              <el-button style="margin-left: 10px;" size="mini" type="success" @click="submitUpload">上传到服务器</el-button>
              <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
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
  import FaIconTooltip from "@/components/FaIconTooltip";
  import {format} from "@/utils/datetime";
  import {baseUrl} from '@/utils/global'
  import Cookies from "js-cookie";
    export default {
      name: "PortalCarousel",
      components :{
        KtButton,
        FaIconTooltip,

      },
      data(){
        return {
          size: "mini",
          loading: false,
          uploadFile: {
            url: 'upload',
            withCredentials: true,
            multiple: false,
            limit: 1
          },
          filters: {
            name: ""
          },
          data: {},
          pageRequest: {currentPage: 1, pageSize: 3},
          tableTreeDdata: [],
          dialogVisible: false,
          dataForm: {
            id: 0,
            name: "",
            position: 0,
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
          popupTreeProps: {
            label: "name",
            children: "children"
          }
        };
      },
      methods : {
        /*---上传图片---*/
        handleRemove(file, fileList) {
          //console.log(file, fileList);
        },
        handlePreview(file) {
          //console.log(file);
        },
        submitUpload() {
          this.$refs.upload.submit();
        },
        handleSuccess(response, file, fileList) {
          if (response.code == 200) {
            this.dataForm.url = response.data
            this.$message({message: '上传成功', type: 'success'})
          } else {
            this.$message({message: '上传失败', type: 'error'})
          }
          //console.log(response)
        },
        // 时间格式化
        dateFormat: function (row, column, cellValue, index) {
          return format(row[column.property])
        },
        findPage: function () {
          this.loading = true;
          this.pageRequest.columnFilters = {name: {name: 'name', value: this.filters.name}}
          this.$api.portalCarousel.findPage(this.pageRequest).then(res => {
            this.data = res.data
            this.tableTreeDdata = res.data.result
            this.popupTreeData = res.data.result
            this.loading = false;
          });
        },
        // 显示编辑界面
        handleEdit: function (row) {
          this.dialogVisible = true;
          Object.assign(this.dataForm, row);
        },
        submitForm() {
          this.$refs["dataForm"].validate(valid => {
            if (valid) {
              this.$confirm("确认提交吗？", "提示", {}).then(() => {
                this.editLoading = true;
                let params = Object.assign({}, this.dataForm);
                if (!this.dataForm.id) {

                } else {
                  this.$api.portalCarousel.update(params).then(res => {
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
          this.uploadFile.url = baseUrl + "portal/carousel/upload"
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
