import { Component } from '@angular/core';
import { NavController, ViewController, NavParams} from 'ionic-angular';

import {SearchPage} from '../search/search';
/*
  Generated class for the MenuPage page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  templateUrl: 'build/pages/menu/menu.html',
})
export class MenuPage {

  map: google.maps.Map;

  constructor(private navCtrl: NavController, private viewCtrl: ViewController, params: NavParams) {
    this.map = params.get('map');
  }

  searchTapped(event){
    this.navCtrl.push(SearchPage,{
      map: this.map
    });
  }

  dismiss() {
    this.viewCtrl.dismiss();
  }
}
