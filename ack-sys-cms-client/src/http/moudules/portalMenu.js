import axios from '../axios'

/*
 * 菜单管理模块
 */

// 保存
export const save = (data) => {
  return axios({
    url: '/portal/menu/add',
    method: 'post',
    data
  })
}
// 保存
export const update = (data) => {
  return axios({
    url: '/portal/menu/edit',
    method: 'patch',
    data
  })
}
// 删除
export const batchDelete = (data) => {
  return axios({
    url: '/portal/menu/delete',
    method: 'delete',
    data
  })
}

// 查找菜单
export const findPage = (data) => {
  return axios({
    url: '/portal/menu/findPage',
    method: 'post',
    data
  })
}

// 查找菜单
export const findAll = (params) => {
  return axios({
    url: '/portal/menu/findAll',
    method: 'get',
    params
  })
}
