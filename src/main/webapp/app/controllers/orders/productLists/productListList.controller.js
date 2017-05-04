angular
    .module('app')
    .controller('ProductListListController', ProductListListController);

function ProductListListController($uibModal, ProductListFactory, OrderFactory, $state, $stateParams) {
    var self = this;

    self.productLists = [];
    self.isUpdatingChosen = false;

    self.title = 'Order ProductLists';
    self.orderId = $stateParams.order;
    self.addProductList = addProductList;
    self.deleteProductList = deleteProductList;
    self.editProductList = editProductList;

    refresh();

    function refresh() {
        OrderFactory.getProductLists({ id: self.orderId }).$promise.then(function (result) {
            self.productLists = result;
        });
    }

    function addProductList() {
        openModal();
    }

    function deleteProductList(productList) {
        ProductListFactory.delete(productList, refresh);
    }

    function editProductList(productList) {
        openModal(productList);
    }

    function openModal(productList) {
        var modalInstance = $uibModal.open({
            controller: 'ProductListModalController as self',
            templateUrl: 'templates/crud/productLists/modal.html',
            resolve: {
                productList: function () {
                    return angular.copy(productList);
                },
                orderId: self.orderId
            }
        });
        modalInstance.result.then(refresh);
    }
}