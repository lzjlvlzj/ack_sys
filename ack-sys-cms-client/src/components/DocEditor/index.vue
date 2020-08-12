<template>
  <div class="tinymce-editor">
    <editor v-model="content"
            :init="init"
            @onClick="onClick">
    </editor>
  </div>
</template>
<script>
  import tinymce from 'tinymce/tinymce'
  import Editor from '@tinymce/tinymce-vue'
  import 'tinymce/themes/silver'
  import 'tinymce/icons/default/icons.min'
  import fileUtil from '@/utils/fileUtil';


  import 'tinymce/plugins/lists'// 列表插件
  import 'tinymce/plugins/advlist'
  import 'tinymce/plugins/autolink'
  import 'tinymce/plugins/link'
  import 'tinymce/plugins/image'
  import 'tinymce/plugins/charmap'
  import 'tinymce/plugins/print'
  import 'tinymce/plugins/preview'
  import 'tinymce/plugins/anchor'
  import 'tinymce/plugins/searchreplace'
  import 'tinymce/plugins/visualblocks'
  import 'tinymce/plugins/code'
  import 'tinymce/plugins/fullscreen'
  import 'tinymce/plugins/insertdatetime'
  import 'tinymce/plugins/media'
  import 'tinymce/plugins/table'
  import 'tinymce/plugins/paste'
  import 'tinymce/plugins/code'
  import 'tinymce/plugins/help'
  import 'tinymce/plugins/wordcount'


  export default {
    name: 'DocEditor',
    components: {
      Editor
    },
    props: {
      editorConfig: {
        type: Object,
        default: function () {
          let defaultValue = {
            content: '',
            id: "_editor_000",
            mode: 'exact',
            height: 500,
            value: "",
            baseUrl: "",
            imgUploadUrl: "",
            mediaUploadUrl: "",
            fileUploadUrl: "",
            plugins: [
              'advlist autolink lists link image charmap print preview anchor',
              'searchreplace visualblocks code fullscreen',
              'insertdatetime media table paste code help wordcount'
            ],
            toolbar: 'code undo redo restoredraft | cut copy paste pastetext | \
            forecolor backcolor bold italic underline strikethrough link anchor |\
            alignleft aligncenter alignright alignjustify outdent indent | \
            formatselect fontselect fontsizeselect | bullist numlist |\
             blockquote subscript superscript removeformat | \
            table image media charmap emoticons hr pagebreak insertdatetime print preview |\
            fullscreen | bdmap indent2em lineheight formatpainter axupimgs'
          };
          return defaultValue;
        }
      }
    },
    data() {
      return {
        init: {
          mode: this.editorConfig.mode,
          selector: this.editorConfig.id,
          height: this.editorConfig.height,
          language_url: `../../../static/tinymce/langs/zh_CN.js`,
          language: 'zh_CN',
          skin_url: `static/tinymce/skins/ui/oxide`,
          content_css: `static/tinymce/skins/content/default/content.min.css`,
          plugins: this.editorConfig.plugins,
          toolbar: this.editorConfig.toolbar,
          branding: false,
          menubar: false,
          fontsize_formats: "8pt 10pt 12pt 14pt 18pt 24pt 36pt 42pt 48pt 54pt 72pt",
          images_upload_handler: this.imgUpload,
          file_picker_callback: this.fileUpload,
          file_picker_types: 'file image media',
        },
        content: this.editorConfig.content
      }
    },
    mounted() {
      // tinymce.init({})
    },
    methods: {
      uploadEvent(uploadInfo) {
        let input = document.createElement('input');
        input.setAttribute('type', 'file');
        input.setAttribute('accept', uploadInfo.fileType);
        input.click();
        input.onchange = function () {
          let file = this.files[0];
          let callbackText = {text: file.name};
          let FileUtil = new fileUtil(file, uploadInfo.checkConfig);
          let checkSizeResult = FileUtil.checkSize();
          if (checkSizeResult.code != 200) {
            uploadInfo.self.$message({message: checkSizeResult.msg, type: "error"});
            return;
          }
          let checkSuffixResult = FileUtil.checkSuffix();
          if (checkSuffixResult.code != 200) {
            uploadInfo.self.$message({message: checkSuffixResult.msg, type: "error"});
            return;
          }

          let xhr, formData;
          xhr = new XMLHttpRequest();
          xhr.withCredentials = true;
          xhr.open('POST', uploadInfo.upUrl);
          xhr.onload = function () {
            let json;
            if (xhr.status != 200) {
              uploadInfo.self.$message({message: "上传失败", type: "error"});
              return;
            }
            console.log(xhr.responseText);
            json = JSON.parse(xhr.responseText);
            if (!json || typeof json.data != 'string') {
              uploadInfo.self.$message({message: json.msg, type: "error"});
              return;
            }
            json.location = json.data;
            uploadInfo.callback(json.location, callbackText);
          };
          formData = new FormData();
          formData.append('file', file, file.name);
          xhr.send(formData);
        };


      },
      mediaUpload() {
        let _self = this;
        let fileType = ".mp3, .mp4";
        //let upUrl = this.editorConfig.baseUrl + 'portal/article/mediaUpload';
        let upUrl = this.editorConfig.mediaUploadUrl;
        this.uploadEvent(fileType, upUrl, _self);
      },
      imgUpload(blobInfo, success, failure) {
        //let upUrl = this.editorConfig.baseUrl + 'portal/article/imgUpload';
        let upUrl = this.editorConfig.imgUploadUrl;
        let xhr, formData;
        xhr = new XMLHttpRequest();
        xhr.withCredentials = true;
        xhr.open('POST', upUrl);
        xhr.onload = function () {
          let json;
          if (xhr.status != 200) {
            failure('HTTP Error: ' + xhr.status);
            return;
          }
          json = JSON.parse(xhr.responseText);
          let msg = json.msg;
          json.location = json.data
          if (!json || typeof json.location != 'string') {
            failure('上传失败: ' + msg);
            return;
          }
          success(json.location);
        };
        formData = new FormData();
        formData.append('file', blobInfo.blob(), blobInfo.filename());
        xhr.send(formData);
      },
      fileUpload(callback, value, meta) {
        let fileType = '.pdf, .txt, .zip, .rar, .7z, .doc, .docx, .xls, .xlsx, .ppt, .pptx';
        //let upUrl = this.editorConfig.baseUrl + 'portal/article/fileUpload';
        let upUrl = this.editorConfig.fileUploadUrl;
        let checkConfig = {
          size: 10, /*10M*/
          suffixes: fileType.split(",")
        };
        if (meta.filetype == "image") {
          fileType = ".png, .jpg. jpeg";
          upUrl = this.editorConfig.imgUploadUrl;
          checkConfig.size = 2;/*2M*/
          checkConfig.suffixes = fileType.split(",");
        }
        if (meta.filetype == "media") {
          fileType = ".mp3,.mp4";
          upUrl = this.editorConfig.mediaUploadUrl;
          checkConfig.size = 10;/*200M*/
          checkConfig.suffixes = fileType.split(",");
        }
        let uploadInfo = {};
        uploadInfo.self = this;
        uploadInfo.upUrl = upUrl;
        uploadInfo.fileType = fileType;
        uploadInfo.callback = callback;
        uploadInfo.meta = meta;
        uploadInfo.value = value;
        uploadInfo.checkConfig = checkConfig;
        this.uploadEvent(uploadInfo);
      },
      getContent() {
        this.content = tinymce.activeEditor.getContent();
        return this.content;
      },
      onClick(e) {
        this.$emit('onClick', e, tinymce)
      },
      // 可以添加一些自己的自定义事件，如清空内容
      clear() {
        this.myValue = ''
      }
    },
    watch: {}
  }
</script>
