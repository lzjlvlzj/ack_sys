<template>
  <el-breadcrumb separator="/" class="breadcrumb">
    <el-breadcrumb-item v-for="item in $route.matched" :key="item.path">
      <router-link :to="item.path">{{ item.name }}</router-link>
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script>
  import tool from "../../util/tool"
export default {
  data() {
    return {
      titleList:null,
      tmpMenu:''
    }
  },
  watch:{
    $route(){
      this.initTitleList()
    }
  },
  methods: {

    findMenuName(data, path){

      if(!data){
        return
      }
      for (let i = 0; i < data.length; i++) {
        let item = data[i]
        let url = item.url
        let pid = this.traverseNode(item, path)
        this.findMenuName(item.children);
      }

    },
    traverseNode(node){
      this.tmpMenu.push(node)
    },
    sortMenus(data){
      if(!data){
        return
      }
      for (let i = 0; i < data.length; i++) {
        let item = data[i]
        this.sortMenus(item.children);
      }
    },
    initTitleList(){
      let matched = this.$route.matched.filter(item=>item.path);
      let first = matched[0];
      let path = first.path
      let stack = this.$store.state.menu.navTree
      //console.log(stack)
      let mu = Object.assign({}, stack);
      let menus = this.sortMenus(mu)
      console.log(this.tmpMenu)

    }
  },
  created() {
    //this.initTitleList()
  },
  beforeCreate() {


  },
  mounted() {

  }
};
</script>

<style scoped >
.breadcrumb {
  padding: 10px 10px 10px 0px;
  margin: 20px 0 20px 0;
}
</style>
