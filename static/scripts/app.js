var myApp = angular.module('lodeinfo', ['ngResource', 'ngRoute']);

myApp.config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider){

    $routeProvider

        .when('/', {
            redirectTo: '/news/for/all'
        })
        .when('/news', {
            redirectTo: '/news/for/all'
        })
        .when('/login', {
            templateUrl: '/pages/login.html',
            controller: 'loginController'
        })
        .when('/news/for/:someone', {
            templateUrl: '/pages/news.html',
            controller: 'newsController'
        })
        .when('/TimeTableManual', {
            templateUrl: '/pages/staticPages/TimeTableManual.html'
        })
        .when('/news/newsAdd/:someone', {
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
        .when('/sms', {
            templateUrl: '/pages/sms.html',
            controller: 'smsController'
        })
        .when('/vip', {
            templateUrl: '/pages/vip.html',
            controller: 'vipController'
        })
    ;

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
}]);


