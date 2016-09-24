myApp.controller('newsAddController', ['$scope', '$routeParams', 'NewsFactory', 'fileUpload',
    function($scope, $routeParams, NewsFactory, fileUpload){

        var someone = $routeParams.someone;
        $scope.someone = someone;

        // SAVE news and UPLOAD file
        $scope.saveNews = function(){

            var news = new NewsFactory();
            news.title = $scope.news.title;
            news.text = $scope.news.text;
            news.forWhom = someone;

            // save return promise of news
            var newsPromise = news.$save({do:'save', id:null});

            // After save news, needs to upload news files and set the foreign key 'news_id'
            if ($scope.news.files != undefined) {
                newsPromise.then(function(news) {
                    // take id of news and put into upload field "news_id"
                    var newsId = news.id;
                    var files = $scope.news.files;
                    fileUpload.uploadFileToUrl(files, "/news/uploadFiles", newsId);
                });
            }
            window.location.href = '/#/news/for/' + someone;
        };
    }
]);


