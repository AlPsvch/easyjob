import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {TokenProvider} from "./token-provider";
import {catchError} from "rxjs/operators";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor {

  constructor(private tokenProvider: TokenProvider) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
    let authReq = req;

    console.log('HELLO');
    if (this.tokenProvider.getToken() != null) {
      console.log('HELLO 2');
      authReq = req.clone({
        headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.tokenProvider.getToken())}
      );
      console.log(authReq);
      console.log(req);
    }

    return next.handle(authReq).pipe(catchError((error: any) => {
        console.log('ERROR')
      console.log(error.message);
        return throwError(error);
      }
    ));
  }
}
