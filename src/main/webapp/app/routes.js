angular
    .module('demo')
    .config(DemoConfig);

function DemoConfig($routeProvider, $locationProvider) {
    $routeProvider
        .when('/', {
            templateUrl : 'home.html'
        })
        .when('/users', {
            templateUrl : 'users.html'
        })
        .when('/convertion-rates', {
            templateUrl : 'convertion-rates.html'
        })
        .when('/countries', {
            templateUrl : 'countries.html'
        })
        .when('/offices', {
            templateUrl : 'offices.html'
        })
        .when('/orders', {
            templateUrl : 'orders.html'
        })
        .when('/products', {
            templateUrl : 'products.html'
        })
        .when('/types', {
            templateUrl : 'products-types.html'
        })
        .when("/about", {
            templateUrl : 'about.html'
        })
        .otherwise({
            template : "<h1>None</h1><p>Nothing has been selected</p>"
        });
}