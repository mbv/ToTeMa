angular
    .module('app')
    .factory('CountryFactory', CountryFactory);

function CountryFactory($resource) {
    return $resource('api/countries/:id', { id: '@id' }, {
        update: { method: 'PUT' },
        delete: { method: 'DELETE' }
    });
}
