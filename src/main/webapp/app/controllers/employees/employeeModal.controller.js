angular
    .module('app')
    .controller('EmployeeModalController', EmployeeModalController);

function EmployeeModalController($uibModalInstance, EmployeeFactory, employee) {
    var self = this;

    self.employee = employee;
    self.saveEmployee = saveEmployee;
    self.cancel = cancel;
    self.updateMode = !!self.employee;

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