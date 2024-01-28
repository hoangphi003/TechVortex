let TechVortex = angular.module('TechVortex', []);

// TechVortex.controller('loginController', function ($scope, $http, $window) {
//     $scope.form = {};
//     console.log($scope.form.username);
//     console.log($scope.form.password);
//     $scope.login = function () {
//         $http.post('/TokenGenerator', $scope.form, { headers: { 'Content-Type': 'application/json' } })
//             .then(Response => {
//                 // Store token and user information in local storage
//                 $window.localStorage.setItem('userToken', JSON.stringify({ token: Response.data }));

//                 window.location.reload();
//             })
//             .catch(error => {
//                 console.log(error);
//             });
//     };
// });