import Vue from 'vue'
import App from './App.vue'
import VueResource from 'vue-resource';
import VueRouter from 'vue-router';

import { routes } from './routes';

import VueHighlightJS from 'vue-highlightjs'
Vue.use(VueHighlightJS)


import 'highlight.js/styles/default.css';


Vue.use(VueRouter);

const router = new VueRouter({
  routes,
  mode: 'history' //sem isso seria hash mode. com isso precisa redirecionar as requests no backend caso esteja deployado no msm server
});

Vue.use(VueResource);

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
