myApp.controller('asteriskDayController', ['$scope', '$http', '$filter',
    function($scope, $http, $filter){

        $scope.dayStat = {};

        $scope.dayStat.day = new Date();

        $scope.dayStat.startHour = 8;
        $scope.dayStat.endHour = 21;

        $scope.dayStat.hours = [];

        for (var i = 0; i <= 23; i++) {
            $scope.dayStat.hours.push(i);
        };


        $scope.showDayStat = function(day, startHour, endHour){

            $scope.dayStat.stat_list = [];

            for (var hour = parseInt(startHour); hour < parseInt(endHour); hour++) {

                var stringDay = $filter('date')($scope.dayStat.day, 'yyyy-MM-dd', '+03');

                $http({
                    url: '/asterisk/getStatForHour',
                    method: "GET",
                    params: {
                                'day': stringDay,
                                'hour': hour
                            }
                }).then(function(response){

                    $scope.dayStat.stat_list.push(response.data);

                },function(data){
                    $scope.dayStat.stat_list.push(0, 0, 0, 0); // error handle
                });
            }
        };
    }
]);


