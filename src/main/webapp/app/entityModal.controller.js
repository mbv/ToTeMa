angular
    .module('demo')
    .controller('EntityModalController', EntityModalController);

function EntityModalController($uibModalInstance, EntityService, entity, toaster) {
    var self = this;

    self.entity = entity;
    self.toaster = toaster;

    self.promiseErrorHandler = function (errorResponse) {
        if (!!errorResponse.data.errors) {
            for (var fieldName in errorResponse.data.errors) {
                if (errorResponse.data.errors.hasOwnProperty(fieldName)) {
                    self.toaster.pop('error', "Attribute \"" + fieldName + "\"", errorResponse.data.errors[fieldName]);
                }
            }
        }
        self.toaster.pop('error', "Error", errorResponse.data.error);
    };

    self.saveEntity = saveEntity;
    self.cancel = cancel;
    self.updateMode = !!self.entity;

    function saveEntity() {
        if (self.updateMode) {
            EntityService.update(self.entity, $uibModalInstance.close).$promise.then(null, self.promiseErrorHandler);
        } else {
            EntityService.save(self.entity, $uibModalInstance.close).$promise.then(null, self.promiseErrorHandler);
        }
    }

    function cancel() {
        $uibModalInstance.dismiss('cancel');
    }
}