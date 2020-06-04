// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en'
import store from './store'
import api from './http'
import  '@/mock/index'
import  '../static/css/base.css'
import BaiduMap from 'vue-baidu-map'
Vue.config.productionTip = false
Vue.use(ElementUI, { locale })
Vue.use(api)
Vue.use(BaiduMap,{ak:"VEVXv35TGb5bSIqqcpmvIxEf7rbHc8Yr"})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
