import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {TokenProvider} from "../../service/token-provider";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private authService: AuthService, private tokenProvider: TokenProvider, private router: Router) {
  }

  ngOnInit(): void {
    if (!localStorage.getItem('firstReload') || localStorage.getItem('firstReload') == 'true') {
      localStorage.setItem('firstReload', 'false');
      window.location.reload();
    } else {
      localStorage.setItem('firstReload', 'true');
    }
  }

  onRegistry(registryForm: NgForm) {

  }
}
