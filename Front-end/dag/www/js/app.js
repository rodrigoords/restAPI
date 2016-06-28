/**
 * Arquivo Js global do Aplicativo
 *
 * @file App.js
 * @authors André Queiroz, Rafael Mello e Rodrigo Sene
 * @version 1.0
 *
 * @copyright 2016, Dag!
 *
 */

var dag = angular.module('dag', ['ionic','ngCordova','dag.controllers','dag.services','dag.directives', 'dag.factories']);
// removido ,'ionic.contrib.drawer'

/********************************************************************/
// -> Dag::Run
dag.run(function($ionicPlatform, $state) {

  $ionicPlatform.ready(function() {

    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);
    }

    //ionic.Platform.fullScreen();

    if (window.StatusBar) {
      StatusBar.styleDefault();
    }

  });

  $logged = 0;

  /*if(!$logged){
  	$state.go('app/intro');
  }*/


});
// <- Fim
/********************************************************************/

/********************************************************************/
// -> Dag::Controllers & Services
var controllers = angular.module('dag.controllers', []);
var services    = angular.module('dag.services', []);
var directives  = angular.module('dag.directives', []);
var factories   = angular.module('dag.factories', []);

// <- Fim
/********************************************************************/
