import axios from '../axios'

/*
 * 菜单管理模块
 */

 // 保存
export const save = (data) => {
    return axios({
        url: '/menu/add',
        method: 'post',
        data
    })
}
// 保存
export const update = (data) => {
  return axios({
    url: '/menu/edit',
    method: 'patch',
    data
  })
}
// 删除
export const batchDelete = (data) => {
    return axios({
        url: '/menu/delete',
        method: 'delete',
        data
    })
}
// 查找导航菜单树
export const findNavTree = (params) => {
    return axios({
        url: '/menu/findNavTree',
        method: 'get',
        params
    })
}
// find all
export const findMenuTree = (data) => {
  return axios({
    url: '/menu/findTree',
    method: 'get',
    data
  })
}

// 查询角色菜单集合
export const findRoleMenus = (params) => {
  return axios({
    url: '/role/findRoleMenus',
    method: 'get',
    params
  })
}

// 查找菜单
export const findPage = (data) => {
    return axios({
        url: '/menu/findPage',
        method: 'post',
        data
    })
}
