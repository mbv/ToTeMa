angular
    .module('app')
    .config(AppStates);

function AppStates($stateProvider, $urlRouterProvider) {
    [
        { name: 'root', url: '/', redirectTo: 'home' },
        { name: 'home', url: '/home', templateUrl: 'templates/home.html' },
        { name: 'about', url: '/about', templateUrl: 'templates/about.html' },
        {
            name: 'signIn',
            url: '/sign_in',
            controller: 'SignInController as self',
            templateUrl: 'templates/sign_in.html'
        },
        {
            name: 'conversionRates',
            url: '/conversion-rates',
            controller: 'ConversionRateListController as self',
            templateUrl: 'templates/crud/conversionRates/index.html'
        },
        {
            name: 'countries',
            url: '/countries',
            controller: 'CountryListController as self',
            templateUrl: 'templates/crud/countries/index.html'
        },
        {
            name: 'employees',
            url: '/employees',
            controller: 'EmployeeListController as self',
            templateUrl: 'templates/crud/employees/index.html'
        },
        {
            name: 'offices',
            url: '/offices',
            controller: 'OfficeListController as self',
            templateUrl: 'templates/crud/offices/index.html'
        },
        {
            name: 'orders',
            url: '/orders',
            controller: 'OrderListController as self',
            templateUrl: 'templates/crud/orders/index.html'
        },
        {
            name: 'products',
            url: '/products',
            controller: 'ProductListController as self',
            templateUrl: 'templates/crud/products/index.html'
        },
        {
            name: 'productTypes',
            url: '/product-types',
            controller: 'ProductTypeListController as self',
            templateUrl: 'templates/crud/productTypes/index.html'
        },
        { name: 'otherwise', url: '/otherwise', template: '<h1>404</h1>' }
    ].forEach(function(state) { $stateProvider.state(state) });

    $urlRouterProvider.when('', '/');
    $urlRouterProvider.otherwise('/otherwise');
}
