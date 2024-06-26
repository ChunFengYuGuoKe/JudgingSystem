const state = {
    problems: []
};

const mutations = {
    setProblems(state, payload) {
        state.problems = payload;
    },
};

const actions = {
    setProblems({
        commit
    }, payload) {
        // console.log('setProblems', payload);
        commit('setProblems', payload);
    },
    fetchProblems({
        commit
    }, payload) {
        // console.log('fetchProblems:', payload);
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
                    commit('setProblems', data.data);
                } else {
                    this.$message.error(data.msg);
                }
            })
            .catch(error => {
                // 请求失败，处理错误
                console.error('Error fetching data:', error);
                commit('setProblems', null);
            });
    }
};


export default {
    namespaced: true,
    state,
    mutations,
    actions,
}