import {
  createStore
} from 'vuex';
import userModule from './modules/userModule';
import ipModule from './modules/ipModule';
import menuModule from './modules/menuModule';
import languageModule from './modules/languageModule';
import problemModule from './modules/problemModule';
import submissionModule from './modules/submissionModule';

const store = createStore({
  modules: {
    user: userModule,
    ip: ipModule,
    menu: menuModule,
    language: languageModule,
    problem: problemModule,
    submission: submissionModule,
  },
});

export default store;