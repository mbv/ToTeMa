angular
    .module('demo')
    .controller('EmployeesListController', EmployeesListController);

function EmployeesListController($controller) {
    var self = this;
    $controller('EntityListController', {resourceName: 'users', self:self});
}
