<template>
  <textarea id="mytextarea">Hello, World!</textarea>
</template>

<script>
  export default {
    name: "DocEditor",
    props: {
      docEditor: {
        type: Object,
        required: true,
      }
    },
    data() {
      return {

      }
    },
    methods:{
      init:function () {
        tinymce.init({
          selector: '#mytextarea',
          language:'zh_CN', //调用放在langs文件夹内的语言包
          menubar: true, //隐藏菜单栏
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
        });
      }
    },
    mounted() {
      this.init()
    }
  }
</script>

<style scoped>

</style>
