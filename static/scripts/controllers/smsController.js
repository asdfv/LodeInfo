myApp.controller('smsController', ['$scope', '$http',
    function($scope, $http){

        $http.get('/sms/getSMS')
            .then(function(response){
                $scope.sms_list = response.data;

                angular.forEach($scope.sms_list, function(row){
                    row.forSend = true; // add new property to each row
                });
            });

       $scope.sendJson = function(){

           var toSend = $scope.sms_list;

           //Remove from list 'toSend' row with 'forSend = false'
           toSend = toSend.filter(function( row ) {
               return row.forSend === true;
           });

           if (confirm('Отправить ' + toSend.length + ' sms?')){
               $http({
                   method: 'POST',
                   url: '/test',
                   data: toSend,
                   headers: { "Content-Type": 'application/json' }
               });

           }
       };
    }
]);


