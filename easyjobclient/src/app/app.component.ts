import {Component, OnInit} from '@angular/core';
import 'src/app/resources/images/bg_1.jpg'
import 'src/app/resources/images/company-1.jpg'
import 'src/app/resources/images/company-2.jpg'
import 'src/app/resources/images/company-3.jpg'
import 'src/app/resources/images/company-4.jpg'
import 'src/app/resources/images/image_1.jpg'
import 'src/app/resources/images/image_2.jpg'
import 'src/app/resources/images/image_3.jpg'
import 'src/app/resources/images/image_4.jpg'
import 'src/app/resources/images/image_5.jpg'
import 'src/app/resources/images/image_6.jpg'
import 'src/app/resources/images/image_7.jpg'
import 'src/app/resources/images/image_8.jpg'
import 'src/app/resources/images/loc.png'
import 'src/app/resources/images/person_1.jpg'
import 'src/app/resources/images/person_2.jpg'
import 'src/app/resources/images/person_3.jpg'
import 'src/app/resources/images/person_4.jpg'
import 'src/app/resources/images/person_5.jpg'
import 'src/app/resources/images/person_6.jpg'
import 'src/app/resources/js/main.js'
import 'src/app/resources/js/aos'
import 'src/app/resources/js/jquery.animateNumber.min'
import 'src/app/resources/js/jquery.easing.1.3'
import 'src/app/resources/js/jquery.min'
import 'src/app/resources/js/jquery.stellar.min'
import 'src/app/resources/js/jquery.waypoints.min'
import 'src/app/resources/js/jquery-3.2.1.min'
import 'src/app/resources/js/owl.carousel.min'
import 'src/app/resources/js/popper.min'
import 'src/app/resources/js/range'
import {TokenProvider} from "./service/token-provider";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'easyjobclient';

  public token: string = '';

  constructor(private tokenProvider: TokenProvider) {
  }

  ngOnInit() {
    this.token = this.tokenProvider.getToken();
    console.log('token: ' + this.token);
  }
}
