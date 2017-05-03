angular
    .module('app')
    .controller('CountryModalController', CountryModalController);

function CountryModalController($uibModalInstance, CountryFactory, country) {
    var self = this;

    self.country = country;
    self.saveCountry = saveCountry;
    self.cancel = cancel;
    self.updateMode = !!self.country;

    function saveCountry() {
        if (self.updateMode){
            CountryFactory.update(self.country, $uibModalInstance.close);
        } else {
            CountryFactory.save(self.country, $uibModalInstance.close);
        }
    }

    function cancel() {
        $uibModalInstance.dismiss('cancel');
    }
}