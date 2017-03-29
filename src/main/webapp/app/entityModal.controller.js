angular
    .module('demo')
    .controller('EntityModalController', EntityModalController);

function EntityModalController($uibModalInstance, EntityService, entity) {
    var self = this;

    self.entity = entity;

    self.saveEntity = saveEntity;
    self.cancel = cancel;
    self.updateMode = !!self.entity;

    function saveEntity() {
        if (self.updateMode){
            EntityService.update(self.entity, $uibModalInstance.close)
        } else {
            EntityService.save(self.entity, $uibModalInstance.close)
        }
    }

    function cancel() {
        $uibModalInstance.dismiss('cancel');
    }
}