/**
 * 
 */

var app = angular.module('usermanagement', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);
 
app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/list.html',
        controller: 'ListCtrl'
    }).when('/create', {
        templateUrl: 'views/create.html',
        controller: 'CreateCtrl'
    }).otherwise({
        redirectTo: '/'
    })
});
 
app.controller('ListCtrl', function ($scope, $http) {
	$scope.loadData = function(){
    $http.get('/api/v1/users').success(function (data) {
        $scope.users = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })
	}
    $scope.edit = function(user){
    	$scope.tempuser = user;
    }
    $scope.cancel = function(user)
    {
    	$scope.loadData();
    }
    
    $scope.save = function(user)
    {
        $http.put('/api/v1/users/' + user.id, user).success(function (data) {
            console.log('status changed');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
 
    $scope.loadData();
});
 
app.controller('CreateCtrl', function ($scope, $http, $location) {
    $scope.todo = {
        done: false
    };
 
    $scope.createUser = function () {
        console.log($scope.user);
        $http.post('/api/v1/users', $scope.user).success(function (data) {
            $location.path('/');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
    
});