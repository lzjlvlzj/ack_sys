import http from '../http'



//
export const findDetail = (params) => {
  return http({
    url: '/article/detail',
    method: 'get',
    params
  })
}
