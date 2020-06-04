import http from '../http'


// 保存
export const save = (data) => {
  return http({
    url: '/notice/add',
    method: 'post',
    data
  })
}

//
export const findNotice = (params) => {
  return http({
    url: '/notices',
    method: 'get',
    params
  })
}
