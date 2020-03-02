export default {
    state: {
        userInfo:{
          name : 'ack',
          realName :'疙瘩汤',
          avatar:''
        }, //用户基础信息
        perms: [],  // 用户权限标识集合
    },
    getters: {

    },
    mutations: {
        setUserInfo(state, info){ // 用户信息
          state.userInfo = info
        },
        setPerms(state, perms){  // 用户权限标识集合
          state.perms = perms;
        }
    },
    actions: {
    }
}
