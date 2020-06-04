import Vue from 'vue'
import Vuex from 'vuex'
import menu from './modules/menu'
import app from './modules/app'


Vue.use(Vuex)

const store = new Vuex.Store({
  modules : {
    menu,
    app
  }
})

export default store
