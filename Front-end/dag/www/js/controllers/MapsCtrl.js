/**
 * Arquivo de Controle do Mapa
 *
 * @file Controllers/MapsCtrl.js
 * @authors André Queiroz, Rafael Mello e Rodrigo Sene
 * @version 1.0
 *
 * @copyright 2016, Dag!
 *
 */
 controllers.controller('MapsCtrl', function($scope, $state, $http, $cordovaGeolocation, MapsFactory) {
   MapsFactory.init();
});
