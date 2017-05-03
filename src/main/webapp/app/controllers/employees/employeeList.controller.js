angular
    .module('app')
    .controller('EmployeeListController', EmployeeListController);

function EmployeeListController($uibModal, EmployeeFactory, $state) {
    var self = this;

    self.employees = [];
    self.isUpdatingChosen = false;

    self.title = 'Employees';
    self.addEmployee = addEmployee;
    self.deleteEmployee = deleteEmployee;
    self.editEmployee = editEmployee;

    refresh();

    function refresh() {
        EmployeeFactory.query().$promise.then(function (result) {
            self.employees = result;
        });
    }

    function addEmployee() {
        openModal();
    }

    function deleteEmployee(employee) {
        EmployeeFactory.delete(employee, refresh);
    }

    function editEmployee(employee) {
        openModal(employee);
    }

    function openModal(employee) {
        var modalInstance = $uibModal.open({
            controller: 'EmployeeModalController as self',
            templateUrl: 'templates/crud/employees/modal.html',
            resolve: {
                employee: function () {
                    return angular.copy(employee);
                }
            }
        });
        modalInstance.result.then(refresh);
    }
}