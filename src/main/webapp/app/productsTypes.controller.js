angular
    .module('demo')
    .controller('ProductsTypesListController', ProductsTypesListController);

function ProductsTypesListController($controller) {
    var self = this;
    $controller('ProductsTypesListController', {resourceName: 'productsTypes', self: self});
}
