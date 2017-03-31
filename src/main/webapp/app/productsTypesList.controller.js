angular
    .module('demo')
    .controller('ProductsTypesListController', ProductsTypesListController);

function ProductsTypesListController($controller) {
    var self = this;
    $controller('EntityListController', {resourceName: 'products-types', self: self});
}
