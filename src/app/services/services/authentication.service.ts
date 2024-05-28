/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { auth } from '../fn/authentication/auth';
import { Auth$Params } from '../fn/authentication/auth';
import { callback } from '../fn/authentication/callback';
import { Callback$Params } from '../fn/authentication/callback';
import { TokenDto } from '../models/token-dto';
import { UrlDto } from '../models/url-dto';


/**
 * The Authentication API
 */
@Injectable({ providedIn: 'root' })
export class AuthenticationService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `auth()` */
  static readonly AuthPath = '/auth/url';

  /**
   * Login.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `auth()` instead.
   *
   * This method doesn't expect any request body.
   */
  auth$Response(params?: Auth$Params, context?: HttpContext): Observable<StrictHttpResponse<UrlDto>> {
    return auth(this.http, this.rootUrl, params, context);
  }

  /**
   * Login.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `auth$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  auth(params?: Auth$Params, context?: HttpContext): Observable<UrlDto> {
    return this.auth$Response(params, context).pipe(
      map((r: StrictHttpResponse<UrlDto>): UrlDto => r.body)
    );
  }

  /** Path part for operation `callback()` */
  static readonly CallbackPath = '/auth/callback';

  /**
   * Login.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `callback()` instead.
   *
   * This method doesn't expect any request body.
   */
  callback$Response(params: Callback$Params, context?: HttpContext): Observable<StrictHttpResponse<TokenDto>> {
    return callback(this.http, this.rootUrl, params, context);
  }

  /**
   * Login.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `callback$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  callback(params: Callback$Params, context?: HttpContext): Observable<TokenDto> {
    return this.callback$Response(params, context).pipe(
      map((r: StrictHttpResponse<TokenDto>): TokenDto => r.body)
    );
  }

}
