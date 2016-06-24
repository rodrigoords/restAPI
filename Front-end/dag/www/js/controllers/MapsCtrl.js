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
controllers.controller('MapsCtrl', function($scope, $state, $cordovaGeolocation) {
  var options = {timeout: 10000, enableHighAccuracy: true};

  $cordovaGeolocation.getCurrentPosition(options).then(function(position){

    var latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);

    $scope.map = iniciaMapa(position);

		//Espera o Mapa carregar para adionar o indicador do usuario.
		google.maps.event.addListenerOnce($scope.map, 'idle', function(){

			var marker = new google.maps.Marker({
			    map: $scope.map,
			    animation: google.maps.Animation.DROP,
			    position: latLng,
          icon: 'img/marcadores/red.png'
			 });

		  	$scope.map.addListener('click', function(event){
		  	})

		});

  }, function(error){
    console.log("Could not get location");
  });
});
