myApp.controller('vipController', ['$scope', '$http',
    function($scope, $http){

        $scope.clients = {};
        $scope.clients.spent = 1000;
        $scope.clients.payments = 3;


        $scope.searchVipList = function(spent, payments){

            $scope.loading = true;

            $http({
                url: '/vip/searchVipList',
                method: "GET",
                params: {'spent': spent, 'payments': payments}
            }).then(function(response){

                $scope.vip_list = response.data;

            }).finally(function(){
                $scope.loading = false;
            });

        };


        //$scope.searchVipCount = function(spent, payments){
        //
        //    $scope.clients.vipCount = null;
        //    $scope.loading = true;
        //
        //    $http({
        //        url: '/vip/searchVipCount',
        //        method: "GET",
        //        params: {'spent': spent, 'payments': payments}
        //    }).then(function(response){
        //
        //        $scope.clients.vipCount = response.data;
        //    }).finally(function(){
        //        $scope.loading = false;
        //    });
        //
        //};


    }
]);