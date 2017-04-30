angular
    .module('app')
    .controller('CountryListController', CountryListController);

function CountryListController($uibModal, CountryFactory, $state) {
    var self = this;

    self.countries = [];
    self.isUpdatingChosen = false;

    self.title = 'Countries';
    self.addCountry = addCountry;
    self.deleteCountry = deleteCountry;
    self.editCountry = editCountry;

    refresh();

    function refresh() {
        CountryFactory.query().$promise.then(function (result) {
            self.countries = result;
        });
    }

    function addCountry() {
        openModal();
    }

    function deleteCountry(country) {
        CountryFactory.delete(country, refresh);
    }

    function editCountry(country) {
        openModal(country);
    }

    function openModal(country) {
        var modalInstance = $uibModal.open({
            controller: 'CountryModalController as self',
            templateUrl: 'templates/crud/countries/modal.html',
            resolve: {
                country: function () {
                    return angular.copy(country);
                }
            }
        });
        modalInstance.result.then(refresh);
    }
}