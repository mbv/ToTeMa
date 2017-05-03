angular
    .module('app')
    .controller('ProductListController', ProductListController);

function ProductListController($uibModal, ProductFactory, $state) {
    var self = this;

    self.products = [];
    self.isUpdatingChosen = false;

    self.title = 'Products';
    self.addProduct = addProduct;
    self.deleteProduct = deleteProduct;
    self.editProduct = editProduct;

    refresh();

    function refresh() {
        ProductFactory.query().$promise.then(function (result) {
            self.products = result;
        });
    }

    function addProduct() {
        openModal();
    }

    function deleteProduct(product) {
        ProductFactory.delete(product, refresh);
    }

    function editProduct(product) {
        openModal(product);
    }

    function openModal(product) {
        var modalInstance = $uibModal.open({
            controller: 'ProductModalController as self',
            templateUrl: 'templates/crud/products/modal.html',
            resolve: {
                product: function () {
                    return angular.copy(product);
                }
            }
        });
        modalInstance.result.then(refresh);
    }
}