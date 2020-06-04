import http from '../http'


// 更多
export const findMore = (data) => {
  return http({
    url: '/menu/add',
    method: 'post',
    data
  })
}

//
export const findContentSummary = (data) => {
  return http({
    url: '/content/summary',
    method: 'post',
    data
  })
}
