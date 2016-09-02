myApp.controller('newsEditController', ['$scope', '$routeParams', 'NewsFactory', 'fileUpload',
    function($scope, $routeParams, NewsFactory, fileUpload){

        var newsId = $routeParams.newsId;

        NewsFactory.get({do:'get', id:newsId}).$promise.then(
            function(news){
                $scope.newsEdit = {};
                $scope.newsEdit.id = news.id;
                $scope.newsEdit.title = news.title;
                $scope.newsEdit.text = news.text;
                $scope.newsEdit.createdOn = news.createdOn;
                $scope.newsEdit.lastEdit = news.lastEdit;
                $scope.newsEdit.oldFiles = news.files;

                // By default all files will be saved
                angular.forEach($scope.newsEdit.oldFiles, function(file){
                    file.forDelete = false; // add new property to each file
                });
            }
        );

        //DELETE FILE
        var deleteFile = function(fileId) {

            var file = new NewsFactory();
            file.$remove({do: 'deleteFile', id: fileId});
            NewsFactory.get({do:'get', id:newsId}).$promise.then( // Test if this needed?
                function(news){
                    $scope.newsEdit.oldFiles = news.files;
                }
            );
        };

        // UPDATE
        $scope.updateNews = function(){

            var news = new NewsFactory();
            var id = $scope.newsEdit.id;

            news.title = $scope.newsEdit.title;
            news.text = $scope.newsEdit.text;

            news.$update({do:'save', id:id});

            //Remove file with forDelete === true
            var oldFiles = $scope.newsEdit.oldFiles;
            angular.forEach(oldFiles, function(file){
                if (file.forDelete) {
                    deleteFile(file.id);
                    console.log(file.name + ' is deleted');
                }
            });

            // Save new added files
            var newFiles = $scope.newsEdit.newFiles;
            if (newFiles != undefined)
            fileUpload.uploadFileToUrl(newFiles, "/news/uploadFiles", newsId);

            window.location.href = '/#/news';
        };
    }
]);


