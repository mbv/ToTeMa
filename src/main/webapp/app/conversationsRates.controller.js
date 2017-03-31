angular
    .module('demo')
    .controller('ConvertionsRateListController', ConvertionsRatesListController);

function ConvertionsRatesListController($controller) {
    var self = this;
    $controller('EntityListController', {resourceName: 'convertion-rates', self: self});
}
