angular
    .module('app')
    .controller('OfficeModalController', OfficeModalController);

function OfficeModalController($scope, $uibModalInstance, OfficeFactory, CountryFactory, office) {
    var self = this;

    self.office = office;
    if (!!office) {
        $scope.temporaryCountry = self.office.country.id;
    }
    self.saveOffice = saveOffice;
    self.cancel = cancel;
    self.updateMode = !!self.office;

    CountryFactory.query().$promise.then(function (result) {
        self.countries = result;
        $scope.$watch('temporaryCountry', function (newValue, oldValue) {
            if (newValue) {
                angular.forEach(self.countries, function (country) {
                    if (country.id == newValue && !!self.office) {
                        self.office.country = country;
                    }
                });
            }
        });
    });

    function saveOffice() {
        if (self.updateMode) {
            OfficeFactory.update(self.office, $uibModalInstance.close);
        } else {
            OfficeFactory.save(self.office, $uibModalInstance.close);
        }
    }

    function cancel() {
        $uibModalInstance.dismiss('cancel');
    }
}