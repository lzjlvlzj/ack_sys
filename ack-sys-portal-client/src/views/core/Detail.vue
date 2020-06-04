<template>
  <div>
    <page-header></page-header>
    <el-row :gutter="0">
      <el-col :sm="1" :md="2" :lg="2" :xl="4"><div class="">&nbsp;</div></el-col>
      <el-col :sm="22" :md="20" :lg="20" :xl="16">
        <bread-crumb></bread-crumb>
        <el-col :lg="18">
          <el-container>
            <el-header class="article-detail">
              <div class="title">患者至上 | 一位年轻女孩在协和的“心”生之路</div>
              <div class="info">
                <el-col :md="6" :lg="6" :xl="6">
                  <span>时间：</span><span>2019-12-12</span>
                </el-col>
                <el-col :md="6" :lg="6" :xl="6">
                  <span>作者：</span><span>一颗圣兽</span>
                </el-col>
                <el-col :md="6" :lg="6" :xl="6">
                  <span>来源：</span><span>2019-12-12</span>
                </el-col>
                <el-col :md="6" :lg="6" :xl="6">
                  <span>点击次数：</span><span>198</span>
                </el-col>
              </div>
            </el-header>
            <el-main>
              2222
            </el-main>
            <el-footer>
              <el-col :md="12" :lg="12" :xl="12">22222</el-col>
              <el-col :md="12" :lg="12" :xl="12">33333</el-col>
            </el-footer>
          </el-container>
        </el-col>
        <el-col :lg="6">
          <div>1111</div>
        </el-col>
      </el-col>
      <el-col :sm="1" :md="2" :lg="2" :xl="4"><div class="">&nbsp;</div></el-col>
    </el-row>
    <page-footer></page-footer>
  </div>
</template>

<script>
  import pageHeader from '../PageHeader'
  import pageFooter from '../PageFooter'
  import BreadCrumb from '@/components/BreadCrumb'
  export default {
    name: "Detail",
    data(){
      return {

      }
    },
    components: {
      'pageHeader': pageHeader,
      'pageFooter': pageFooter,
      'breadCrumb':BreadCrumb
    },
    methods:{
      findDetail(){
        let id = this.$route.params.id
        console.log("11")
        console.log(id)
      }
    },
    created(){
      this.findDetail()
    },
    mounted() {

    }
  }
</script>

<style scoped>
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: left;


  }
  .article-detail .title{
    font-size: 30px;
    line-height: 44px;
    text-align: center;
  }
  .article-detail .info{

  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: left;
    line-height: 200px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: left;
    line-height: 160px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
</style>
