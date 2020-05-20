import Vue from 'vue'
const reg = Vue.resource('/product/{prodId}');

export default {
    get: () => reg.get(),
    getById: prodId => reg.get({prodId}),
    add: prodId => reg.save({}, prodId),
    remove: prodId => reg.remove({prodId}),
}
