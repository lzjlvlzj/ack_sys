<template>
  <div id="page-index">
    <page-header></page-header>
    <page-content></page-content>
    <page-footer></page-footer>
  </div>
</template>

<script>
  import pageHeader from './PageHeader'
  import pageContent from './PageContent'
  import pageFooter from './PageFooter'

  export default {
    name: "Index",
    components: {
      'pageHeader': pageHeader,
      'pageContent': pageContent,
      'pageFooter': pageFooter
    }
  }
</script>

<style scoped>
   #page-index{

   }
</style>
