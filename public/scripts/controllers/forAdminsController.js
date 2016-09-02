myApp.controller('forAdminsController', ['$scope', 'fileUpload', 'forAdminsFactory',
    function($scope, fileUpload, forAdminsFactory){

        $scope.pageNum = 0;

        // For render Page of news list /news/findAll?offset=pageNum
        var loadList = function(pageNum) {
            var pagePromise = forAdminsFactory.getPage({do:'findAll', offset: pageNum});
            pagePromise.$promise.then(function(data){
                $scope.news_list = data.content;
                $scope.totalPages = data.totalPages;
            });
        };

        loadList(0);

        $scope.loadPage = function(pageNum){
            $scope.pageNum = pageNum;
            loadList(pageNum);
        }

        //$scope.showFile = function(id){
        //    NewsFactory.get({do: 'downloadFile', id: id}, function success(data, headers){
        //        var type = headers('content-type');
        //        $scope.type = null;
        //        if (type.indexOf('image')!== - 1) // comparison content type contains 'image' or not
        //            $scope.type = 'image';
        //    });
        //};
}]);
