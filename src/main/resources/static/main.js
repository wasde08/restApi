import Vue from 'vue'
import Vuetify from 'vuetify'
import 'api/resource'
import router from 'router/router.js'
import App from 'pages/app.vue'
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuetify)


new Vue({
    el: '#app',
    router,
    render: a => a(App)
})