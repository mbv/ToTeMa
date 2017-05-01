angular
    .module('app')
    .controller('OfficeListController', OfficeListController);

function OfficeListController($uibModal, OfficeFactory, $state) {
    var self = this;

    self.offices = [];
    self.isUpdatingChosen = false;

    self.title = 'Offices';
    self.addOffice = addOffice;
    self.deleteOffice = deleteOffice;
    self.editOffice = editOffice;

    refresh();

    function refresh() {
        OfficeFactory.query().$promise.then(function (result) {
            self.offices = result;
        });
    }

    function addOffice() {
        openModal();
    }

    function deleteOffice(office) {
        OfficeFactory.delete(office, refresh);
    }

    function editOffice(office) {
        openModal(office);
    }

    function openModal(office) {
        var modalInstance = $uibModal.open({
            controller: 'OfficeModalController as self',
            templateUrl: 'templates/crud/offices/modal.html',
            resolve: {
                office: function () {
                    return angular.copy(office);
                }
            }
        });
        modalInstance.result.then(refresh);
    }
}