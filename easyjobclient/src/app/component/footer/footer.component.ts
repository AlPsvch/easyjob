import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {TokenProvider} from "../../service/token-provider";
import {Router} from "@angular/router";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  public token: string = '';

  constructor(private authService: AuthService, private tokenProvider: TokenProvider, private router: Router) { }

  ngOnInit(): void {
    this.token = this.tokenProvider.getToken();
  }

  logout() {
    this.authService.logout();
    this.token = '';
    window.location.reload();
    this.router.navigate(['/home']);
  }

}
