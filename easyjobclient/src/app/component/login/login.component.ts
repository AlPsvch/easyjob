import {Component, OnInit} from '@angular/core';
import {NgForm} from "@angular/forms";
import {AuthService} from "../../service/auth.service";
import {TokenProvider} from "../../service/token-provider";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

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

  onLogin(loginForm: NgForm) {
    this.authService.login(loginForm.value).subscribe(
      response => {
        this.tokenProvider.saveToken(response['token']);
        this.router.navigate(['/home']);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    )
  }

}
