import { createStore } from 'vuex';
import userModule from './modules/userModule';
import ipModule from './modules/ipModule';

const store = createStore({
  modules: {
    user: userModule,
    ip: ipModule,
  },
});

export default store;
