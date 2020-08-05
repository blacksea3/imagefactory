import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'

import Header from "./components/Header";
import Footer from "./components/Footer";

Vue.component("myHeader", Header)
Vue.component("myFooter", Footer)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
