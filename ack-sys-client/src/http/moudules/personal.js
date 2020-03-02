import axios from '../axios'

/*
 * 个人管理模块
 */

// 保存
export const edit = (data) => {
  return axios({
    url: '/personal/edit',
    method: 'patch',
    data
  })
}

// 保存
export const changePassword = (data) => {
  return axios({
    url: '/personal/changePwd',
    method: 'patch',
    data
  })
}

// 保存
export const findUserByName = (params) => {
  return axios({
    url: '/personal/findUserByName',
    method: 'get',
    params
  })
}

