<template>
	<div id="main-content" class="">
    <!-- 首页-->
    <keep-alive v-if="$route.meta.keepAlive">
      <transition name="fade" mode="out-in">
        <router-view></router-view>
      </transition>
    </keep-alive>

    <!-- 其他页面-->
    <el-row :gutter="0" v-else>
      <el-col :sm="1" :md="2" :lg="2" :xl="4"><div class="">&nbsp;</div></el-col>
      <el-col :sm="22" :md="20" :lg="20" :xl="16">
        <bread-crumb></bread-crumb>
        <keep-alive >
          <transition name="fade" mode="out-in">
            <router-view></router-view>
          </transition>
        </keep-alive>
      </el-col>
      <el-col :sm="1" :md="2" :lg="2" :xl="4"><div class="">&nbsp;</div></el-col>
    </el-row>
    <!--<home v-if="!isHome"></home>
    <el-row :gutter="0" v-else>
      <el-col :sm="1" :md="2" :lg="2" :xl="4"><div class="">&nbsp;</div></el-col>
      <el-col :sm="22" :md="20" :lg="20" :xl="16">
        <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>活动管理</el-breadcrumb-item>
          <el-breadcrumb-item>活动列表</el-breadcrumb-item>
          <el-breadcrumb-item>活动详情</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>

      <el-col :sm="1" :md="2" :lg="2" :xl="4"><div class="">&nbsp;</div></el-col>
    </el-row>-->
	</div>
</template>
<script>
  import Home from './Home'
  import BreadCrumb from '@/components/BreadCrumb'

  export default {
    name: "PageContent",
    components: {
      "home": Home,
      "breadCrumb" : BreadCrumb
    },
    data(){
      return {
        isHome : false
      }
    },
    created() {
      let path = this.$route.path
      if(path == '/' || '' == path){
        this.isHome = true
      }
      //console.log(path)
    },
    beforeRouteLeave(to, from, next) {
      // 设置下一个路由的 meta
      //to.meta.keepAlive = true;  // B 跳转到 A 时，让 A 缓存，即不刷新
      //next();
    },
    beforeCreate() {


    },
    mounted() {

    }
  }
</script>
<style scoped>
  #main-content{
    min-height: 400px;
   /* border: red solid 1px;*/
    margin:0px 0px 10px 0px;
  }
  .breadcrumb {
    font-size: 14px;
    color: #707070;
    padding: 20px 0;
  }
</style>
