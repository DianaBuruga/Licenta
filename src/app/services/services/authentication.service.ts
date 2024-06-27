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
import { getUserRole } from '../fn/authentication/get-user-role';
import { GetUserRole$Params } from '../fn/authentication/get-user-role';
import { isOwner } from '../fn/authentication/is-owner';
import { IsOwner } from '../models/is-owner';
import { IsOwner$Params } from '../fn/authentication/is-owner';
import { Role } from '../models/role';
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

  /** Path part for operation `isOwner()` */
  static readonly IsOwnerPath = '/isOwner/{endpoint}/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `isOwner()` instead.
   *
   * This method doesn't expect any request body.
   */
  isOwner$Response(params: IsOwner$Params, context?: HttpContext): Observable<StrictHttpResponse<IsOwner>> {
    return isOwner(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `isOwner$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  isOwner(params: IsOwner$Params, context?: HttpContext): Observable<IsOwner> {
    return this.isOwner$Response(params, context).pipe(
      map((r: StrictHttpResponse<IsOwner>): IsOwner => r.body)
    );
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

  /** Path part for operation `getUserRole()` */
  static readonly GetUserRolePath = '/auth/role';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getUserRole()` instead.
   *
   * This method doesn't expect any request body.
   */
  getUserRole$Response(params?: GetUserRole$Params, context?: HttpContext): Observable<StrictHttpResponse<Role>> {
    return getUserRole(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getUserRole$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getUserRole(params?: GetUserRole$Params, context?: HttpContext): Observable<Role> {
    return this.getUserRole$Response(params, context).pipe(
      map((r: StrictHttpResponse<Role>): Role =>  {
        return r.body;})
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
