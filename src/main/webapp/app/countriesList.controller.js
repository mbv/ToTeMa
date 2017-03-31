angular
    .module('demo')
    .controller('CountriesListController', CountriesListController);

function CountriesListController($controller) {
    var self = this;
    $controller('EntityListController', {resourceName: 'countries', self: self});
}
