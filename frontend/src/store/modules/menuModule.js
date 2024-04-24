const state = {
    selectedMenuItemIndex: 1
};

const mutations = {
    setSelectedMenuItemIndex(state, index) {
        state.selectedMenuItemIndex = index;
    }
};

const actions = {
    setSelectedMenuItemIndex({
        commit
    }, payload) {
        commit('setSelectedMenuItemIndex', payload);
    },
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}