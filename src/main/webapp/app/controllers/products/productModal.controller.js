angular
    .module('app')
    .controller('ProductModalController', ProductModalController);

function ProductModalController($scope, $uibModalInstance, ProductFactory, ProductTypeFactory, product) {
    var self = this;

    self.product = product;
    if (!!product) {
        $scope.temporaryProductType = self.product.type.id;
    }
    self.saveProduct = saveProduct;
    self.cancel = cancel;
    self.updateMode = !!self.product;

    ProductTypeFactory.query().$promise.then(function (result) {
        self.productTypes = result;
        $scope.$watch('temporaryProductType', function (newValue, oldValue) {
            if (newValue) {
                angular.forEach(self.productTypes, function (productType) {
                    if (productType.id == newValue && !!self.product) {
                        self.product.type = productType;
                    }
                });
            }
        });
    });

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