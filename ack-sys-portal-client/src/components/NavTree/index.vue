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
    created(){

    },
    beforeCreate () {

    }
  }
</script>

<style>

</style>
