import axios from '../axios'

/*
 * 菜单管理模块
 */

// 保存
/*
export const save = (data) => {
  return axios({
    url: '/portal/carousel/add',
    method: 'post',
    data
  })
}
*/

// 保存
export const update = (data) => {
  return axios({
    url: '/portal/carousel/edit',
    method: 'patch',
    data
  })
}
// 删除
export const batchDelete = (data) => {
  return axios({
    url: '/portal/carousel/delete',
    method: 'delete',
    data
  })
}

// 查找菜单
export const findPage = (data) => {
  return axios({
    url: '/portal/carousel/findPage',
    method: 'post',
    data
  })
}
