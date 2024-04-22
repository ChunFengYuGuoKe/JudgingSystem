const state = {
    backendIP: 'http://172.17.55.197:8081',
};

const getters = {
    loginIP: state => `${state.backendIP}/login`,
    pluginsIP: state => `${state.backendIP}/plugin`,
    homeworkIP: state => `${state.backendIP}/homework`,
    submitIP: state => `${state.backendIP}/submits`,
};

export default {
    namespaced: true,
    state,
    getters
};