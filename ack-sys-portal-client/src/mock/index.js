import Mock from 'mockjs'
import {mockUrl} from '../config/url'

import * as menu from './modules/menu'
import * as carousel from './modules/carousel'

createMockData(menu, true)
createMockData(carousel, true)
function createMockData(mod, isOpen = true) {
  if (isOpen) {
    for (var key in mod) {
      ((res) => {
        let url = mockUrl + res.url
        Mock.mock(new RegExp(url), res.method, (opts) => {
          //console.log("---------")
          //console.log(opts)
          //console.log("---------")
          opts['data'] = opts.body ? JSON.parse(opts.body) : null
          delete opts.body
          //console.log('\n')
          //console.log('%cmock拦截, 请求: ', 'color:blue', opts)
          //console.log('%cmock拦截, 响应: ', 'color:blue', res.data)
          return res.data
        })
      })(mod[key]() || {})
    }
  }
}
