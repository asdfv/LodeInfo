var myApp = angular.module('lodeinfo', ['ngResource', 'ngRoute']);

myApp.config(['$routeProvider', function($routeProvider){

    $routeProvider

        .when('/', {
            templateUrl: '/pages/news.html',
            controller: 'newsController'
        })
        .when('/news', {
            templateUrl: '/pages/news.html',
            controller: 'newsController'
        })
        .when('/news/edit', {
            templateUrl: '/pages/newsEdit.html',
            controller: 'newsController'
        })
        .when('/TimeTableManual', {
            templateUrl: '/pages/staticPages/TimeTableManual.html',
            controller: 'newsController'
        })
        .when('/news/newsAdd', {
            templateUrl: '/pages/newsAdd.html',
            controller: 'newsAddController'
        })
        .when('/news/newsDetail/:newsId', {
            templateUrl: '/pages/newsDetail.html',
            controller: 'newsDetailController'
        })
        .when('/news/newsEdit/:newsId', {
            templateUrl: '/pages/newsEdit.html',
            controller: 'newsEditController'
        })

        // FOR ADMINS
        .when('/forAdmins', {
            templateUrl: '/pages/forAdmins.html',
            controller: 'forAdminsController'
        })
        .when('/forAdmins/edit', {
            templateUrl: '/pages/forAdminsEdit.html',
            controller: 'forAdminsEditController'
        })
        .when('/forAdmins/newsAdd', {
            templateUrl: '/pages/forAdminsAdd.html',
            controller: 'forAdminsAddController'
        })
        .when('/forAdmins/newsDetail/:newsId', {
            templateUrl: '/pages/forAdminsDetail.html',
            controller: 'forAdminsDetailController'
        })
        .when('/forAdmins/newsEdit/:newsId', {
            templateUrl: '/pages/forAdminsEdit.html',
            controller: 'forAdminsEditController'
        })
}]);


