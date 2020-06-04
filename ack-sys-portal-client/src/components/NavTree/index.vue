<template>

  <el-submenu v-if="menu.children && menu.children.length >= 1" :index="'' + menu.id">
    <template slot="title">
      <i :class="menu.icon" ></i>
      <span slot="title">{{menu.name}}</span>
    </template>
    <NavTree v-for="item in menu.children" :key="item.id" :menu="item"></NavTree>
  </el-submenu>
  <el-menu-item v-else :index="'' + menu.id" @click="handleRoute(menu)">
    <i :class="menu.icon"></i>
    <span slot="title">{{menu.name}}</span>
  </el-menu-item>
</template>


<script>
  //import bus from '@/util/bus'
  export default {
    name: 'NavTree',
    props: {
      menu: {
        type: Object,
      }
    },
    data() {
      return {

      }
    },
    methods:{
      handleRoute(menu){
        let path = menu.url
        path = "/" + path
        let currentPath = this.$route.path
        //console.log("currentPath = " + currentPath)
        if(path == currentPath){
          //console.log("不需要跳转")
          return
        }
        //bus.$emit("currentMenu",menu)

        this.$router.push(path)
      }
    },
    created(){

    },
    beforeCreate () {

    }
  }
</script>

<style>

</style>
