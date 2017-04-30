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
            name: 'crud',
            url: '/crud',
            templateUrl: 'templates/crud/index.html',
            redirectTo: 'crud.employees'
        },
        {
            name: 'crud.employees',
            url: '/employees',
            controller: 'EmployeeListController as self',
            templateUrl: 'templates/crud/employees/index.html'
        },
        { name: 'otherwise', url: '/otherwise', template: '<h1>404</h1>' }
    ].forEach(function(state) { $stateProvider.state(state) });

    $urlRouterProvider.when('', '/');
    $urlRouterProvider.otherwise('/otherwise');
}
