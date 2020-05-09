<template>
  <div>
    <carousel id="home-carousel"></carousel>
    <el-row :gutter="10">
      <el-col :lg="3" :xl="3"><div class="grid-content bg-purple">11</div></el-col>
      <el-col :lg="18" :xl="18">
        <content-summary></content-summary>
        <department></department>
      </el-col>
      <el-col :lg="3" :xl="3"><div class="grid-content bg-purple-light">33</div></el-col>
    </el-row>
  </div>
</template>

<script>
  import carousel from '../Carousel/Carousel'
  import contentSummary from "./ContentSummary"
  import Department from './Department'
  export default {
    name: "Home",
    components: {
      "carousel": carousel,
      "contentSummary":contentSummary,
      "department":Department,
    },
    data(){
      return {

      }
    }
  }
</script>

<style scoped>
  #home-carousel{
    margin-bottom: 10px;
  }
  el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
</style>
