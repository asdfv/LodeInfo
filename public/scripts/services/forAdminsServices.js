// RESOURCE factory
myApp.factory('forAdminsFactory', [
    '$resource', function($resource){
        return $resource('/forAdmins/:do/:id',
            {do: '@do', id: '@id'},
            {
                'getPage': {method: 'GET', isArray: false},
                'update': {method: 'PUT', params: '@news'}
            }
            );
    }
]);


// UPLOAD service
myApp.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(data, uploadUrl, newsId){

        var fd = new FormData();

        for(var key in data) {
            fd.append(key, data[key]);
        }

        fd.append('newsId', newsId);

        $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
            .success(function(){
                console.log('Uploading complete');
            })
            .error(function(){
                alert('Ошибка загрузки файлов: проверьте размер загружаемых файлов');
            });
    }
}]);