<template>
  <div>
    <carousel id="home-carousel" :carousel-obj='carouselObj'></carousel>
    <el-row :gutter="0">
      <el-col :sm="1" :md="2" :lg="2" :xl="4"><div class="">&nbsp;</div></el-col>
      <el-col :sm="22" :md="20" :lg="20" :xl="16">
        <content-summary></content-summary>
        <department></department>
        <self-info></self-info>
      </el-col>
      <el-col :sm="1" :md="2" :lg="2" :xl="4"><div class="">&nbsp;</div></el-col>
    </el-row>
  </div>
</template>

<script>
  import carousel from '@/components/Carousel'
  import contentSummary from "./ContentSummary"
  import Department from './Department'
  import SelfInfo from './SelfInfo'
  export default {
    name: "Home",
    components: {
      "carousel": carousel,
      "contentSummary":contentSummary,
      "department":Department,
      "SelfInfo": SelfInfo
    },
    data(){
      return {
        carouselObj :{
          url:"/carousel/images",
          height:"500"
        }

      }
    },
    methods:{

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
