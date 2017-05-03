angular
    .module('app')
    .factory('OrderFactory', OrderFactory);

function OrderFactory($resource) {
    return $resource('api/orders/:id', {id: '@id'}, {
        update: {method: 'PUT'},
        delete: {method: 'DELETE'},
        getProductLists: {
            method: 'GET',
            url: 'api/orders/:id/product-lists',
            isArray: true
        }
    });
}
