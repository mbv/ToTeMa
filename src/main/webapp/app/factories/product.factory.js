angular
    .module('app')
    .factory('ProductFactory', ProductFactory);

function ProductFactory($resource) {
    return $resource('api/products/:id', { id: '@id' }, {
        update: { method: 'PUT' },
        delete: { method: 'DELETE' }
    });
}
