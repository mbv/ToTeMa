angular
    .module('app')
    .factory('UserFactory', UserFactory);

function UserFactory($resource) {
    return $resource('api/employees/:id', { id: '@id' }, {
        update: { method: 'PUT' },
        me: {
            method: 'GET',
            url: 'api/employees/me',
        }
    });
}
