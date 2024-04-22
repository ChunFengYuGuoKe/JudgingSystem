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
        console.log('setProblems', payload);
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
                return response.json(); // 返回一个 Promise，解析后得到 JSON 数据
            })
            .then(data => {
                // console.log("赋值操作给tableData:", data.data);
                commit('setProblems', data.data);
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