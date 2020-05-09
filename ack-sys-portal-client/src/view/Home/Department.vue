<template>
  <div class="box box-border-none" id="ksdh">
    <div class="box-header box-header-content">科室导航</div>
    <div class="box-body ksdh-content">
      <el-row :gutter="20">
        <el-col :lg="9" :xl="9" >
          <img :src="imgUrl" class="ksdh-content"/>
        </el-col>
        <el-col :lg="15" :xl="15">
          <el-tabs v-model="activeName" @tab-click="handleClick" type="border-card" class="ksdh-content">
            <el-tab-pane name="first">
              <span slot="label" class="tabs">内科</span>
              <ul class="ksdh-tab">
                <li>默认按钮</li>
                <li>主要按钮</li>
                <li>成功按钮</li>
                <li>警告按钮</li>
                <li>危险按钮</li>
                <li>默认按钮</li>
              </ul>
            </el-tab-pane>
            <el-tab-pane  name="second">
              <span slot="label" class="tabs">外科</span>
              <ul class="ksdh-tab">
                <li>默认按钮</li>
                <li>主要按钮</li>
                <li>成功按钮</li>
                <li>警告按钮</li>
                <li>危险按钮</li>
                <li>默认按钮</li>
              </ul>
            </el-tab-pane>
            <el-tab-pane  name="third">
              <span slot="label" class="tabs">医技</span>
              <ul class="ksdh-tab">
                <li>默认按钮</li>
                <li>主要按钮</li>
                <li>成功按钮</li>
                <li>警告按钮</li>
                <li>危险按钮</li>
                <li>默认按钮</li>
              </ul>
            </el-tab-pane>
            <el-tab-pane  name="more">
              <span slot="label" class="tabs">更多</span>
            </el-tab-pane>
          </el-tabs>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Department",
    data(){
      return {
        imgUrl:"../../../static/img/ks-1.jpg",
        activeName: 'first'
      }
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
        if(tab.name=="more"){
           console.log('更多')
        }
      }
    }
  }
</script>

<style scoped>
  #ksdh {
    min-height: 320px;
    width: 100%;
    position: inherit;
    margin-top: 15px;
  }
  .ksdh-content{
    width: 100%;
    height:320px ;
  }
  .ksdh-tab {
    width: 100%;
  }
  .ksdh-tab li{
    float: left;
    margin-left: 15px;
    cursor: pointer;
    border: 1px solid #cccccc;
    border-radius: 5px;
    width: 144px;
    height: 38px;
    line-height: 38px;
    font-size: 14px;
    margin-bottom: 14px;
  }
  .tabs {
    font-size: 18px;
  }

</style>
