angular
    .module('app')
    .controller('ProductListModalController', ProductListModalController);

function ProductListModalController($scope, $uibModalInstance, ProductListFactory, ProductFactory, productList, orderId) {
    var self = this;

    self.orderId = orderId;

    self.productList = productList;
    if (!!productList) {
        $scope.temporaryProduct = self.productList.product.id;
    }
    self.saveProductList = saveProductList;
    self.cancel = cancel;
    self.updateMode = !!self.productList;

    ProductFactory.query().$promise.then(function (result) {
        self.products = result;
        if (!self.productList && (self.products.length > 0)) {
            self.productList = {};
            self.productList.product = self.products[0];
            $scope.temporaryProduct = self.productList.product.id;
        }
        $scope.$watch('temporaryProduct', function (newValue, oldValue) {
            if (newValue) {
                angular.forEach(self.offices, function (product) {
                    if (product.id == newValue && !!self.productList) {
                        self.productList.product = product;
                    }
                });
            }
        });
    });

    function saveProductList() {
        self.productList.orderKey = self.orderId;
        if (self.updateMode){
            ProductListFactory.update(self.productList, $uibModalInstance.close);
        } else {
            ProductListFactory.save(self.productList, $uibModalInstance.close);
        }
    }

    function cancel() {
        $uibModalInstance.dismiss('cancel');
    }
}