<template>
  <el-tabs :tab-position="tabsConf.tabPosition"  :type="tabsConf.type" :stretch="tabsConf.stretch">
    <el-tab-pane label="菜单管理">
      <portal-menu></portal-menu>
    </el-tab-pane>
    <el-tab-pane label="轮播管理">
      <portal-carousel></portal-carousel>
    </el-tab-pane>
    <el-tab-pane label="卡片管理">卡片管理</el-tab-pane>
    <el-tab-pane label="部门管理">部门管理</el-tab-pane>
  </el-tabs>
</template>

<script>
  import KtButton from "@/views/Core/KtButton";
  import TableTreeColumn from "@/views/Core/TableTreeColumn";
  import PopupTreeInput from "@/components/PopupTreeInput";
  import FaIconTooltip from "@/components/FaIconTooltip";
  import PortalMenu from "@/views/Portal/Menu";
  import PortalCarousel from "@/views/Portal/Carousel";


  export default {
    components: {
      PopupTreeInput,
      KtButton,
      TableTreeColumn,
      FaIconTooltip,
      PortalMenu,
      PortalCarousel
    },
    name: "PortalIndex",
    data() {
      return {
        tableConfig:{
          size:"mini",
        },
        loading: false,
        tabsConf:{
          tabPosition: "left",
          stretch:true,
          type:""
        },
        uploadFile: {
          url: '',
          withCredentials: true,
          multiple: false,
          limit: 1
        }
      };
    },
    methods: {

    },
    mounted() {

    }
  }
</script>

<style scoped>

</style>
