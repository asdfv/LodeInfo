myApp.controller('newsDetailController', ['$scope', '$routeParams', 'NewsFactory',
    function($scope, $routeParams, NewsFactory){

        var newsId = $routeParams.newsId;

        NewsFactory.get({do:'get', id:newsId}).$promise.then(
            function(news){
                $scope.newsDetail = {};
                $scope.newsDetail.id = news.id;
                $scope.newsDetail.title = news.title;
                $scope.newsDetail.text = news.text;
                $scope.newsDetail.createdOn = news.createdOn;
                $scope.newsDetail.lastEdit = news.lastEdit;
                $scope.newsDetail.files = news.files;
                $scope.newsDetail.someone = news.forWhom;
            }
        );

        //DOWNLOAD FILE
        $scope.download = function(id){
            var downloadUrl = '/news/downloadFile/' + id;
            window.location.href = downloadUrl;
        };
    }
]);


