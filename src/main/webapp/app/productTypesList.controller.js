angular
    .module('demo')
    .controller('ProductTypesListController', ProductTypesListController);

function ProductTypesListController($controller) {
    var self = this;
    $controller('EntityListController', {resourceName: 'product-types', self: self});
}
