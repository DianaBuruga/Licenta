/* tslint:disable */
/* eslint-disable */
import {HttpClient, HttpContext} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {BaseService} from '../base-service';
import {ApiConfiguration} from '../api-configuration';
import {StrictHttpResponse} from '../strict-http-response';

import {home, Home$Params} from '../fn/authentication-controller/home';
import {home1, Home1$Params} from '../fn/authentication-controller/home-1';
import {home2, Home2$Params} from '../fn/authentication-controller/home-2';
import {home3, Home3$Params} from '../fn/authentication-controller/home-3';
import {home4, Home4$Params} from '../fn/authentication-controller/home-4';
import {home5, Home5$Params} from '../fn/authentication-controller/home-5';
import {home6, Home6$Params} from '../fn/authentication-controller/home-6';
import {isAuthenticated, IsAuthenticated$Params} from '../fn/authentication-controller/is-authenticated';
import {isAuthenticated1, IsAuthenticated1$Params} from '../fn/authentication-controller/is-authenticated-1';
import {isAuthenticated2, IsAuthenticated2$Params} from '../fn/authentication-controller/is-authenticated-2';
import {isAuthenticated3, IsAuthenticated3$Params} from '../fn/authentication-controller/is-authenticated-3';
import {isAuthenticated4, IsAuthenticated4$Params} from '../fn/authentication-controller/is-authenticated-4';
import {isAuthenticated5, IsAuthenticated5$Params} from '../fn/authentication-controller/is-authenticated-5';
import {isAuthenticated6, IsAuthenticated6$Params} from '../fn/authentication-controller/is-authenticated-6';
import {login, Login$Params} from '../fn/authentication-controller/login';
import {welcome1, Welcome1$Params} from '../fn/authentication-controller/welcome-1';

