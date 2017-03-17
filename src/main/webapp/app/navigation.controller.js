angular
    .module('demo')
    .controller('NavigationController', NavigationController);

function NavigationController($location) {
    var self = this;

    self.isActive = isActive;

    function isActive(navigationLinkPath) {
        return navigationLinkPath === $location.path();
    }
}