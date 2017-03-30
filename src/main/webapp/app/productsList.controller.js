angular
    .module('demo')
    .controller('ProductsListController', ProductsListController);

function ProductsListController($controller) {
    var self = this;
    $controller('ProductsListController', {resourceName: 'products', self: self});
}