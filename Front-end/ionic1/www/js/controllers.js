/**
 * Arquivo Js global do Controllers
 *
 * @file Controllers.js
 * @authors André Queiroz, Rafael Mello e Rodrigo Sene
 * @version 1.0
 *
 * @copyright 2016, Dag!
 *
 */
controllers.controller('AppCtrl', function($scope, $ionicSideMenuDelegate, MapsFactory) {

  $scope.toggleLeft = function() {
       $ionicSideMenuDelegate.toggleLeft();
  };

  $scope.toggleBolhas = function(){
    MapsFactory.toggleBolhas();
  }
});
