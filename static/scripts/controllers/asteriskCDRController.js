myApp.controller('asteriskCDRController', ['$scope', '$filter', 'CdrFactory',
    function($scope, $filter, CdrFactory){


        //var pathToAudio = 'http://callcenter.lode/monitor/';
        var pathToAudio = '/assets/audio/monitor/';
        var pathToFtpIncoming = 'ftp://ftp_asterisk_records:YxM5F1Nb9MguZMtrp8DB@192.168.211.55/records/';
        var pathToFtpOutgoing = 'ftp://ftp_asterisk_records:YxM5F1Nb9MguZMtrp8DB@192.168.211.55/records_outgoing/';
        $scope.showMainTable = true;

        $scope.selectedDate = new Date();

        var dateToString = function(date) {
            return $filter('date')(date, 'yyyy-MM-dd', '+03');
        };

        $scope.loadList = function() {

            var stringDate = dateToString($scope.selectedDate);

            $scope.dayLoading = true;

            var promise = CdrFactory.query({source: 'day', key: 'dayToSearch', value: stringDate}).$promise;

            promise.then(function(data){
                $scope.cdr_list = data;
            }, function(responseError){
                alert('Что-то пошло не так! \n Еще раз попробуйте!');
            }).finally(function(){
                $scope.dayLoading = false;
            });

            $scope.showMainTable = true;
        }

        $scope.numberSearch = function(){

            $scope.numberLoading = true;

            var promise = CdrFactory.query({source: 'number', key: 'numberToSearch', value: $scope.number}).$promise;

            promise.then(function(data){
                $scope.number_list = data;
            }, function(responseError){

            }).finally(function(){
                $scope.numberLoading = false;
            });

            $scope.showMainTable = false;
        }

        //Paths to Audio sources
        $scope.getAudioPath = function(record){

            var stringDate = record.calldate.substring(0, 10);

            var month = stringDate.substring(0,7);
            var day = stringDate.substring(8, 10);

            var path = pathToAudio + month + '/' + day + "/" + record.uniqueid + ".wav";

            return path;
        };

        $scope.getAudioPathIncomingFtp = function(record){

            var stringDate = record.calldate.substring(0, 10);

            var month = stringDate.substring(0,7);
            var day = stringDate.substring(8, 10);

            return pathToFtpIncoming + month + '/' + day + "/" + record.uniqueid + ".wav";
        };

        $scope.getAudioPathOutgoingFtp = function(record){
            return pathToFtpOutgoing + record.uniqueid + ".wav";
        };

        $scope.isIncoming = function(record) {
            return record.src.length != 4;
        }
        //Paths to Audio sources END


        //Filters
        $scope.timeFilter = function(record) {

            var startHour = dateToString($scope.selectedDate).substring(0, 10) + ' 08';
            var endHour = dateToString($scope.selectedDate).substring(0, 10) + ' 21';

            if ($scope.timeFilterEnabled) {
                return (record.calldate > startHour && record.calldate < endHour) ;
            } else return true;
        };

        $scope.incomingFilter = function(record) {
            if ($scope.incomingFilterEnabled) return record.src.length != 4;
            else return true;
        };

        $scope.outgoingFilter = function(record) {
            if ($scope.outgoingFilterEnabled) return record.src.length == 4; /*140X - outgoing*/
            else return true;
        };
        //Filters END

        $scope.loadList();
    }
]);
