angular
    .module('app')
    .factory('ProductTypeFactory', ProductTypeFactory);

function ProductTypeFactory($resource) {
    return $resource('api/product-types/:id', { id: '@id' }, {
        update: { method: 'PUT' },
        delete: { method: 'DELETE' }
    });
}
