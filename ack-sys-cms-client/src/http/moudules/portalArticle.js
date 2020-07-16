import axios from '../axios'

/*
 * 文章管理模块
 */

// 保存
export const save = (data) => {
  return axios({
    url: '/portal/article/add',
    method: 'post',
    data
  })
}
// 保存
export const update = (data) => {
  return axios({
    url: '/portal/article/edit',
    method: 'patch',
    data
  })
}
// 删除
export const batchDelete = (data) => {
  return axios({
    url: '/portal/article/delete',
    method: 'delete',
    data
  })
}

// 查找菜单
export const findPage = (data) => {
  return axios({
    url: '/portal/article/findPage',
    method: 'post',
    data
  })
}
