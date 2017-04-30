angular
    .module('app')
    .controller('CountryModalController', CountryModalController);

function CountryModalController($uibModalInstance, CountryFactory, country, toaster) {
    var self = this;

    self.country = country;
    self.saveCountry = saveCountry;
    self.cancel = cancel;
    self.updateMode = !!self.country;
    self.toaster = toaster;

    self.promiseErrorHandler = function (errorResponse) {
        console.log(errorResponse);
        /*if (!!errorResponse.data.errors) {
            for (var fieldName in errorResponse.data.errors) {
                if (errorResponse.data.errors.hasOwnProperty(fieldName)) {
                    self.toaster.pop('error', "Attribute \"" + fieldName + "\"", errorResponse.data.errors[fieldName]);
                }
            }
        }*/
        self.toaster.pop('error', "Error", "hh");
    };

    function saveCountry() {
        if (self.updateMode){
            CountryFactory.update(self.country, $uibModalInstance.close, self.promiseErrorHandler);
        } else {
            CountryFactory.save(self.country, $uibModalInstance.close, self.promiseErrorHandler);
        }
    }

    function cancel() {
        $uibModalInstance.dismiss('cancel');
    }
}