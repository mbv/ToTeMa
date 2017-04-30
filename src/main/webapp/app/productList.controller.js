angular
    .module('demo')
    .controller('ProductListController', ProductListController);

function ProductListController($controller) {
    var self = this;
    $controller('EntityListController', {resourceName: 'products', self: self});
}