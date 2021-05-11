import Vue from 'vue'
import router from './router'
import axios from 'axios';
import App from '@/App'
Vue.config.productionTip = false
Vue.prototype.$axios = axios;
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components:{App},
  template: '<App/>'
})
