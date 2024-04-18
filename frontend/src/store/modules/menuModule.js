const state = {
    selectedMenuItemIndex: 1
};

const mutations = {
    setSelectedMenuItemIndex(state, index) {
        state.selectedMenuItemIndex = index;
    }
};

export default {
    namespaced: true,
    state,
    mutations,
}