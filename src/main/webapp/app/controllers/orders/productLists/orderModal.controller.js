angular
    .module('app')
    .controller('OrderModalController', OrderModalController);

function OrderModalController($scope, $uibModalInstance, OrderFactory, OfficeFactory, EmployeeFactory, order) {
    var self = this;

    self.order = order;
    if (!!order) {
        $scope.temporaryOffice = self.order.office.id;
        $scope.temporaryEmployee = self.order.employee.id;
    }
    self.saveOrder = saveOrder;
    self.cancel = cancel;
    self.updateMode = !!self.order;

    OfficeFactory.query().$promise.then(function (result) {
        self.offices = result;
        if (!self.order && (self.offices.length > 0)) {
            self.order = {};
            self.order.office = self.offices[0];
            $scope.temporaryOffice = self.order.office.id;
        }
        $scope.$watch('temporaryOffice', function (newValue, oldValue) {
            if (newValue) {
                angular.forEach(self.offices, function (office) {
                    if (office.id == newValue && !!self.order) {
                        self.order.office = office;
                    }
                });
            }
        });
    });

    EmployeeFactory.query().$promise.then(function (result) {
        self.employees = result;
        if (!self.order && (self.employees.length > 0)) {
            self.order = {};
            self.order.employee = self.employees[0];
            $scope.temporaryEmployee = self.order.employee.id;
        }
        $scope.$watch('temporaryEmployee', function (newValue, oldValue) {
            if (newValue) {
                angular.forEach(self.employees, function (employee) {
                    if (employee.id == newValue && !!self.order) {
                        self.order.employee = employee;
                    }
                });
            }
        });
    });

    function saveOrder() {
        if (self.updateMode){
            OrderFactory.update(self.order, $uibModalInstance.close);
        } else {
            OrderFactory.save(self.order, $uibModalInstance.close);
        }
    }

    function cancel() {
        $uibModalInstance.dismiss('cancel');
    }
}