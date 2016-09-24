myApp.controller('newsController', ['$scope', '$routeParams', 'fileUpload', 'NewsFactory',
    function($scope, $routeParams, fileUpload, NewsFactory){

        var someone = $routeParams.someone;
        $scope.someone = someone;

        $scope.pageNum = 0;

        // For render Page of news list /news/findForSomeone?offset=pageNum=&someone=someone
        var loadList = function(pageNum, someone) {
            var pagePromise = NewsFactory.getPage({do:'findForSomeone', offset: pageNum, someone: someone});
            pagePromise.$promise.then(function(data){
                $scope.news_list = data.content;
                $scope.totalPages = data.totalPages;
            });
        };

        loadList(0, someone);

        $scope.loadPage = function(pageNum){
            $scope.pageNum = pageNum;
            loadList(pageNum, someone);
        }

        //Summary size of attached files
        //$scope.sumSize = function(arr) {
        //    var sizes = arr.map( function(file) {
        //        return file.size;
        //    });
        //
        //    var result = sizes.reduce(function(sum, current) {
        //        return sum + current;
        //    }, 0);
        //
        //    return result;
        //};

}]);
