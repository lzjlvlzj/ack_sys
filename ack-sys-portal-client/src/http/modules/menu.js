import http from '../http'


// 保存
export const save = (data) => {
  return http({
    url: '/menu/add',
    method: 'post',
    data
  })
}

//
export const findNav = (params) => {
  return http({
    url: '/menu/nav',
    method: 'get',
    params
  })
}
