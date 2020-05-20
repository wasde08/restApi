import Vue from 'vue'
import VueRouter from 'vue-router'
import loginPage from 'pages/loginPage.vue'
import User from 'pages/User.vue'
import Seller from 'pages/Seller.vue'
import Main from 'pages/Main.vue'

Vue.use(VueRouter)

const routes = [
    {path: '/', component: Main},
    {path: '/login', component: loginPage},
    {path: '/user', component: User},
    {path: '/sellers', component: Seller},
    {path: '/**', component: Main}

]

export default new VueRouter({
    mode: 'history',
    routes
})