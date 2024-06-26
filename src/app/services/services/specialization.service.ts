/* tslint:disable */
/* eslint-disable */
import {HttpClient, HttpContext} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {BaseService} from '../base-service';
import {ApiConfiguration} from '../api-configuration';
import {StrictHttpResponse} from '../strict-http-response';

import {deleteSpecialization} from '../fn/specialization/delete-specialization';
import {DeleteSpecialization$Params} from '../fn/specialization/delete-specialization';
import {findAllSpecializations} from '../fn/specialization/find-all-specializations';
import {FindAllSpecializations$Params} from '../fn/specialization/find-all-specializations';
import {findSpecializationById} from '../fn/specialization/find-specialization-by-id';
import {FindSpecializationById$Params} from '../fn/specialization/find-specialization-by-id';
import {saveSpecialization} from '../fn/specialization/save-specialization';
import {SaveSpecialization$Params} from '../fn/specialization/save-specialization';
import {SpecializationDto} from '../models/specialization-dto';
import {updateSpecialization} from '../fn/specialization/update-specialization';
import {UpdateSpecialization$Params} from '../fn/specialization/update-specialization';


/**
 * The Specialization API
 */
@Injectable({providedIn: 'root'})
export class SpecializationService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllSpecializations()` */
  static readonly FindAllSpecializationsPath = '/specializations';

  /**
   * Find all specializations.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllSpecializations()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllSpecializations$Response(params?: FindAllSpecializations$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<SpecializationDto>>> {
    return findAllSpecializations(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all specializations.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllSpecializations$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllSpecializations(params?: FindAllSpecializations$Params, context?: HttpContext): Observable<Array<SpecializationDto>> {
    return this.findAllSpecializations$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<SpecializationDto>>): Array<SpecializationDto> => r.body)
    );
  }

  /** Path part for operation `saveSpecialization()` */
  static readonly SaveSpecializationPath = '/specializations';

  /**
   * Save specialization.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveSpecialization()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveSpecialization$Response(params: SaveSpecialization$Params, context?: HttpContext): Observable<StrictHttpResponse<SpecializationDto>> {
    return saveSpecialization(this.http, this.rootUrl, params, context);
  }

  /**
   * Save specialization.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveSpecialization$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveSpecialization(params: SaveSpecialization$Params, context?: HttpContext): Observable<SpecializationDto> {
    return this.saveSpecialization$Response(params, context).pipe(
      map((r: StrictHttpResponse<SpecializationDto>): SpecializationDto => r.body)
    );
  }

  /** Path part for operation `deleteSpecialization()` */
  static readonly DeleteSpecializationPath = '/specializations/{id}';

  /**
   * Delete specialization.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteSpecialization()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteSpecialization$Response(params?: DeleteSpecialization$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteSpecialization(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete specialization.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteSpecialization$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteSpecialization(params?: DeleteSpecialization$Params, context?: HttpContext): Observable<void> {
    return this.deleteSpecialization$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `updateSpecialization()` */
  static readonly UpdateSpecializationPath = '/specializations';

  /**
   * Update specialization.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateSpecialization()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateSpecialization$Response(params: UpdateSpecialization$Params, context?: HttpContext): Observable<StrictHttpResponse<SpecializationDto>> {
    return updateSpecialization(this.http, this.rootUrl, params, context);
  }

  /**
   * Update specialization.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateSpecialization$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateSpecialization(params: UpdateSpecialization$Params, context?: HttpContext): Observable<SpecializationDto> {
    return this.updateSpecialization$Response(params, context).pipe(
      map((r: StrictHttpResponse<SpecializationDto>): SpecializationDto => r.body)
    );
  }

  /** Path part for operation `findSpecializationById()` */
  static readonly FindSpecializationByIdPath = '/specializations/{id}';

  /**
   * Find specialization by id.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findSpecializationById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findSpecializationById$Response(params: FindSpecializationById$Params, context?: HttpContext): Observable<StrictHttpResponse<SpecializationDto>> {
    return findSpecializationById(this.http, this.rootUrl, params, context);
  }

  /**
   * Find specialization by id.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findSpecializationById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findSpecializationById(params: FindSpecializationById$Params, context?: HttpContext): Observable<SpecializationDto> {
    return this.findSpecializationById$Response(params, context).pipe(
      map((r: StrictHttpResponse<SpecializationDto>): SpecializationDto => r.body)
    );
  }

}
