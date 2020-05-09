import Vue from 'vue'
import Router from 'vue-router'
import Index from '../view/Index'


Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index,

    },
    {
      path: '/',
      redirect : '/index'
    },
    {
      path: '',
      redirect : '/index'
    },
    {
      path:'*',
      redirect :'/index'
    }
  ]
})

export default router
