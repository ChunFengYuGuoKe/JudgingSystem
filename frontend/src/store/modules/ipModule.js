const state = {
    backendIP: 'http://172.17.55.197:8081',
};

const getters = {
    loginIP: state => `${state.backendIP}/login`,
    pluginsIP: state => `${state.backendIP}/plugin`,
};

export default {
    namespaced: true,
    state,
    getters
};