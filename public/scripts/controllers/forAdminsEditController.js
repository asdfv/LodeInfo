myApp.controller('forAdminsEditController', ['$scope', '$routeParams', 'forAdminsFactory', 'fileUpload',
    function($scope, $routeParams, forAdminsFactory, fileUpload){

        var newsId = $routeParams.newsId;

        forAdminsFactory.get({do:'get', id:newsId}).$promise.then(
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

            var file = new forAdminsFactory();
            file.$remove({do: 'deleteFile', id: fileId});
            forAdminsFactory.get({do:'get', id:newsId}).$promise.then(
                function(news){
                    $scope.newsEdit.oldFiles = news.files;
                }
            );
        };

        // UPDATE
        $scope.updateNews = function(){

            var news = new forAdminsFactory();
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
            fileUpload.uploadFileToUrl(newFiles, "/forAdmins/uploadFiles", newsId);

            window.location.href = '/#/forAdmins';
        };
    }
]);


