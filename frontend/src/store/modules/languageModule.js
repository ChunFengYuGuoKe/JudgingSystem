const state = {
    selectedLanguage: null,
    languages: null
};

const mutations = {
    setSelectedLanguage(state, index) {
        state.selectedLanguage = index;
    },
    setLanguages(state, payload) {
        state.languages = payload.payload.map(item => ({
            value: item.id,
            label: item.name
        }));
        state.selectedLanguage = state.languages[0].value;
    },
};

const actions = {
    setPlugins({
        commit
    }, payload) {
        // console.log("调用setPlugins方法", payload);
        commit('setLanguages', payload);
        // router.push('/homepage');
    }
};

export default {
    namespaced: true,
    state,
    mutations,
    actions,
}