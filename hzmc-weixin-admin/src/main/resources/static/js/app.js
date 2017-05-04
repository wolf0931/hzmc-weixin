'use strict';

var app = angular
    .module('myApp', [
        'ngRoute'
    ])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'web/login/login.ftl',
                controller: 'LoginCtrl'
            })
    });

    app.controller('LoginCtrl', function ($scope,$http) {
        $scope.name = "wph"
        $scope.password = "123456"
        $scope.submit = function () {
            console.log($scope.name)
            console.log($scope.password)
        }
    })






