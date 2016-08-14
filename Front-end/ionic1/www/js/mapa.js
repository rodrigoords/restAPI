var makers = [];
var mapaNormal, mapaCalor;

function iniciaMapa(position){

	var mapOptions = {
	  center: new google.maps.LatLng(position.coords.latitude, position.coords.longitude),
	  zoom: 15,
		minZoom:12,
		maxZoom: 20,
	  mapTypeId: google.maps.MapTypeId.ROADMAP,
	  disableDefaultUI: true
	};

	mapaNormal = new google.maps.Map(document.getElementById("map"), mapOptions);

	mapaCalor  = new google.maps.visualization.HeatmapLayer({map: mapaNormal});

	return mapaNormal;
}

function mudaVisualMapa(){
	mapaCalor.setMap(mapaCalor.getMap() ? null : mapaNormal);
}

function getMapaCalor(){
	return mapaCalor;
}
