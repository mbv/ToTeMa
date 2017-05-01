angular
    .module('app')
    .controller('OfficeModalController', OfficeModalController);

function OfficeModalController($uibModalInstance, OfficeFactory, office, toaster) {
    var self = this;

    self.office = office;
    self.saveOffice = saveOffice;
    self.cancel = cancel;
    self.updateMode = !!self.office;
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

    function saveOffice() {
        if (self.updateMode){
            OfficeFactory.update(self.office, $uibModalInstance.close, self.promiseErrorHandler);
        } else {
            OfficeFactory.save(self.office, $uibModalInstance.close, self.promiseErrorHandler);
        }
    }

    function cancel() {
        $uibModalInstance.dismiss('cancel');
    }
}