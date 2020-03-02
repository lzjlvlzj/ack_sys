import axios from '../axios'

/*
 * 用户详细信息管理模块
 */

// 保存
export const save = (data) => {
    return axios({
        url: '/user/add',
        method: 'post',
        data
    })
}

// 保存
export const edit = (data) => {
  return axios({
    url: '/user/edit',
    method: 'patch',
    data
  })
}
//授权
export const grant = (data) => {
  return axios({
    url: '/user/grant',
    method: 'patch',
    data
  })
}
//重置密码
export const restPassword = (data) => {
  return axios({
    url: '/user/restPassword',
    method: 'patch',
    data
  })
}
//重置密码
export const ableUser = (data) => {
  return axios({
    url: '/user/able',
    method: 'patch',
    data
  })
}

// 删除
export const batchDelete = (data) => {
    return axios({
        url: '/user/batchDelete',
        method: 'delete',
        data
    })
}

// 删除
export const deleteById = (data) => {
  return axios({
    url: '/user/delete',
    method: 'delete',
    data
  })
}

// 分页查询
export const findPage = (data) => {
    return axios({
        url: '/user/findPage',
        method: 'post',
        data
    })
}
// 查找用户的菜单权限标识集合
export const findPermissions = (params) => {
    return axios({
        url: '/user/findPermissions',
        method: 'get',
        params
    })
}

// 查询用户对应的角色
export const findUserRoles = (params) =>{
  return axios({
        url: '/user/findUserRoles',
        method: 'get',
        params
  })
}
