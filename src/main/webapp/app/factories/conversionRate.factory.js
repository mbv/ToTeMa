angular
    .module('app')
    .factory('ConversionRateFactory', ConversionRateFactory);

function ConversionRateFactory($resource) {
    return $resource('api/conversion-rates/:id', { id: '@id' }, {
        update: { method: 'PUT' },
        delete: { method: 'DELETE' }
    });
}
