angular
    .module('app')
    .controller('ProductTypeModalController', ProductTypeModalController);

function ProductTypeModalController($uibModalInstance, ProductTypeFactory, productType, toaster) {
    var self = this;

    self.productType = productType;
    self.saveProductType = saveProductType;
    self.cancel = cancel;
    self.updateMode = !!self.productType;
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

    function saveProductType() {
        if (self.updateMode){
            ProductTypeFactory.update(self.productType, $uibModalInstance.close, self.promiseErrorHandler);
        } else {
            ProductTypeFactory.save(self.productType, $uibModalInstance.close, self.promiseErrorHandler);
        }
    }

    function cancel() {
        $uibModalInstance.dismiss('cancel');
    }
}