angular
    .module('app')
    .controller('OrderListController', OrderListController);

function OrderListController($uibModal, OrderFactory, $state) {
    var self = this;

    self.orders = [];
    self.isUpdatingChosen = false;

    self.title = 'Orders';
    self.addOrder = addOrder;
    self.deleteOrder = deleteOrder;
    self.editOrder = editOrder;
    self.showProductLists = showProductLists;

    refresh();

    function refresh() {
        OrderFactory.query().$promise.then(function (result) {
            self.orders = result;
        });
    }

    function addOrder() {
        openModal();
    }

    function deleteOrder(order) {
        OrderFactory.delete(order, refresh);
    }

    function editOrder(order) {
        openModal(order);
    }

    function showProductLists(order) {
        $state.go('productLists', { order: order.id });
    }


    function openModal(order) {
        var modalInstance = $uibModal.open({
            controller: 'OrderModalController as self',
            templateUrl: 'templates/crud/orders/modal.html',
            resolve: {
                order: function () {
                    return angular.copy(order);
                }
            }
        });
        modalInstance.result.then(refresh);
    }
}