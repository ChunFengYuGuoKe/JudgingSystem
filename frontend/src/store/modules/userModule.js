import router from '@/router.js';

const state = {
    username: null,
    identity: null, // 当前用户信息
    jwt: null,
    isLoggedIn: false // 用户登录状态
};

const mutations = {
    setUser(state, payload) { // 只能传一个payload值
        // console.log("修改属性", payload.username, payload.identity, payload.jwt);
        state.username = payload.username;
        state.identity = payload.identity;
        state.jwt = payload.jwt;
        state.isLoggedIn = true;
    },
    clearUser(state) {
        state.username = null;
        state.identity = null;
        state.jwt = null;
        state.isLoggedIn = false;

        // 这里可能有一堆Bug，账户登录后退出没想出合理的方法清理残留信息
        this.dispatch('menu/setSelectedMenuItemIndex', 1);
        this.dispatch('problem/setProblems', null);
        // this.dispatch('language/setLanguages', null);
        
    }
};

const actions = {
    loginUser({
        commit
    }, username, identity, jwt) {
        // console.log("调用loginUser方法");
        commit('setUser', username, identity, jwt);
        router.push('/homepage');
    },
    logoutUser({
        commit
    }) {
        // 提交 mutation 清除用户信息
        commit('clearUser');
        router.push('/login');
    }
};

export default {
    namespaced: true,
    state,
    mutations,
    actions
};