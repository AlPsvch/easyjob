import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {TokenProvider} from "../../service/token-provider";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {CheckboxItem} from "../../model/checkbox-item";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  personalityTypes: CheckboxItem[];

  constructor(private authService: AuthService, private tokenProvider: TokenProvider, private router: Router) {
  }

  ngOnInit(): void {
    if (!localStorage.getItem('firstReload') || localStorage.getItem('firstReload') == 'true') {
      localStorage.setItem('firstReload', 'false');
      window.location.reload();
    } else {
      localStorage.setItem('firstReload', 'true');
    }

    this.getPersonalityTypes();
  }

  onRegistry(registryForm: NgForm) {
    this.authService.register(registryForm.value).subscribe(
      response => {
        this.tokenProvider.saveToken(response['token']);
        this.router.navigate(['/home']);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    )
  }

  getPersonalityTypes() {
    this.personalityTypes = [
      {code: "ESTJ", description: "ESTJ - УПРАВЛЕНЕЦ", isSelected: false},
      {code: "ENTJ", description: "ENTJ - КОМАНДИР", isSelected: false},
      {code: "ESFJ", description: "ESFJ - УЧИТЕЛЬ", isSelected: false},
      {code: "ESTP", description: "ESTP - МАРШАЛ", isSelected: false},
      {code: "ENFJ", description: "ENFJ - НАСТАВНИК", isSelected: false},
      {code: "ENTP", description: "ENTP - ИЗОБРЕТАТЕЛЬ", isSelected: false},
      {code: "ESFP", description: "ESFP - ПОЛИТИК", isSelected: false},
      {code: "ENFP", description: "ENFP - ЧЕМПИОН", isSelected: false},
      {code: "INFP", description: "INFP - ЦЕЛИТЕЛЬ", isSelected: false},
      {code: "ISFP", description: "ISFP - КОМПОЗИТОР", isSelected: false},
      {code: "INTP", description: "INTP - АРХИТЕКТОР", isSelected: false},
      {code: "INFJ", description: "INFJ - СОВЕТНИК", isSelected: false},
      {code: "INTJ", description: "INTJ - ВДОХНОВИТЕЛЬ", isSelected: false},
      {code: "ISFJ", description: "ISFJ - ЗАЩИТНИК", isSelected: false},
      {code: "ISTP", description: "ISTP - УМЕЛЕЦ", isSelected: false},
      {code: "ISTJ", description: "ISTJ - ИНСПЕКТОР", isSelected: false}
    ]
  }
}
