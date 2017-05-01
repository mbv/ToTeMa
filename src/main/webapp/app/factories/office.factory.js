angular
    .module('app')
    .factory('OfficeFactory', OfficeFactory);

function OfficeFactory($resource) {
    return $resource('api/offices/:id', { id: '@id' }, {
        update: { method: 'PUT' },
        delete: { method: 'DELETE' }
    });
}
