angular
    .module('app')
    .factory('EmployeeFactory', EmployeeFactory);

function EmployeeFactory($resource) {
    return $resource('api/employees/:id', { id: '@id' }, {
        update: { method: 'PUT' },
        delete: { method: 'DELETE' }
    });
}
