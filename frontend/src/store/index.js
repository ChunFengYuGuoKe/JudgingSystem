import { createStore } from 'vuex';
import userModule from './modules/userModule';
import ipModule from './modules/ipModule';
import menuModule from './modules/menuModule';
import languageModule from './modules/languageModule';

const store = createStore({
  modules: {
    user: userModule,
    ip: ipModule,
    menu: menuModule,
    language: languageModule,
  },
});

export default store;
