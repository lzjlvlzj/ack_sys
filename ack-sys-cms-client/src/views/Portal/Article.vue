<template>
  <div id="portalArticle-add">

    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="submitForm()"
             :label-width="formConfig.labelWidth" :size="formConfig.size" style="text-align:left;">
      <div class="article-btns">
        <el-button size="small" type="info" @click="submitForm(0)">{{$t('action.save')}}</el-button>
        <el-button size="small" type="primary" @click="submitForm(1)">{{$t('action.publish')}}</el-button>
        <!--<el-button size="small" type="success" @click="submitForm()">{{$t('action.preView')}}</el-button>-->
      </div>
      <el-tabs tab-position="left">
        <el-tab-pane label="基础信息">
          <el-form-item label="分类" prop="title">
            <el-select v-model="dataForm.menuId" placeholder="请选择">
              <el-option
                v-for="item in portalMenus"
                :key="item.id"
                :label="item.name"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="标题" prop="title">
            <el-input v-model="dataForm.title" placeholder="标题"></el-input>
          </el-form-item>
          <el-form-item label="作者" prop="author">
            <el-input v-model="dataForm.author" placeholder="作者"></el-input>
          </el-form-item>
          <el-form-item label="来源" prop="source">
            <el-input v-model="dataForm.source" placeholder="作者"></el-input>
          </el-form-item>
          <el-form-item label="摘要" prop="summery">
            <el-input v-model="dataForm.summery" placeholder="摘要"></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input type="textarea" v-model="dataForm.remark" placeholder="备注"></el-input>
          </el-form-item>
        </el-tab-pane>
        <el-tab-pane label="文章内容">
          <doc-editor :editorConfig="editorConfig" v-model="dataForm.content" ref="docEditor"></doc-editor>
        </el-tab-pane>
      </el-tabs>

    </el-form>
  </div>
</template>

<script>
  import DocEditor from '@/components/DocEditor';
  import {baseUrl} from '@/utils/global';

  export default {
    name: 'PortalArticle',
    components: {
      DocEditor
    },
    data() {
      return {
        portalMenus: {},
        uploadFile: {
          url: 'upload',
          withCredentials: true,
          multiple: false,
          limit: 1
        },
        formConfig: {
          size: "mini",
          labelWidth: "80px",
        },
        dataForm: {
          id: 0,
          menuId: 5,
          summery: 'ack',
          source: 'ack',
          content: '',
          author: 'ack',
          title: "测试新闻",
          remark: "ack",
          deleteStatus: 0
        },
        dataRule: {
          title: [{required: true, message: "文章标题不能为空", trigger: "blur"}],
          author: [{required: true, message: "文章作者不能为空", trigger: "blur"}],
          content: [{required: true, message: "文章内容不能为空", trigger: "blur"}],
        },
        editorConfig: {
          imgUploadUrl: baseUrl + "portal/article/imgUpload",
          mediaUploadUrl: baseUrl + "portal/article/mediaUpload",
          fileUploadUrl: baseUrl + "portal/article/fileUpload",
          mode : "exact",
          id: '#_portal_content_',
          language: 'zh_CN',
          height: 500,
          plugins: [
            'advlist autolink lists link image charmap print preview anchor',
            'searchreplace visualblocks code fullscreen',
            'insertdatetime media table paste code help wordcount'
          ],
          toolbar:
            'code undo redo restoredraft | cut copy paste pastetext | \
            forecolor backcolor bold italic underline strikethrough link anchor |\
            alignleft aligncenter alignright alignjustify outdent indent | \
            formatselect fontselect fontsizeselect | bullist numlist |\
             blockquote subscript superscript removeformat | \
            table image media charmap emoticons hr pagebreak insertdatetime print preview |\
            fullscreen | bdmap indent2em lineheight formatpainter axupimgs',

        }
      }
    },
    methods: {
      getContent(){
         let content = this.$refs.docEditor.getContent();
         return content;
      },
      submitForm(flag) {
        this.$refs["dataForm"].validate(valid => {
          if (valid) {
            //this.dataForm.content = tinymce.activeEditor.getContent()
            this.dataForm.content = this.getContent();
            if (!this.dataForm.content) {
              this.$message({message: "文章内容不能为空", type: "error"});
              return;
            }
            this.$confirm("确认提交吗？", "提示", {}).then(() => {
              this.editLoading = true;
              let params = Object.assign({}, this.dataForm);
              params.portalArticleDetail = {}
              params.portalArticleDetail.content = this.dataForm.content
              if (!this.dataForm.id) {
                if (flag == 0) {
                  this.$api.portalArticle.save(params).then(res => {
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
                  });
                } else {

                }

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

                });
              }

            });

          }
        })
        //console.log(this.dataForm)
      },
      findMenus() {
        this.$api.portalMenu.findAll({}).then(res => {
          this.portalMenus = res.data
        }).catch(err => {

        })
      },
      init() {
        this.findMenus();
      }
    },
    created() {


    },
    mounted() {
      this.init()
    }
  }
</script>
<style>
  .article-btns {
    margin-bottom: 10px;
  }
</style>
