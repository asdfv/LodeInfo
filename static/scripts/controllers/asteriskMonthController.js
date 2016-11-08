myApp.controller('asteriskMonthController', ['$scope', '$http',
    function($scope, $http){

        $scope.monthStat = {};

        var d = new Date();
        var curr_date = d.getDate();
        var curr_month = d.getMonth() + 1;
        var curr_year = d.getFullYear();

        // return in format 'MM-monthName'
        $scope.monthStat.months = ('январь февраль март апрель май июнь июль ' +
        'август сентябрь октябрь ноябрь декабрь').split(' ').map(function (month, index) {
            return { monthName: ('0' + (index + 1)).slice(-2) + ' - ' + month };
        });

        $scope.monthStat.years = ('2013 2014 2015 2016').split(' ').map(function (year) {
            return { year: year };
        });

        // Default value current year and month
        $scope.monthStat.year = curr_year;
        $scope.monthStat.extendedMonth = $scope.monthStat.months[parseInt(curr_month) - 1].monthName;

        $scope.showMonthStat = function(year, extendedMonth){

            //Count of day to show, 28, 29, 30, 31 or current day?
            if (curr_year === parseInt(year) && curr_month === parseInt(extendedMonth))
                var countDayInMonth = curr_date;
            else
                var countDayInMonth = new Date(year, extendedMonth.slice(0, 2), 0).getDate();

            $scope.monthStat.stat_list = [];



            for (var i = 1; i < countDayInMonth + 1; i++) {

                var stringDay = ('0' + i).slice(-2); // 3 -> 03, 4 -> 04 etc.
                var stringMonth = extendedMonth.slice(0, 2); // 01-январь -> 01

                $http({
                    url: '/asterisk/getStatForDay',
                    method: "GET",
                    params: {'day': $scope.monthStat.year + '-' + stringMonth + '-' + stringDay}
                }).then(function(response){

                    $scope.monthStat.stat_list.push(response.data);

                },function(data){
                    $scope.monthStat.stat_list.push(0, 0, 0, 0); // error handle
                });
            }
        };
    }
]);


