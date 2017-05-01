angular
    .module('app')
    .controller('ProductTypeListController', ProductTypeListController);

function ProductTypeListController($uibModal, ProductTypeFactory, $state) {
    var self = this;

    self.productTypes = [];
    self.isUpdatingChosen = false;

    self.title = 'ProductTypes';
    self.addProductType = addProductType;
    self.deleteProductType = deleteProductType;
    self.editProductType = editProductType;

    refresh();

    function refresh() {
        ProductTypeFactory.query().$promise.then(function (result) {
            self.productTypes = result;
        });
    }

    function addProductType() {
        openModal();
    }

    function deleteProductType(productType) {
        ProductTypeFactory.delete(productType, refresh);
    }

    function editProductType(productType) {
        openModal(productType);
    }

    function openModal(productType) {
        var modalInstance = $uibModal.open({
            controller: 'ProductTypeModalController as self',
            templateUrl: 'templates/crud/productTypes/modal.html',
            resolve: {
                productType: function () {
                    return angular.copy(productType);
                }
            }
        });
        modalInstance.result.then(refresh);
    }
}