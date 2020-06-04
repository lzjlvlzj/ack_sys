import http from '../http'


// 保存
export const save = (data) => {
  return http({
    url: '/dept/add',
    method: 'post',
    data
  })
}

//
export const findDepartments = (params) => {
  return http({
    url: '/dept',
    method: 'get',
    params
  })
}
