angular
    .module('demo')
    .controller('PersonModalController', PersonModalController)

function PersonModalController($uibModalInstance, Person, person) {
    var self = this;

    self.person = person;

    self.savePerson = savePerson;
    self.cancel = cancel;
    self.updateMode = !!self.person;

    function savePerson() {
        if (self.updateMode){
            Person.update(self.person, $uibModalInstance.close)
        } else {
            Person.save(self.person, $uibModalInstance.close)
        }
    }

    function cancel() {
        $uibModalInstance.dismiss('cancel');
    }
}