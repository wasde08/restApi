import Vue from 'vue'
const seller = Vue.resource('/seller/{prodId}');

export default {
    get: () => seller.get(),
    getById: prodId => seller.get({prodId}),
    add: prodId => seller.save({}, prodId),
}