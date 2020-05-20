import Vue from 'vue'
const user = Vue.resource('/client/{prodId}');

export default {
    get: () => user.get(),
    getById: prodId => user.get({prodId}),
    add: prodId => user.save({}, prodId),
}