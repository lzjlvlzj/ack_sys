import Vue from 'vue'
import Router from 'vue-router'
import Index from '../views/Index'
import api from '@/http/api'
import store from '@/store'
import NotFound from '@/views/Error/404'
import Home from '@/views/Home/index'
import Detail from '@/views/core/Detail'

let indexPage = [{
  "id":1,
  "name": "首页",
  "url": "/",
  "icon": "",
  "index":"1",
  "children":[]
}]

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '',
      name: '首页',
      component: Index,
      children: [
        {
          path: '/',
          name: '首页',
          component: Home,
          meta: {
            icon: 'fa fa-home fa-lg',
            index: 0,
            keepAlive: true
          },
          children:[]
        }
      ]

    },
    {
      path: '',
      redirect: '/'
    },
    {
      path: '/404',
      name: 'notFound',
      component: NotFound
    },
    {
      path: '/detail/:id',
      name: 'detail',
      component: Detail
    }
  ]
})

router.beforeEach((to, from, next) => {
  //console.log(from)
  //console.log(to)
  addDynamicMenuAndRoutes(to, from)
  next()

})

router.selfAddRoutes = function (params){
  router.matcher = new Router({mode: 'history'}).matcher;
  router.addRoutes(params)
}

/**
 * 添加动态(菜单)路由
 * @param {*} menuList 菜单列表
 * @param {*} routes 递归创建的动态(菜单)路由
 */

function addDynamicRoutes(menuList = [], routes = []) {
 // console.log(menuList)
  var temp = []
  for (var i = 0; i < menuList.length; i++) {
    let currentMenu = menuList[i]
    //console.log("currentMenu = ")
    //console.log(currentMenu)
    if (currentMenu.children && currentMenu.children.length >= 1) {
      temp = temp.concat(currentMenu.children)
    } else if (currentMenu.url && /\S/.test(currentMenu.url)) {
      currentMenu.url = currentMenu.url.replace(/^\//, '')
      // 创建路由配置
      var route = {
        path: currentMenu.url,
        component: null,
        name: currentMenu.name,
        meta: {
          icon: currentMenu.icon,
          index: currentMenu.id
        }
      }
      try {
        // 根据菜单URL动态加载vue组件，这里要求vue组件须按照url路径存储
        // 如url="sys/user"，则组件路径应是"@/views/sys/user.vue",否则组件加载不到
        let array = currentMenu.url.split('/')
        let url = ''
        for (let i = 0; i < array.length; i++) {
          url += array[i].substring(0, 1).toUpperCase() + array[i].substring(1) + '/'
        }
        url = url.substring(0, url.length - 1)
        //console.log("url = " + url)
        route['component'] = resolve => require([`@/views/${url}`], resolve)
      } catch (e) {
       // console.log(e)
      }
      routes.push(route)
    }
  }
  if (temp.length >= 1) {
    addDynamicRoutes(temp, routes)
  } else {
    //console.log('动态路由加载...')
    //console.log(routes)
    //console.log('动态路由加载完成.')
  }
  return routes

}
/*
*
* 处理路由到本地直接指定页面组件的情况
* 比如'代码生成'是要求直接绑定到'Generator'页面组件
*/
function handleStaticComponent(router, dynamicRoutes) {
  let orignalRoute =  router.options.routes[0].children

  for(var i = 0 ; i < dynamicRoutes.length; i++){
    var rt = dynamicRoutes[i];
    if(rt.name != '首页'){
      orignalRoute.push(rt);
    }

  }
}


function addDynamicMenuAndRoutes(to, from) {

  if (store.state.app.menuRouteLoaded) {
    //console.log('动态菜单和路由已经存在.')
    return
  }
  api.menu.findNav().then(res => {
    let menuData =  indexPage.concat(res.data)
    // 添加动态路由
    let dynamicRoutes = addDynamicRoutes(res.data)
    // 处理静态组件绑定路由
    handleStaticComponent(router, dynamicRoutes)
    router.addRoutes(router.options.routes)

    // 保存加载状态
    store.commit('menuRouteLoaded', true)
    // 保存菜单树
    store.commit('setNavTree', res.data)
  }).catch(function (err) {
    console.log("---------------")
    console.log(err)
  })
}


export default router
