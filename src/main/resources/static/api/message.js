import Vue from 'vue'
const message = Vue.resource('/message/{messageId}');

export default {
    get: ()=> message.get(),
    getById: messageId => message.get({messageId}),
    add: messageId => message.save({}, messageId),
    remove: messageId => message.remove({messageId})
}