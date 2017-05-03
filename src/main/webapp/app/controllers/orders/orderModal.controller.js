angular
    .module('app')
    .controller('OrderModalController', OrderModalController);

function OrderModalController($uibModalInstance, OrderFactory, order) {
    var self = this;

    self.order = order;
    self.saveOrder = saveOrder;
    self.cancel = cancel;
    self.updateMode = !!self.order;

    function saveOrder() {
        if (self.updateMode){
            OrderFactory.update(self.order, $uibModalInstance.close);
        } else {
            OrderFactory.save(self.order, $uibModalInstance.close);
        }
    }

    function cancel() {
        $uibModalInstance.dismiss('cancel');
    }
}