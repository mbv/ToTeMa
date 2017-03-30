angular
    .module('demo')
    .controller('OfficesListController', OfficesListController);

function OfficesListController($controller) {
    var self = this;
    $controller('OfficeListController', {resourceName: 'offices', self: self});
}