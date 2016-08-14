import {Component} from '@angular/core';
import {NavController, ModalController, Platform, NavParams, ViewController} from 'ionic-angular';

import {MenuPage} from '../menu/menu';

@Component({
  templateUrl: 'build/pages/home/home.html'
})
export class HomePage {

  constructor(public navCtrl: NavController, public modalCtrl: ModalController) {
    this.loadGoogleMaps();
  }

  map: any;
  apiKey: any = 'AIzaSyAu6Qs0-M9n_0f5kChH6RjzOgoD8qX39qk';


  openModal() {
    let modal = this.modalCtrl.create(MenuPage);
    modal.present();
  }

  loadGoogleMaps(){
    if(typeof google == "undefined" || typeof google.maps == "undefined"){

      console.log("online, loading map");

      //Load the SDK
      (<any>window).mapInit=() => {
                        console.log("MAPINIT FUNCTION")
                          this.initMap();
                      }

      let script = document.createElement("script");
      script.id = "googleMaps";

      if(this.apiKey){
          console.log("API EXISTS");
          script.src = 'http://maps.google.com/maps/api/js?key=' + this.apiKey + '&callback=mapInit';
      } else {
          script.src = 'http://maps.google.com/maps/api/js?callback=mapInit';
      }

      document.body.appendChild(script);


    }else {
      console.log("showing map");
      this.initMap();
    }

  }

  initMap(){
    let styles = [{"stylers":[{"hue":"#ff1a00"},{"invert_lightness":true},{"saturation":-100},{"lightness":25},{"gamma":0.5}]},{"featureType":"water","elementType":"geometry","stylers":[{"color":"#2D333C"}]},{"featureType":"poi","stylers":[{"visibility":"off"}]}];

    navigator.geolocation.getCurrentPosition( (position) => {

      let latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
      console.log(latLng);

      let mapOptions = {
        center: latLng,
        zoom: 15,
        minZoom:12,
        maxZoom: 20,
        styles:styles,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        disableDefaultUI: true
      }

      this.map = new google.maps.Map(document.getElementById("map"), mapOptions);
      console.log(this.map);
      let marker = new google.maps.Marker({
          map: this.map,
          icon: 'img/marcadores/user.png',
          animation: google.maps.Animation.DROP,
          position: latLng
      });

      marker.addListener('click', function() {
        this.map.setCenter(marker.getPosition());
      });

    }, (error) => {
        console.error("this is error",error);
    });
  }

  centerMap(){
    navigator.geolocation.getCurrentPosition( (position) => {
      this.map.setCenter(new google.maps.LatLng(position.coords.latitude, position.coords.longitude));
    }, (error) => {
        console.error("this is error",error);
    });
  }
}
