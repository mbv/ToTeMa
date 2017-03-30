angular
    .module('demo')
    .controller('OrdersListController', OrdersListController);

function OrdersListController($controller) {
    var self = this;
    $controller('OrdersListController', {resourceName: 'orders', self: self});
}