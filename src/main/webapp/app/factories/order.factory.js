angular
    .module('app')
    .factory('OrderFactory', OrderFactory);

function OrderFactory($resource) {
    return $resource('api/orders/:id', {id: '@id'}, {
        save: {
            method: 'POST',
            url: 'api/orders/dto'
        },
        update: {
            method: 'PUT',
            url: 'api/orders/:id/dto'
        },
        delete: {method: 'DELETE'},
        getProductLists: {
            method: 'GET',
            url: 'api/orders/:id/product-lists',
            isArray: true
        }
    });
}
