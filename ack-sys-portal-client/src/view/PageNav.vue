<template>
  <el-menu
    :default-active="activeIndex2"
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b">
    <nav-tree v-for="item in navTree" :key="item.id" :menu="item" ></nav-tree>
  </el-menu>

</template>

<script>
  import { mapState } from 'vuex'
  import NavTree from "@/components/NavTree"
  export default {
    name: "PageNav",
    components: {
      NavTree
    },
    computed: {
      ...mapState({
        navTree: state=>state.menu.navTree
      }),
    },
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1',

      }
    },
    methods: {
      handleSelect(key, keyPath) {
        //console.log(key, keyPath);
      },
      initNav(){
        let url = "/nav"
        let params = {}
        let method = "get"
        var request = {}
        request.url = url
        request.method = method
        request.params = params
        this.$api.menu.findNav(request).then(res=>{
          this.$store.commit("setNavTree", res.data )
        }).catch(err=>{
           //console.log(err)
        })
      }
    },
    created(){
      this.initNav()
    },
    beforeCreate () {
      //console.log('在实例初始化之前调用1')

    },
    watch:{

    },
    mounted() {

    }
  }
</script>

<style scoped>

</style>
