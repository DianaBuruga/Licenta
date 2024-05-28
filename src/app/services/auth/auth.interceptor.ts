import {HttpHeaders, HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor : HttpInterceptorFn = (req, next) => {
    console.log('am incerceptat requestul');
    const isBrowser = typeof window !== 'undefined'
    let authToken = "";
    if (isBrowser) {
       authToken = localStorage.getItem('token')??'';
    }
    if (authToken) {
      const authReq = req.clone({
        headers: new HttpHeaders({
          Authorization: "Bearer " + authToken
        })
      });
      return next(authReq);
    }
    return next(req);
}
