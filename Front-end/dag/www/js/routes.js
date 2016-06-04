/**
 * Arquivo global de Roteamento
 *
 * @file Routes.js
 * @authors André Queiroz, Rafael Mello e Rodrigo Sene
 * @version 1.0
 *
 * @copyright 2016, Dag!
 *
 */

dag.config(function($stateProvider, $urlRouterProvider, $ionicConfigProvider) {

  // Força o tabs no rodapé utilizando o Android
  //$ionicConfigProvider.tabs.position('bottom');
  $ionicConfigProvider.backButton.previousTitleText(false).text('');

  $stateProvider
  .state('termos', {
      url: '/termos',
      templateUrl: 'templates/termos.html'
  })
  .state('app', {
    url: '/app',
    abstract: true,
    templateUrl: 'templates/menu.html',
    controller: 'AppCtrl'
  })
  .state('app.intro', {
      url: '/intro',
      views: {
          'menuContent' :{
            templateUrl: "templates/intro.html",
            controller: 'IntroCtrl'
          }
        }
  })
  .state('app.mapa', {
    url: '/mapa',
    views: {
        'menuContent' :{
          templateUrl: "templates/map.html",
          controller: 'MapsCtrl'
        }
      }
  });


  // Se nada for definido, chama a página abaixo
  $urlRouterProvider.otherwise('/app/intro');
});
