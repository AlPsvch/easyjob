import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {User} from "../model/user/user";
import {UserLogin} from "../model/user/user-login";
import {TokenProvider} from "./token-provider";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient, private tokenProvider: TokenProvider) {
  }

  public register(user: User): Observable<any> {
    return this.http.post<User>(this.apiServerUrl + '/user/register', user)
  }

  public login(userLogin: UserLogin): Observable<any> {
    return this.http.post<User>(this.apiServerUrl + '/user/login', userLogin)
  }

  public logout(): void {
    this.tokenProvider.removeToken();
  }
}
