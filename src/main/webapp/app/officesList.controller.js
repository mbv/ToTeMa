angular
    .module('demo')
    .controller('OfficesListController', OfficesListController);

function OfficesListController($controller) {
    var self = this;
    $controller('EntityListController', {resourceName: 'offices', self: self});
}