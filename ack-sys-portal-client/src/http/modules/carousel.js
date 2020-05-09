import http from '../http'


// 保存
export const save = (data) => {
  return http({
    url: '/carousel/add',
    method: 'post',
    data
  })
}

//
export const findCarouselImages = (params) => {
  return http({
    url: '/carousel/images',
    method: 'get',
    params
  })
}
