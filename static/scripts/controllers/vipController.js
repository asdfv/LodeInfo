myApp.controller('vipController', ['$scope', '$http',
    function($scope, $http){
        $scope.clients = {};
        $scope.clients.spent = 100;
        $scope.clients.payments = 3;


        $scope.searchVip = function(spent, payments){

            $scope.clients.vipCount = null;

            $scope.loading = true;

            $http({
                url: '/vip/searchVip',
                method: "GET",
                params: {'spent': spent, 'payments': payments}
            }).then(function(response){
                $scope.clients.vipCount = response.data;
            }).finally(function(){
                $scope.loading = false;
            });

        };
    }
]);