const state = {
    submissions: []
};

const mutations = {
    setSubmissions(state, payload) {
        state.submissions = payload;
    },
};

const actions = {
    setSubmissions({
        commit
    }, payload) {
        console.log('setSubmissions', payload);
        commit('setSubmissions', payload);
    },
    fetchSubmissions({
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
                    commit('setSubmissions', data.data);
                } else {
                    this.$message.error(data.msg);
                }
            })
            .catch(error => {
                // 请求失败，处理错误
                console.error('Error fetching data:', error);
                commit('setSubmissions', null);
            });
    }
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
}