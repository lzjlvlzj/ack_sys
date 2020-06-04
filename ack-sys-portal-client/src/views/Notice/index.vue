<template>
  <div class="notice">
    <div v-if="isList">
      <notice-list :infoList="infoList"></notice-list>
    </div>
    <div v-else>
      <notice-detail></notice-detail>
    </div>
  </div>
</template>

<script>
  import NoticeList from "./List"
  import NoticeDetail from "./Detail"

  export default {
    name: "Notice",
    data(){
      return {
        isList: true,
        currentPage3: 1,
        infoList:{
          title:"",
          data:[]
        },
        infoDetail:{},
        cMenu:{"id":1}
      }
    },
    components: {
      noticeList : NoticeList,
      noticeDetail: NoticeDetail
    },
    methods:{

      initList(req){
        var request = req || {}
        request.pageSize = 10
        this.$api.notice.findNotice(request).then(res => {
          this.infoList = res.data
          //this.$store.commit("setNavTree", res.data)
        }).catch(err => {
          //console.log(err)
        })

      }
    },
    created(){

    },
    mounted() {
      this.initList()
    }
  }
</script>

<style scoped>



</style>