@Injectable({providedIn: 'root'})
export class AuthenticationControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `login()` */
  static readonly LoginPath = '/auth/google';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `login()` instead.
   *
   * This method doesn't expect any request body.
   */
  login$Response(params?: Login$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return login(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `login$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  login(params?: Login$Params, context?: HttpContext): Observable<string> {
    return this.login$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

  /** Path part for operation `welcome1()` */
  static readonly Welcome1Path = '/';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `welcome1()` instead.
   *
   * This method doesn't expect any request body.
   */
  welcome1$Response(params?: Welcome1$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return welcome1(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `welcome1$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  welcome1(params?: Welcome1$Params, context?: HttpContext): Observable<string> {
    return this.welcome1$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

  /** Path part for operation `isAuthenticated()` */
  static readonly IsAuthenticatedPath = '/isAuthenticated';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `isAuthenticated()` instead.
   *
   * This method doesn't expect any request body.
   */
  isAuthenticated$Response(params?: IsAuthenticated$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return isAuthenticated(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `isAuthenticated$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  isAuthenticated(params?: IsAuthenticated$Params, context?: HttpContext): Observable<boolean> {
    return this.isAuthenticated$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  /** Path part for operation `isAuthenticated3()` */
  static readonly IsAuthenticated3Path = '/isAuthenticated';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `isAuthenticated3()` instead.
   *
   * This method doesn't expect any request body.
   */
  isAuthenticated3$Response(params?: IsAuthenticated3$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return isAuthenticated3(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `isAuthenticated3$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  isAuthenticated3(params?: IsAuthenticated3$Params, context?: HttpContext): Observable<boolean> {
    return this.isAuthenticated3$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  /** Path part for operation `isAuthenticated2()` */
  static readonly IsAuthenticated2Path = '/isAuthenticated';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `isAuthenticated2()` instead.
   *
   * This method doesn't expect any request body.
   */
  isAuthenticated2$Response(params?: IsAuthenticated2$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return isAuthenticated2(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `isAuthenticated2$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  isAuthenticated2(params?: IsAuthenticated2$Params, context?: HttpContext): Observable<boolean> {
    return this.isAuthenticated2$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  /** Path part for operation `isAuthenticated5()` */
  static readonly IsAuthenticated5Path = '/isAuthenticated';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `isAuthenticated5()` instead.
   *
   * This method doesn't expect any request body.
   */
  isAuthenticated5$Response(params?: IsAuthenticated5$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return isAuthenticated5(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `isAuthenticated5$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  isAuthenticated5(params?: IsAuthenticated5$Params, context?: HttpContext): Observable<boolean> {
    return this.isAuthenticated5$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  /** Path part for operation `isAuthenticated6()` */
  static readonly IsAuthenticated6Path = '/isAuthenticated';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `isAuthenticated6()` instead.
   *
   * This method doesn't expect any request body.
   */
  isAuthenticated6$Response(params?: IsAuthenticated6$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return isAuthenticated6(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `isAuthenticated6$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  isAuthenticated6(params?: IsAuthenticated6$Params, context?: HttpContext): Observable<boolean> {
    return this.isAuthenticated6$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  /** Path part for operation `isAuthenticated1()` */
  static readonly IsAuthenticated1Path = '/isAuthenticated';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `isAuthenticated1()` instead.
   *
   * This method doesn't expect any request body.
   */
  isAuthenticated1$Response(params?: IsAuthenticated1$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return isAuthenticated1(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `isAuthenticated1$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  isAuthenticated1(params?: IsAuthenticated1$Params, context?: HttpContext): Observable<boolean> {
    return this.isAuthenticated1$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  /** Path part for operation `isAuthenticated4()` */
  static readonly IsAuthenticated4Path = '/isAuthenticated';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `isAuthenticated4()` instead.
   *
   * This method doesn't expect any request body.
   */
  isAuthenticated4$Response(params?: IsAuthenticated4$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return isAuthenticated4(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `isAuthenticated4$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  isAuthenticated4(params?: IsAuthenticated4$Params, context?: HttpContext): Observable<boolean> {
    return this.isAuthenticated4$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  /** Path part for operation `home()` */
  static readonly HomePath = '/home';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `home()` instead.
   *
   * This method doesn't expect any request body.
   */
  home$Response(params?: Home$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return home(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `home$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  home(params?: Home$Params, context?: HttpContext): Observable<string> {
    return this.home$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

  /** Path part for operation `home3()` */
  static readonly Home3Path = '/home';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `home3()` instead.
   *
   * This method doesn't expect any request body.
   */
  home3$Response(params?: Home3$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return home3(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `home3$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  home3(params?: Home3$Params, context?: HttpContext): Observable<string> {
    return this.home3$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

  /** Path part for operation `home2()` */
  static readonly Home2Path = '/home';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `home2()` instead.
   *
   * This method doesn't expect any request body.
   */
  home2$Response(params?: Home2$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return home2(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `home2$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  home2(params?: Home2$Params, context?: HttpContext): Observable<string> {
    return this.home2$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

  /** Path part for operation `home5()` */
  static readonly Home5Path = '/home';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `home5()` instead.
   *
   * This method doesn't expect any request body.
   */
  home5$Response(params?: Home5$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return home5(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `home5$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  home5(params?: Home5$Params, context?: HttpContext): Observable<string> {
    return this.home5$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

  /** Path part for operation `home6()` */
  static readonly Home6Path = '/home';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `home6()` instead.
   *
   * This method doesn't expect any request body.
   */
  home6$Response(params?: Home6$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return home6(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `home6$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  home6(params?: Home6$Params, context?: HttpContext): Observable<string> {
    return this.home6$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

  /** Path part for operation `home1()` */
  static readonly Home1Path = '/home';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `home1()` instead.
   *
   * This method doesn't expect any request body.
   */
  home1$Response(params?: Home1$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return home1(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `home1$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  home1(params?: Home1$Params, context?: HttpContext): Observable<string> {
    return this.home1$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

  /** Path part for operation `home4()` */
  static readonly Home4Path = '/home';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `home4()` instead.
   *
   * This method doesn't expect any request body.
   */
  home4$Response(params?: Home4$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return home4(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `home4$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  home4(params?: Home4$Params, context?: HttpContext): Observable<string> {
    return this.home4$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

}
