import axios from 'axios'
import qs from 'qs'


/* 创建axios实例*/
const service = axios.create({
 // baseURL: ' http://localhost:8087/',
  withCredentials:true,
  timeout: 15000, /* 请求超时时间*/
})

/*请求开始拦截*/
service.interceptors.request.use(conf => {
    //请求带token
   // conf.headers['Authorization'] = store2('accesstoken')
    return conf
  },
  error => ({ status: 0, msg: error.message })
)

/*响应拦截开始拦截*/
service.interceptors.response.use(
  function(response) {
    //请求正常则返回
    let data;
    // IE9时response.data是undefined，因此需要使用response.request.responseText(Stringify后的字符串)
    if (response.data == undefined) {
      data = JSON.parse(response.request.responseText)
    } else {
      data = response.data
    }
    return data
  },
  function(error) {
    console.log(error)
    // 请求错误则向store commit这个状态变化
    const httpError= {
      hasError:true,
      status:error.response.status,
      statusText:error.response.statusText
    }
    return Promise.reject(error)
  }
)

const httpService = function(options){
  let promise = new Promise((resovle, reject)=>{
    service(options).then(res => {
      resovle(res)
      return false
    }).catch(error => {
      reject(error)
    })
  })
  return promise


}

export default httpService




