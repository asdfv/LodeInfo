(function(){

	var myApp = angular.module('lodeinfo', ['ngResource']);

	myApp.factory('NewsFactory', [
		'$resource', function($resource){
			return $resource('news/news.json');
		}
		]);
	
	myApp.controller('infoController', ['$scope', '$http', 'NewsFactory', function($scope, $http, NewsFactory){

	    $scope.news_list = NewsFactory.query();

	}]);

})();



