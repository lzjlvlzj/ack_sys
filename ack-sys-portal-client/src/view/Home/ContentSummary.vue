<template>
  <el-row :gutter="20" id="content-summary">
    <el-col :lg="8" :xl="8">
      <el-card shadow="hover" :body-style="{padding:'0px', 'min-height':'400px'}" class="grid-content">
        <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
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
        <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
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
        <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
             class="card-image">
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
</template>

<script>
  export default {
    name: "ContentSummary",
    data() {
      return {
        cardBody: {
          padding: '0px;',
          'min-height': '400px;'
        }
      }
    },

  }
</script>

<style scoped>

  @media only screen and (min-width: 1200px) {
    #content-summary {
      max-height: 430px;
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
