angular
    .module('app')
    .controller('ConversionRateModalController', ConversionRateModalController);

function ConversionRateModalController($scope, $uibModalInstance, ConversionRateFactory, CountryFactory, conversionRate) {
    var self = this;

    self.conversionRate = conversionRate;
    if (!!conversionRate) {
        $scope.temporaryCountry = self.conversionRate.country.id;
    }
    self.saveConversionRate = saveConversionRate;
    self.cancel = cancel;
    self.updateMode = !!self.conversionRate;

    CountryFactory.query().$promise.then(function (result) {
        self.countries = result;
        $scope.$watch('temporaryCountry', function (newValue, oldValue) {
            if (newValue) {
                angular.forEach(self.countries, function (country) {
                    if (country.id == newValue && !!self.conversionRate) {
                        self.conversionRate.country = country;
                    }
                });
            }
        });
    });

    function saveConversionRate() {
        if (self.updateMode){
            ConversionRateFactory.update(self.conversionRate, $uibModalInstance.close);
        } else {
            ConversionRateFactory.save(self.conversionRate, $uibModalInstance.close);
        }
    }

    function cancel() {
        $uibModalInstance.dismiss('cancel');
    }
}