angular
    .module('demo')
    .controller('OrdersListController', OrdersListController);

function OrdersListController($controller) {
    var self = this;
    $controller('EntityListController', {resourceName: 'orders', self: self});
}