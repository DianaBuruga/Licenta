/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { deleteReferral } from '../fn/referral/delete-referral';
import { DeleteReferral$Params } from '../fn/referral/delete-referral';
import { findAllReferrals } from '../fn/referral/find-all-referrals';
import { FindAllReferrals$Params } from '../fn/referral/find-all-referrals';
import { findReferralById } from '../fn/referral/find-referral-by-id';
import { FindReferralById$Params } from '../fn/referral/find-referral-by-id';
import { ReferralDto } from '../models/referral-dto';
import { saveReferral } from '../fn/referral/save-referral';
import { SaveReferral$Params } from '../fn/referral/save-referral';
import { updateReferral } from '../fn/referral/update-referral';
import { UpdateReferral$Params } from '../fn/referral/update-referral';


/**
 * The Referral API
 */
@Injectable({ providedIn: 'root' })
export class ReferralService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllReferrals()` */
  static readonly FindAllReferralsPath = '/referrals';

  /**
   * Find all referrals.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllReferrals()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllReferrals$Response(params?: FindAllReferrals$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<ReferralDto>>> {
    return findAllReferrals(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all referrals.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllReferrals$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllReferrals(params?: FindAllReferrals$Params, context?: HttpContext): Observable<Array<ReferralDto>> {
    return this.findAllReferrals$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<ReferralDto>>): Array<ReferralDto> => r.body)
    );
  }

  /** Path part for operation `saveReferral()` */
  static readonly SaveReferralPath = '/referrals';

  /**
   * Save referral.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveReferral()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveReferral$Response(params: SaveReferral$Params, context?: HttpContext): Observable<StrictHttpResponse<ReferralDto>> {
    return saveReferral(this.http, this.rootUrl, params, context);
  }

  /**
   * Save referral.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveReferral$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveReferral(params: SaveReferral$Params, context?: HttpContext): Observable<ReferralDto> {
    return this.saveReferral$Response(params, context).pipe(
      map((r: StrictHttpResponse<ReferralDto>): ReferralDto => r.body)
    );
  }

  /** Path part for operation `updateReferral()` */
  static readonly UpdateReferralPath = '/referrals';

  /**
   * Update referral.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateReferral()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateReferral$Response(params: UpdateReferral$Params, context?: HttpContext): Observable<StrictHttpResponse<ReferralDto>> {
    return updateReferral(this.http, this.rootUrl, params, context);
  }

  /**
   * Update referral.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateReferral$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateReferral(params: UpdateReferral$Params, context?: HttpContext): Observable<ReferralDto> {
    return this.updateReferral$Response(params, context).pipe(
      map((r: StrictHttpResponse<ReferralDto>): ReferralDto => r.body)
    );
  }

  /** Path part for operation `findReferralById()` */
  static readonly FindReferralByIdPath = '/referrals/{id}';

  /**
   * Find referral by id.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findReferralById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findReferralById$Response(params: FindReferralById$Params, context?: HttpContext): Observable<StrictHttpResponse<ReferralDto>> {
    return findReferralById(this.http, this.rootUrl, params, context);
  }

  /**
   * Find referral by id.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findReferralById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findReferralById(params: FindReferralById$Params, context?: HttpContext): Observable<ReferralDto> {
    return this.findReferralById$Response(params, context).pipe(
      map((r: StrictHttpResponse<ReferralDto>): ReferralDto => r.body)
    );
  }

  /** Path part for operation `deleteReferral()` */
  static readonly DeleteReferralPath = '/referrals/{id}';

  /**
   * Delete referral.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteReferral()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteReferral$Response(params: DeleteReferral$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteReferral(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete referral.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteReferral$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteReferral(params: DeleteReferral$Params, context?: HttpContext): Observable<void> {
    return this.deleteReferral$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
