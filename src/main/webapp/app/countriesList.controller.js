angular
    .module('demo')
    .controller('CountriesListController', CountriesListController);

function CountriesListController($controller) {
    var self = this;
    $controller('CountriesListController', {resourceName: 'countries', self: self});
}
