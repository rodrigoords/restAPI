import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

/*
  Generated class for the SearchPage page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  templateUrl: 'build/pages/search/search.html',
})
export class SearchPage {

  map: google.maps.Map;
  input: any;
  searchBox: any;
  constructor(private navCtrl: NavController, params: NavParams ) {
    console.log('entrou no construtor :'+params);
    this.map = params.get('map');
    console.log(this.map);
    this.input = document.getElementById('searchBar');
    this.searchBox = new google.maps.places.SearchBox(this.input);
    this.map.controls[google.maps.ControlPosition.TOP_LEFT].push(this.input);
  }

}
