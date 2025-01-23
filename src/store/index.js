import { createStore } from 'vuex';

export default createStore({
  state: {
    isAuthenticated: false,
    role: '',  // 'USER', 'MERCHANT', 'ADMIN'
    username: ''
  },
  mutations: {
    login(state, payload) {
      state.isAuthenticated = true;
      state.role = payload.role;
      state.username = payload.username;
    },
    logout(state) {
      state.isAuthenticated = false;
      state.role = '';
      state.username = '';
    }
  },
  actions: {
    login({ commit }, userData) {
      commit('login', userData);
    },
    logout({ commit }) {
      commit('logout');
    }
  },
  getters: {
    isAuthenticated: state => state.isAuthenticated,
    userRole: state => state.role,
    username: state => state.username
  }
});