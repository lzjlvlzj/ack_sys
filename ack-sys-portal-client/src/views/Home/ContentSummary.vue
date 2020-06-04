<template>
  <el-row :gutter="20" id="content-summary" >
    <el-col :lg="8" :xl="8" v-for="summary in contents" :key="summary.id">
      <el-card shadow="hover" :body-style="{padding:'0px'}" class="grid-content">
        <img :src="summary.sourceImgUrl"
             class="card-image">
        <div class="cad-bd">
          <el-row :gutter="20">
            <el-col :lg="12" :xl="12" class="cd-content-title">{{summary.sourceName}}</el-col>
            <el-col :lg="12" :xl="12" class="cd-more"><a>查看更多</a></el-col>
          </el-row>
          <ul class="text item" v-for="item in summary.titles">
            <li><a target="_blank" :href="item.url" class="ellipsis">{{item.title}}</a></li>
          </ul>
        </div>
      </el-card>
    </el-col>
  </el-row>
  <!--
  <el-row :gutter="20" id="content-summary">
    <el-col :lg="8" :xl="8">
      <el-card shadow="hover" :body-style="{padding:'0px', 'min-height':'400px'}" class="grid-content">
        <img :src="noticeImgUrl"
             class="card-image">
        <div class="cad-bd">
          <el-row :gutter="20">
            <el-col :lg="12" :xl="12" class="cd-content-title">医院公告</el-col>
            <el-col :lg="12" :xl="12" class="cd-more"><a>查看更多</a></el-col>
          </el-row>
          <ul class="text item">
            <li><a target="_blank" href="/detail/23298.html" class="ellipsis">北京协和医院2020年五一门诊工作安排</a></li>
            <li><a target="_blank" href="/detail/23295.html" class="ellipsis">北京协和医院试剂采购公告（第35期）</a></li>
            <li><a target="_blank" href="/detail/23237.html" class="ellipsis">北京协和医院试剂采购公告（第34期）</a></li>
          </ul>
        </div>
      </el-card>
    </el-col>
    <el-col :lg="8" :xl="8">
      <el-card shadow="hover" :body-style="{padding:'0px', 'min-height':'400px'}" class="grid-content">
        <img :src="newsImgUrl"
             class="card-image">
        <div class="cad-bd">
          <el-row :gutter="20">
            <el-col :lg="12" :xl="12" class="cd-content-title">医院新闻</el-col>
            <el-col :lg="12" :xl="12" class="cd-more"><a>查看更多</a></el-col>
          </el-row>
          <ul class="text item">
            <li><a target="_blank" href="/detail/23298.html" class="ellipsis">北京协和医院2020年五一门诊工作安排</a></li>
            <li><a target="_blank" href="/detail/23295.html" class="ellipsis">北京协和医院试剂采购公告（第35期）</a></li>
            <li><a target="_blank" href="/detail/23237.html" class="ellipsis">北京协和医院试剂采购公告（第34期）</a></li>
          </ul>
        </div>

      </el-card>
    </el-col>
    <el-col :lg="8" :xl="8">
      <el-card shadow="hover" :body-style="{padding:'0px', 'min-height':'400px'}" class="grid-content">
        <img :src="healthImgUrl" class="card-image">
        <div class="cad-bd">
          <el-row :gutter="20">
            <el-col :lg="12" :xl="12" class="cd-content-title">健康科普</el-col>
            <el-col :lg="12" :xl="12" class="cd-more"><a>查看更多</a></el-col>
          </el-row>
          <ul class="text item">
            <li><a target="_blank" href="/detail/23298.html" class="ellipsis">北京协和医院2020年五一门诊工作安排</a></li>
            <li><a target="_blank" href="/detail/23295.html" class="ellipsis">北京协和医院试剂采购公告（第35期）</a></li>
            <li><a target="_blank" href="/detail/23237.html" class="ellipsis">北京协和医院试剂采购公告（第34期）</a></li>
          </ul>
        </div>
      </el-card>
    </el-col>

  </el-row>
  -->
</template>

<script>
  export default {
    name: "ContentSummary",
    data() {
      return {
        contents:[],
        sourceType:[0,1,2],
        noticeImgUrl: "../../static/img/cs-1.jpg",
        newsImgUrl: "../../static/img/cs-2.jpg",
        healthImgUrl: "../../static/img/cs-3.png",
        cardBody: {
          'padding': '0px;',
          'min-height': '400px;'
        }
      }
    },
    methods: {

      /*初始化摘要信息*/
      initContentSummary(){
        let url = "/content/summary"
        let data = {}
        data.ids = "0,1,2"
        this.$api.contentSummary.findContentSummary(data).then(res=>{
          //console.log(res)
          this.contents = res.data
        }).catch(err=>{

        })

      },
    },
    created() {

    },
    beforeCreate() {


    },
    watch: {

    },
    mounted() {
      this.initContentSummary()
    }

  }
</script>

<style scoped>

  @media only screen and (min-width: 1200px) {
    #content-summary {
      margin-bottom: 20px;
    }
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
