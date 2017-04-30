angular
    .module('app')
    .controller('SignInController', SignInController);

function SignInController(AuthService) {
    var self = this;

    self.username = "";
    self.password = "";
    self.needToRemember = true;
    self.signIn = signIn;

    function signIn() {
        AuthService.signIn(self.username, self.password, self.needToRemember);
    }
}