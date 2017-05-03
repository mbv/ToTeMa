angular
    .module('app')
    .controller('ConversionRateListController', ConversionRateListController);

function ConversionRateListController($uibModal, ConversionRateFactory, $state) {
    var self = this;

    self.conversionRates = [];
    self.isUpdatingChosen = false;

    self.title = 'ConversionRates';
    self.addConversionRate = addConversionRate;
    self.deleteConversionRate = deleteConversionRate;
    self.editConversionRate = editConversionRate;

    refresh();

    function refresh() {
        ConversionRateFactory.query().$promise.then(function (result) {
            self.conversionRates = result;
        });
    }

    function addConversionRate() {
        openModal();
    }

    function deleteConversionRate(conversionRate) {
        ConversionRateFactory.delete(conversionRate, refresh);
    }

    function editConversionRate(conversionRate) {
        openModal(conversionRate);
    }

    function openModal(conversionRate) {
        var modalInstance = $uibModal.open({
            controller: 'ConversionRateModalController as self',
            templateUrl: 'templates/crud/conversionRates/modal.html',
            resolve: {
                conversionRate: function () {
                    return angular.copy(conversionRate);
                }
            }
        });
        modalInstance.result.then(refresh);
    }
}