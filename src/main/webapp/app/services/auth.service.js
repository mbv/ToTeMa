angular
    .module('app')
    .service('AuthService', AuthService);

function AuthService($http, $state, $cookies, UserFactory) {

    var self = {
        isAuthorized: false,
        tryAuthorize: tryAuthorize,
        unauthorize: unauthorize,
        signIn: signIn,
        signOut: signOut,
        role: null,
        user: null
    };

    return self;

    function tryAuthorize() {
        var accessToken = $cookies.get('recourse-access-token');
        if (!!accessToken) {
            self.isAuthorized = true;
            injectAccessTokenToOutgoingHttpRequests(accessToken);
            initUser(accessToken);
        }
    }

    function unauthorize() {
        $cookies.remove('recourse-access-token');
        rejectAccessTokenToOutgoingHttpRequests();
        self.isAuthorized = false;
        self.role = null;
        $state.go('root');
    }

    function signIn(username, password, needToRemember) {
        var requestParams = {
            username: username,
            password: password,
            grant_type: 'password'
        };

        $http(oauthRequestConfig('/ReCourse/oauth/token', requestParams))
            .then(function (response) {
                handleAccessTokenRequest(response, needToRemember);
            });
    }

    function signOut() {
        makeLogoutRequest(self.unauthorize);
    }

    function handleAccessTokenRequest(response, needToRemember) {
        var accessToken = response.data.access_token;
        var tokenExpiresIn = response.data.expires_in;

        if (!!accessToken) {
            injectAccessTokenToOutgoingHttpRequests(accessToken);
            initUser(accessToken);
            self.isAuthorized = true;
            if (needToRemember) {
                var expirationDate = new Date();
                expirationDate.setSeconds(expirationDate.getSeconds() + tokenExpiresIn);
                $cookies.put('recourse-access-token', accessToken, { expires: expirationDate } );
            }
        }

        $state.go('root');
    }

    function injectAccessTokenToOutgoingHttpRequests(token) {
        $http.defaults.headers.common['Authorization'] = 'Bearer ' + token;
    }

    function rejectAccessTokenToOutgoingHttpRequests() {
        $http.defaults.headers.common['Authorization'] = undefined;
    }

    function makeLogoutRequest(callback) {
        $http.post('/ReCourse/api/users/logout').then(callback);
    }

    function oauthRequestConfig(url, params) {
        return {
            url: url,
            method: 'POST',
            headers: {
                'Authorization': 'Basic d2ViX2NsaWVudDpsSDJIdlE0VmszZjhrMFM=',
                'Accept': 'application/json'
            },
            params: params
        };
    }

    function initUser(accessToken) {
        $http.get('/ReCourse/oauth/check_token', {params: {token:accessToken}}).then(function (response) {
            if (response.status === 200) {
                self.role = response.data.authorities[0];
            }
        });
        UserFactory.me(function(response) {
            self.user = response;
        });
    }
}
