var myApp = angular.module('lodeinfo', ['ngResource', 'ngRoute', 'ngMaterial', 'ngSanitize'/*, 'textAngular'*/]);

myApp.config(['$routeProvider', '$httpProvider', '$mdDateLocaleProvider',

    function($routeProvider, $httpProvider, $mdDateLocaleProvider){

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
        .when('/phoneBook', {
            templateUrl: '/pages/staticPages/phoneBook.html'
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
        .when('/asterisk/month', {
            templateUrl: '/pages/asteriskMonth.html',
            controller: 'asteriskMonthController'
        })
        .when('/asterisk/day', {
            templateUrl: '/pages/asteriskDay.html',
            controller: 'asteriskDayController'
        })
        .when('/asterisk/cdr', {
            templateUrl: '/pages/asteriskCDR.html',
            controller: 'asteriskCDRController'
        })
    ;

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    $mdDateLocaleProvider.firstDayOfWeek = 1;

    $mdDateLocaleProvider.months = ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'];
    $mdDateLocaleProvider.shortMonths = ['янв', 'фев', 'мар', 'апр', 'май', 'июн', 'июл', 'авг', 'сен', 'окт', 'ноя', 'дек'];
    $mdDateLocaleProvider.days = ['Воскресение', 'Понедельник', 'Вторник', 'Среда', 'Четверг', 'Птяница', 'Суббота'];
    $mdDateLocaleProvider.shortDays = ['вс', 'пн', 'вт', 'ср', 'чт', 'пт', 'сб'];
    $mdDateLocaleProvider.firstRenderableDate = new Date(2011, 11, 1);
    $mdDateLocaleProvider.lastRenderableDate = new Date();

}]);

myApp.filter('trusted', ['$sce', function ($sce) {
    return function(url) {
        return $sce.trustAsResourceUrl(url);
    };
}]);


