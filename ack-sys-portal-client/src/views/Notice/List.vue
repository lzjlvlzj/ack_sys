<template>
  <el-container class="notice">
    <el-header>
      <span class="title">{{infoList.title}}</span>
    </el-header>
    <el-main>
      <ul class="list">
        <li v-for=" item in infoList.list">
          <span class="kind">[{{item.type}}]</span>
          <!--<a  @click="findDetail(item.id)">{{item.title}}</a>-->
          <router-link target="_blank" :to='item.url'>{{item.title}}</router-link>
          <span class="date">{{item.time}}</span>
        </li>
      </ul>
    </el-main>
    <el-footer>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-size="infoList.pageSize"
        layout="prev, pager, next, jumper"
        :total="infoList.totalRecord">
      </el-pagination>
    </el-footer>
  </el-container>

</template>

<script>
  export default {
    name: "NoticeList",
    props: {
      infoList: {
        type: Object,
      }
    },
    data(){
      return {
        currentPage: 1,
      }
    },
    methods:{
      returnUrl(id){
        return "/detail/" + id
      },
      findDetail(id){
        let data = {}
        data.path = "/detail"
        data.query = {id : id}
        const detail = this.$router.resolve(data)
        window.open(detail.href,'_blank')
      },
      handleSizeChange(){

      },
      handleCurrentChange(currentPage){
        console.log(currentPage)
        let data = ""
        this.infoList = {}
      }
    },
  }
</script>

<style scoped>
  .notice .list {
    margin-bottom: 49px;
    border-top: 1px solid #e8e8e8;
    overflow: hidden;
  }
  .notice .title {
    margin-bottom: 29px;
    border-left: 10px solid #01763a;
    padding-left: 20px;
    font-size: 24px;
    line-height: 40px;
  }
  .notice .list li {
    position: relative;
    padding: 17px 0 14px;
    border-bottom: 1px solid #e8e8e8;
    font-size: 14px;
    line-height: 1.5;
  }
  .notice .list li a {
    width: 80%;
  }
  .notice span.kind {
    float: left;
    width: 10%;
    padding-left: 15px;
    padding-right: 10px;
    text-align: left;
    color: #333;
  }
  .notice span.date {
    float: right;
    width: 10%;
    padding-left: 15px;
    padding-right: 10px;
    text-align: left;
    color: #333;
  }
</style>
