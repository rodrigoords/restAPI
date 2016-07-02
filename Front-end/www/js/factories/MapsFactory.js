factories.factory('MapsFactory', function($cordovaGeolocation, JSONBolhaFactory){
  /*variavies globais*/
  var map = null;
  var markerUser = null;
  var zoomMinimo = 16;
  var styles = [{"stylers":[{"hue":"#ff1a00"},{"invert_lightness":true},{"saturation":-100},{"lightness":33},{"gamma":0.5}]},{"featureType":"water","elementType":"geometry","stylers":[{"color":"#2D333C"}]}];
  /*Funções da factory*/
  /*Função responsavel por iniciar o Google Maps*/
  function initMap(){

    var options = {timeout: 10000, enableHighAccuracy: true};

    $cordovaGeolocation.getCurrentPosition(options).then(function(position){

      var latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);

      map = iniciaMapa(position);
      map.setOptions({styles: styles});
  		//Espera o Mapa carregar para adionar o indicador do usuario.
  		google.maps.event.addListenerOnce(map, 'idle', function(){

  			markerUser = new google.maps.Marker({
  			    map: map,
  			    animation: google.maps.Animation.DROP,
  			    position: latLng,
            icon: 'img/marcadores/user.png'
  			 });

  		  	addListener(markerUser);

  		});

      getBolhasRange(position.coords.latitude, position.coords.longitude);

    }, function(error){
      console.log("Could not get location");
    });

  }

  /* Função responsavél por carregar as bolhas ao redor do usario. */
  function getBolhasRange(_lat, _longi){
    var promise = JSONBolhaFactory.getBolhasRange(_lat, _longi);
    promise.then(function successCallback(_response){
      /*Cria a Variavel de cluster para as bolhas.*/
      var bolhas      = [];
      bolhas.push(markerUser);

      var latlngbounds = new google.maps.LatLngBounds();
      var pathImage    = '../../img/marcadores/m';
      var mkOptions    = {gridSize: 50, maxZoom: zoomMinimo, imagePath: pathImage, enableRetinaIcons: true};


      var records  = _response.data;

      for (var i = 0; i < records.length; i++) {

        var record = records[i];
        var bolhaPos = new google.maps.LatLng(record.latitude, record.longitude);
        // Add the marker to the map
        var bolha = addBolha(bolhaPos);
        addListener(bolha);
        bolhas.push(bolha);

        latlngbounds.extend(bolha.position);
      }

      var markerCluster = new MarkerClusterer(map, bolhas, mkOptions);
      //Zoom automatico para colcoar todos os markers na tela.
      //map.fitBounds(latlngbounds);

    }, function errorCallback(response){
      alert("Não foi possível recuperar os dados do servidor!");
    });
  };

  function addBolha(position){
    var bolha = new google.maps.Marker({
                                          map: map,
                                          animation: google.maps.Animation.DROP,
                                          position: position,
                                          icon: 'img/marcadores/blue.png'
                                        });
    return bolha;
  }

  function addListener(marker) {

    google.maps.event.addListener(marker, 'click', function () {

      var zoom = Math.max(zoomMinimo, map.getZoom());

      map.setZoom(zoom);

      map.setCenter(this.position);

    });

    google.maps.event.addListener(map, 'click', function(){
    });

  }

  /*Funções de retorno para os chamadores*/
  return {
    init: function(){
      initMap();
    }
  }
})
