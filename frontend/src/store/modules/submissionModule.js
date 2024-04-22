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
                return response.json(); // 返回一个 Promise，解析后得到 JSON 数据
            })
            .then(data => {
                commit('setSubmissions', data.data);
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