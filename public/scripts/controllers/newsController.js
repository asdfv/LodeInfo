myApp.controller('newsController', ['$scope', 'fileUpload', 'NewsFactory',
    function($scope, fileUpload, NewsFactory){

        $scope.pageNum = 0;

        // For render Page of news list /news/findAll?offset=pageNum
        var loadList = function(pageNum) {
            var pagePromise = NewsFactory.getPage({do:'findAll', offset: pageNum});
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

        $scope.sumSize = function(arr) {
            var sizes = arr.map( function(file) {
                return file.size;
            });

            var result = sizes.reduce(function(sum, current) {
                return sum + current;
            }, 0);

            return result;
        };

}]);
