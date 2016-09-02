myApp.controller('forAdminsDetailController', ['$scope', '$routeParams', 'forAdminsFactory',
    function($scope, $routeParams, forAdminsFactory){
        var newsId = $routeParams.newsId;

        forAdminsFactory.get({do:'get', id:newsId}).$promise.then(
            function(news){
                $scope.newsDetail = {};
                $scope.newsDetail.id = news.id;
                $scope.newsDetail.title = news.title;
                $scope.newsDetail.text = news.text;
                $scope.newsDetail.createdOn = news.createdOn;
                $scope.newsDetail.lastEdit = news.lastEdit;
                $scope.newsDetail.files = news.files;
            }
        );

        //DOWNLOAD FILE
        $scope.download = function(id){
            var downloadUrl = '/forAdmins/downloadFile/' + id;
            window.location.href = downloadUrl;
        };

        // DELETE NEWS
        $scope.deleteNews = function(id){
            if (confirm('Удалить эту новость?')) {
                var news = new forAdminsFactory();
                news.$remove({do:'delete', id:id});
                window.location.href = '/#/forAdmins';
            }
        };
    }
]);


