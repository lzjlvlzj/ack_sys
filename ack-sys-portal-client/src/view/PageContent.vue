<template>
	<div id="main-content" class="">
    <home></home>
	</div>
</template>
<script>
  import Home from './Home/home'

  export default {
    name: "PageContent",
    components: {
      "home": Home
    }
  }
</script>
<style scoped>
  #main-content{
    min-height: 400px;
    border: red solid 1px;
    margin:10px 0px 10px 0px;
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
