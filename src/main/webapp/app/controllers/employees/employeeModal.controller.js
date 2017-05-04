angular
    .module('app')
    .controller('EmployeeModalController', EmployeeModalController);

function EmployeeModalController($scope, $uibModalInstance, EmployeeFactory, OfficeFactory, employee) {
    var self = this;

    self.employee = employee;
    if (!!employee) {
        $scope.temporaryOffice = self.employee.office.id;
    }
    self.saveEmployee = saveEmployee;
    self.cancel = cancel;
    self.updateMode = !!self.employee;

    OfficeFactory.query().$promise.then(function (result) {
        self.offices = result;
        if (!self.employee && (self.offices.length > 0)) {
            self.employee = {};
            self.employee.office = self.offices[0];
            $scope.temporaryOffice = self.employee.office.id;
        }
        $scope.$watch('temporaryOffice', function (newValue, oldValue) {
            if (newValue) {
                angular.forEach(self.offices, function (office) {
                    if (office.id == newValue && !!self.employee) {
                        self.employee.office = office;
                    }
                });
            }
        });
    });

    function saveEmployee() {
        if (self.updateMode){
            EmployeeFactory.update(self.employee, $uibModalInstance.close);
        } else {
            EmployeeFactory.save(self.employee, $uibModalInstance.close);
        }
    }

    function cancel() {
        $uibModalInstance.dismiss('cancel');
    }
}