import axios from '../axios'

/*
 * 字典管理模块
 */

// 保存
export const save = (data) => {
    return axios({
        url: '/dict/add',
        method: 'post',
        data
    })
}


// 保存
export const edit = (data) => {
  return axios({
    url: '/dict/edit',
    method: 'patch',
    data
  })
}
// 删除
export const batchDelete = (data) => {
    return axios({
        url: '/dict/delete',
        method: 'post',
        data
    })
}
// 删除
export const deleteById = (data) => {
  return axios({
    url: '/dict/delete',
    method: 'delete',
    data
  })
}
// 分页查询
export const findPage = (data) => {
    return axios({
        url: '/dict/findPage',
        method: 'post',
        data
    })
}
