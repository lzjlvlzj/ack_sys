<template>
  <div class="box box-border-none" id="ksdh">
    <div class="box-header box-header-content">科室导航</div>
    <div class="box-body ksdh-content">
      <el-row :gutter="20">
        <el-col :lg="9" :xl="9">
          <a :href="defaultUrl">
            <img :src="defaultImgUrl" class="ksdh-content"/>
          </a>
        </el-col>
        <el-col :lg="15" :xl="15">
          <el-tabs v-model="defaultActive" @tab-click="handleClick"  class="ksdh-content">
            <el-tab-pane :name="dept.id" v-for="dept in departments" :key="dept.id">
              <span slot="label" class="tabs">{{dept.name}}</span>
              <ul class="ksdh-tab">
                <li v-for="item in dept.children" :style='liClass' @mouseover="selectDeptStyle(item, dept)" @mouseout="outDeptStyle(item,dept)" :ref="dept.id+'_'+item.id" >{{item.name}}</li>
              </ul>
            </el-tab-pane>
            <!--
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
            <el-tab-pane name="second">
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
            <el-tab-pane name="third">
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
            -->
            <el-tab-pane name="more">
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
    data() {
      return {
        departments:[],
        defaultImgUrl: "../../../static/img/ks-1.jpg",
        defaultActive: 'first',
        defaultUrl:"",
        liClass:{
         /* "border":"2px solid green"*/
        },
      }
    },
    methods: {
      selectDeptStyle(item, dept){
        let color = dept.color
        let id = dept.id + "_"+item.id
        let li = this.$refs[id][0];
        li.style.color = "#FFFFFF"
        li.style.background = color
      },
      outDeptStyle(item,dept){
        let id = dept.id + "_" + item.id
        let li = this.$refs[id][0]
        li.style.color = "#000"
        li.style.background = "#FFFFFF"
      },

      initDefault(dept){
        let first = dept || this.departments[0]
        this.defaultImgUrl = first.imgUrl
        this.defaultActive = first.id
        this.defaultUrl = first.url
        this.liClass = {}
        this.liClass.border = "1px solid " + first.color
      },

      initDepartments(){
        let url = "/dept"
        let params = {}
        this.$api.department.findDepartments(params).then(res=>{
          this.departments = res.data
          this.initDefault()
        }).catch(err=>{

        })

      },
      getDefaultDepartment(id){
         let rt = {}
         let size = this.departments.length
         for(let i = 0; i < size; i++){
           let dept = this.departments[i]
           if(dept.id == id){
             rt = dept
             break
           }
         }
         return rt
      },
      findMoreDepartments(){
        alert("更多")
      },
      handleClick(tab, event) {
        //console.log(tab, event);
        let id = tab.name
        if(id != "more"){
          let dept = this.getDefaultDepartment(id)
          this.initDefault(dept)
        } else {
          this.findMoreDepartments()
        }
      },

    },
    created() {
      this.initDepartments()
    },
    beforeCreate() {


    },
    watch: {

    },
    mounted() {

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

  .ksdh-content {
    width: 100%;
    padding: 0;
  }

  .ksdh-tab {
    width: 100%;
  }

  .ksdh-tab li {
    float: left;
    margin-left: 15px;
    cursor: pointer;
    border: 1px solid #cccccc;
    border-radius: 5px;
    /*behavior: url(css/PIE.htc);*/
    width: 100%;
    height: 38px;
    line-height: 38px;
    font-size: 14px;
    margin-bottom: 14px;
  }

  @media only screen and (max-width: 768px){
    .ksdh-tab li {
      width: 48%;
      margin: 0 0 0.2rem;
      height: 31px;
      line-height: 31px;
      font-size: 0.24rem;
    }
  }
  @media only screen and (min-width: 1279px){
    .ksdh-tab li {
      font-size: 12px;
      line-height: 31px;
      height: 31px;
      width: 123px;
    }
  }


  .tabs {
    font-size: 18px;
  }

</style>
