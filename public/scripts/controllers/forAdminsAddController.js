myApp.controller('forAdminsAddController', ['$scope', '$routeParams', 'forAdminsFactory', 'fileUpload',
    function($scope, $routeParams, forAdminsFactory, fileUpload){

        // SAVE news and UPLOAD file
        $scope.saveNews = function(){

            var news = new forAdminsFactory();
            news.title = $scope.news.title;
            news.text = $scope.news.text;

            // save return promise of news
            var newsPromise = news.$save({do:'save', id:null});

            // After save news, needs to upload news files and set the foreign key 'news_id'
            if ($scope.news.files != undefined){
                newsPromise.then(function(news) {
                    // take id of news and put into upload field "news_id"
                    var newsId = news.id;
                    var files = $scope.news.files;
                    fileUpload.uploadFileToUrl(files, "/forAdmins/uploadFiles", newsId);
                });
                window.location.href = '/#/forAdmins/';
            }
        };
    }
]);


