angular
    .module('demo')
    .factory('Person', Person);

function Person($resource) {
    return $resource('api/users/:id', { id: '@id' }, { update: { method: 'PUT' } });
}