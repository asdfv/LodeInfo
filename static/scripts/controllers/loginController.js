myApp.controller('loginController', ['$rootScope', '$scope', '$http', '$location',

    function($rootScope, $scope, $http, $location){

        var authenticate = function(credentials, callback) {

            var headers = credentials ? {authorization : "Basic "
            + btoa(credentials.username + ":" + credentials.password)
            } : {};

            $http.get('/security/user', {headers : headers}).success(function(data) {
                if (data.name) {
                    $rootScope.currentUser = data.name;
                    console.log(data.name + ' has logged');
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.authenticated = false;
                    $rootScope.currentUser = 'anonymous';
                }
                callback && callback();
            }).error(function() {
                $rootScope.authenticated = false;
                $rootScope.currentUser = 'anonymous';
                callback && callback();
            });

        };

        authenticate();

        $scope.credentials = {};

        $scope.login = function() {
            authenticate($scope.credentials, function() {
                if ($rootScope.authenticated) {
                    $scope.error = false;
                    $location.path("/");
                } else {
                    $location.path("/login");
                    $scope.error = true;
                }
            });
        };

        $scope.logout = function() {
            $http.post('logout', {}).success(function() {
                $rootScope.authenticated = false;
                console.log('Post message logout is send.');
                $location.path("/")
            }).error(function(data) {
                console.log('Error logout.');
                $rootScope.authenticated = false;
            });
        };
    }
]);


