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
        .when("/about", {
            templateUrl : 'about.html'
        })
        .otherwise({
            template : "<h1>None</h1><p>Nothing has been selected</p>"
        });
}