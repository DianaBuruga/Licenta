import {HttpHeaders, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { TokenService } from './token.service';

export const authInterceptor : HttpInterceptorFn = (req, next) => {
    console.log('am incerceptat requestul');
    const tokenService = inject(TokenService) as TokenService;
    
    const authToken = tokenService.token;
    if (authToken || authToken !== "") {
      const authReq = req.clone({
        headers: new HttpHeaders({
          Authorization: "Bearer " + authToken
        })
      });
      return next(authReq);
    }
    return next(req);
}
