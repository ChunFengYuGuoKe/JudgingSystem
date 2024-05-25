const state = {
    classes: []
};

const mutations = {
    setClasses(state, payload) {
        state.classes = payload;
    },
};

const actions = {
    setClasses({
        commit
    }, payload) {
        commit('setClasses', payload);
    },
    fetchClasses({
        commit
    }, payload) {
        console.log(payload.url);
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
                    commit('setClasses', data.data);
                } else {
                    this.$message.error(data.msg);
                }
            })
            .catch(error => {
                // 请求失败，处理错误
                console.error('Error fetching data:', error);
                commit('setClasses', null);
            });
    }
}

export default {
    namespaced: true,
    state,
    mutations,
    actions,
}