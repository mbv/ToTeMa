angular
    .module('demo')
    .controller('PersonsListController',PersonsListController);

function PersonsListController(Person, $uibModal) {
    var self = this;

    self.people = [];
    self.isUpdatingChosen = false;

    self.addPerson = addPerson;
    self.deletePerson = deletePerson;
    self.editPerson = editPerson;

    refresh();

    function refresh() {
        Person.query().$promise.then(function (result) {
            self.people = result;
        });
    }

    function addPerson() {
        openUserModal();
    }

    function deletePerson(person) {
        Person.delete(person, refresh);
    }

    function editPerson (person) {
        openUserModal(person);
    }

    function openUserModal(person) {
        var modalInstance = $uibModal.open({
            templateUrl: 'userModal.html',
            controller: 'PersonModalController as self',
            resolve: {
                person: function () {
                    return angular.copy(person);
                }
            }
        });
        modalInstance.result.then(refresh);
    }
}
