import {HttpErrorResponse, HttpHeaders, HttpInterceptorFn} from '@angular/common/http';
import {inject} from '@angular/core';
import {TokenService} from './token.service';
import {catchError} from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  console.log('am incerceptat requestul');
  const tokenService = inject(TokenService) as TokenService;

  const authToken = tokenService.token;
  if (authToken || authToken !== "") {
    const authReq = req.clone({
      headers: new HttpHeaders({
        Authorization: "Bearer " + tokenService.token
      })
    });

    return next(authReq).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          tokenService.deleteToken(authToken);
        }
        throw error;
      })
    );
  }
  return next(req);
}
