import {
  createStore
} from 'vuex';
import userModule from './modules/userModule';
import ipModule from './modules/ipModule';
import menuModule from './modules/menuModule';
import languageModule from './modules/languageModule';
import problemModule from './modules/problemModule';
import submissionModule from './modules/submissionModule';
import rankModule from './modules/rankModule';
import classModule from './modules/classModule';

const store = createStore({
  modules: {
    user: userModule,
    ip: ipModule,
    menu: menuModule,
    language: languageModule,
    problem: problemModule,
    submission: submissionModule,
    class: classModule,
    rank: rankModule,
  },
});

export default store;