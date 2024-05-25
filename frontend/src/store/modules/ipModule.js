const getters = {
    loginIP: () => `/login`,
    pluginsIP: () => `/plugin`,
    homeworkIP: () => `/homework`,
    homeworkAddIP: () => `/homework/add`,
    submitIP: () => `/submits`,
    classIP: () => `/class`,
    studentIP: () => `/students`,
    rankIP: () => '/rank',
};

export default {
    namespaced: true,
    getters
};