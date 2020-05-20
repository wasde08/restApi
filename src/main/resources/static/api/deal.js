import Vue from 'vue'
const deal = Vue.resource('/deal/{dealId}');

export default {
    get: () => deal.get(),
    getById: dealId => deal.get({dealId}),
    add: dealId => deal.save({}, dealId),
    update: dealId => deal.update({id: dealId.id}, dealId),
    remove: dealId => deal.remove({dealId}),
}
