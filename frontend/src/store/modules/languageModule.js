// import problemModule from './problemModule'

const state = {
    selectedLanguage: null,
    languages: null
};

const mutations = {
    // updateLanguage(state, Language) {
    //     console.log("Step2");
    //     state.selectedLanguage = Language;
    //     console.log("值发生变化：", Language);
    // },
    setSelectedLanguage(state, payload) {
        // console.log("Step2:", payload.language);
        state.selectedLanguage = payload.language;

    //     state.selectedLanguage = payload.index;
    //     console.log("值发生变化：", payload.index);
    //     console.log("访问地址:", `${payload.ip}/${payload.index}`);

    //     console.log('languageModule中的fetchProblems参数:', `${payload.ip}/${payload.index}`);
    //     // this.$store.dispatch('problemModule/fetchProblems', {
    //     //     url: `${payload.ip}/${payload.index}`
    //     // });
    //     problemModule.actions.fetchProblems({
    //         url: `${payload.ip}/${payload.index}`,
    //     });
    //     // problemModule.actions.fetchProblems(payload.index);
    },
    setLanguages(state, payload) {
        state.languages = payload.map(item => ({
            value: item.id,
            label: item.name
        }));
    },
};

const actions = {
    setLanguages({
        commit
    }, payload) {
        commit('setLanguages', payload);
    },
    setSelectedLanguage({
        commit,
        rootGetters,
        rootState,
    }, payload) {
        // console.log("Step1 payload:", payload);
        commit('setSelectedLanguage', {
            language: payload,
        });
        this.dispatch('problem/fetchProblems', {
            url:`${rootGetters['ip/homeworkIP']}/${payload}`,
        });
        // console.log("submission url:", `${rootGetters['moduleB/submitIP']}/${rootState.user.username}/${payload}`);

        // console.log("submission url:", `${rootGetters['ip/submitIP']}/${rootState.user.username}/${payload}`);
        this.dispatch('submission/fetchSubmissions', {
            url:`${rootGetters['ip/submitIP']}/${rootState.user.username}/${payload}`,
        })
    },
};

export default {
    namespaced: true,
    state,
    mutations,
    actions,
}