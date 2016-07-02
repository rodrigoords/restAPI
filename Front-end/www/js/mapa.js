var makers = [];
var mapaNormal, mapaCalor;

function iniciaMapa(position){

	var mapaRetorno;

	var mapOptions = {
	  center: new google.maps.LatLng(position.coords.latitude, position.coords.longitude),
	  zoom: 15,
		minZoom:12,
		maxZoom: 20,
	  mapTypeId: google.maps.MapTypeId.ROADMAP,
	  disableDefaultUI: true
	};

	mapaNormal = new google.maps.Map(document.getElementById("map"), mapOptions);

	mapaRetorno = mapaNormal;

	mapaCalor   = new google.maps.visualization.HeatmapLayer({
					 data: [new google.maps.LatLng(position.coords.latitude, position.coords.longitude)],
				  	 map: mapaNormal
				    });

 	mudaVisualMapa();

	return mapaRetorno;
}

function mudaVisualMapa(){
	mapaCalor.setMap(mapaCalor.getMap() ? null : mapaNormal);
}
