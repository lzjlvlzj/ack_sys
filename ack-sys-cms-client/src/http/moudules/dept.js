import axios from '../axios'

/*
 * 机构管理模块
 */

// 保存
export const save = (data) => {
    return axios({
        url: '/dept/add',
        method: 'post',
        data
    })
}
// 修改
export const edit = (data) => {
  return axios({
    url: '/dept/edit',
    method: 'patch',
    data
  })
}
// 删除
export const batchDelete = (data) => {
    return axios({
        url: '/dept/delete',
        method: 'delete',
        data
    })
}
// 查询机构树
export const findDeptTree = () => {
    return axios({
        url: '/dept/tree',
        method: 'get'
    })
}
