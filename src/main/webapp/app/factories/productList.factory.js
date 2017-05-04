angular
    .module('app')
    .factory('ProductListFactory', ProductListFactory);

function ProductListFactory($resource) {
    return $resource('api/product-lists/:id', { id: '@id' }, {
        update: { method: 'PUT' },
        delete: { method: 'DELETE' }
    });
}
