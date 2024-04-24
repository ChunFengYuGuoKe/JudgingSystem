const state = {
    ranks: []
};

const mutations = {
    setRanks(state, payload) {
        state.ranks = payload;
    },
};

const actions = {
    setRanks({
        commit
    }, payload) {
        // console.log('setRanks', payload);
        commit('setRanks', payload);
    },
    fetchRanks({
        commit
    }, payload) {
        fetch(payload.url, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
            })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Network response was not ok.');
                }
            })
            .then(data => {
                if (data.code % 10 === 1) {
                    commit('setRanks', data.data);
                } else {
                    this.$message.error(data.msg);
                }
            })
            .catch(error => {
                // 请求失败，处理错误
                console.error('Error fetching data:', error);
                commit('setRanks', null);
            });
    }
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
}