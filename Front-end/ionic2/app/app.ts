// Componentes
import { Component, ViewChild } from '@angular/core';
import { ionicBootstrap, Platform, Nav} from 'ionic-angular';
import { StatusBar } from 'ionic-native';

// Páginas
import {HomePage} from './pages/home/home';

@Component({
  templateUrl: 'build/app.html'
})

export class Dag {
  rootPage: any = HomePage;

  constructor(platform: Platform) {
    platform.ready().then(() => {
      StatusBar.styleDefault();
    });
  }
}

ionicBootstrap(Dag, [], {
  modalEnter: 'modal-md-slide-in',
  modalLeave: 'modal-md-slide-out',
  pageTransition: 'md',
});
