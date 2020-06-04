<template>
  <div>
   <!-- <div>
      <a href="javascript:;" class="menu_h" @click="handleMenu"><span></span></a>
    </div>-->
    <el-menu
      :collapse-transition="collapseTransition"
      @open="handleOpen"
      @close="handleClose"
      :collapse="isCollapse"
      :default-active="activeIndex"
      class="port-menu"
      :mode="menuMode"
      @select="handleSelect"
      background-color="#015128"
      text-color="#fff"
      active-text-color="#ffd04b">
      <nav-tree v-for="item in navTree" :key="item.id" :menu="item"></nav-tree>
    </el-menu>
  </div>


</template>

<script>
  import {mapState} from 'vuex'
  import NavTree from "@/components/NavTree"

  export default {
    name: "PageNav",
    components: {
      NavTree
    },
    computed: {
      ...mapState({
        navTree: state => state.menu.navTree
      }),
    },
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1',
        isCollapse: false,
        collapseTransition: false,
        menuMode: "horizontal",
        barState: false
      }
    },
    methods: {
      handleSelect(key, keyPath) {
        //console.log(key, keyPath);
      },
      handleOpen() {
        console.log('handleopen')
      },
      handleClose() {
        console.log('handleclose')
        this.menuMode = "vertical"
      },
      handleMenu() {
        this.barState = true
        if (this.barState) {
          this.menuMode = "vertical"
          this.barState = false
        } else {
          this.barState = true
        }

      },
      open: function () {
        this.menuMode = "horizontal"
      },
      close: function () {
        this.menuMode = "vertical"
      },
      initNav() {
        let url = "/nav"
        let params = {}
        let method = "get"
        var request = {}
        request.url = url
        request.method = method
        request.params = params
        this.$api.menu.findNav(request).then(res => {
          //this.$store.commit("setNavTree", res.data)
        }).catch(err => {
          //console.log(err)
        })
      }
    },
    created() {

    },
    beforeCreate() {
      //console.log('在实例初始化之前调用1')

    },
    watch: {},
    mounted() {
      this.initNav()
    }
  }
</script>

<style scoped>
  @media only screen and (min-width: 1023px) {
    .port-menu li:first-child {
      margin-left: 16.6667%;
    }
  }

  @media only screen and (max-width: 1279px) {
    .menu_h {
      display: block; /* background: none; *//* transition: 0.3s all; *//* -webkit-transition: 0.3s all; *//* border: none; */
      width: 35px;
      height: 30px;
      padding: 0;
      outline: none;
      position: fixed;
      right: 10px;
      top: 58px;
      z-index: 2000;
    }

    .menu_h::before, .menu_h::after, .menu_h span {
      background: #015128;
      border-radius: 2px;
    }

    .menu_h::before, .menu_h::after {
      content: '';
      position: absolute;
      height: 4px;
      width: 100%;
      left: 0;
      top: 50%;
      -webkit-transform-origin: 50% 50%;
      transform-origin: 50% 50%;
      -webkit-transition: -webkit-transform 0.25s;
      transition: -webkit-transform 0.25s;
      transition: transform 0.25s;
      transition: transform 0.25s, -webkit-transform 0.25s;
    }

    .menu_h span {
      position: absolute;
      width: 100%;
      height: 4px;
      top: 50%;
      left: 0;
      overflow: hidden;
      text-indent: 200%;
      -webkit-transition: opacity 0.25s;
      transition: opacity 0.25s;
    }

    .menu_h::before {
      -webkit-transform: translate3d(0, -12px, 0);
      transform: translate3d(0, -12px, 0);
    }

    .menu_h::after {
      -webkit-transform: translate3d(0, 12px, 0);
      transform: translate3d(0, 12px, 0);
    }

    .menu-open .menu_h span {
      opacity: 0;
    }

    .menu-open .menu_h::before {
      -webkit-transform: rotate3d(0, 0, 1, 45deg);
      transform: rotate3d(0, 0, 1, 45deg);
    }

    .menu-open .menu_h::after {
      -webkit-transform: rotate3d(0, 0, 1, -45deg);
      transform: rotate3d(0, 0, 1, -45deg);
    }
  }


</style>
