angular
    .module('app')
    .controller('ProductModalController', ProductModalController);

function ProductModalController($uibModalInstance, ProductFactory, product) {
    var self = this;

    self.product = product;
    self.saveProduct = saveProduct;
    self.cancel = cancel;
    self.updateMode = !!self.product;

    function saveProduct() {
        if (self.updateMode){
            ProductFactory.update(self.product, $uibModalInstance.close);
        } else {
            ProductFactory.save(self.product, $uibModalInstance.close);
        }
    }

    function cancel() {
        $uibModalInstance.dismiss('cancel');
    }
}