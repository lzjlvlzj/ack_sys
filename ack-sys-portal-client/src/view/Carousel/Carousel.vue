<template>
  <el-carousel :interval="5000" arrow="always" :height="carouselHeight">
    <el-carousel-item v-for="item in images" :key="item.id">
      <img :src="item.imgUrl"/>
    </el-carousel-item>
  </el-carousel>
</template>
<script>
  export default {
    name: "Carousel",
    data() {
      return {
        images:[],
        carouselHeight:"500px"
      }
    },
    methods:{
      initCarousel(){
        let data = {}
        data.params = {}
        data.url = "/carousel/images"
        data.method = "get"
        this.$api.carousel.findCarouselImages(data).then(res=>{
          console.log(res)
          this.images = res.data
        }).catch(err=>{
          //console.log(err)
        })



      }
    },
    mounted() {
      this.initCarousel();
    }
  }
</script>

<style scoped>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 20px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
