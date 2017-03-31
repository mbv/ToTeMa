angular
    .module('demo')
    .controller('ProductsListController', ProductsListController);

function ProductsListController($controller) {
    var self = this;
    $controller('EntityListController', {resourceName: 'products', self: self});
}