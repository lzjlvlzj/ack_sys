<template>
  <div id="header">
    <el-row :gutter="20">
      <el-col :span="4">
        <div class="header-content bg-purple"></div>
      </el-col>
      <el-col :span="16" class="header">
        <el-row class="header-content" :gutter="20">
          <el-col :span="12" class="logo">
            <img src="../../static/img/logo.png"></img>
          </el-col>
          <el-col :span="12">
            <div class="contact-us">
              <span><a>首页</a></span>
              <span><a>联系我们</a></span>
            </div>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="4">
        <div class="header-content bg-purple"></div>
      </el-col>
    </el-row>
    <!-- nav-->
    <page-nav></page-nav>
  </div>

</template>
<script>

  import Nav from './PageNav'

  export default {
    components: {
      'pageNav': Nav
    }
  };
</script>
<style scoped>
  #header {
    width: 100%;
    border: #795da3 solid 1px;

  }

  .contact-us {
    float: right;
    padding-top: 40px;
  }

  .logo {

  }
  .header-content {
    min-height: 36px;
    margin: 10px 0 10px 0;
  }

  .bg-purple {
    background: #d3dce6;
  }
</style>
