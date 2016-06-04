/**
 * Arquivo de Controle do Chat
 *  
 * @file Controllers/ChatCtrl.js 
 * @authors André Queiroz, Rafael Mello e Rodrigo Sene
 * @version 1.0
 *
 * @copyright 2016, Dag!
 * 
 */
controllers.controller('PlaylistsCtrl', function($scope, $stateParams, $q) {

 $scope.playlists = [
    { title: 'Reggae', id: 1 },
    { title: 'Chill', id: 2 },
    { title: 'Dubstep', id: 3 },
    { title: 'Indie', id: 4 },
    { title: 'Rap', id: 5 },
    { title: 'Cowbell', id: 6 }
  ];

});