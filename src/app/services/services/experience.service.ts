/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { deleteExperience } from '../fn/experience/delete-experience';
import { DeleteExperience$Params } from '../fn/experience/delete-experience';
import { ExperienceDto } from '../models/experience-dto';
import { findAllExperiences } from '../fn/experience/find-all-experiences';
import { FindAllExperiences$Params } from '../fn/experience/find-all-experiences';
import { findByCriteria11 } from '../fn/experience/find-by-criteria-11';
import { FindByCriteria11$Params } from '../fn/experience/find-by-criteria-11';
import { findExperienceById } from '../fn/experience/find-experience-by-id';
import { FindExperienceById$Params } from '../fn/experience/find-experience-by-id';
import { saveExperience } from '../fn/experience/save-experience';
import { SaveExperience$Params } from '../fn/experience/save-experience';
import { updateExperience } from '../fn/experience/update-experience';
import { UpdateExperience$Params } from '../fn/experience/update-experience';


/**
 * The Experience API
 */
@Injectable({ providedIn: 'root' })
export class ExperienceService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllExperiences()` */
  static readonly FindAllExperiencesPath = '/experiences';

  /**
   * Find all experiences.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllExperiences()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllExperiences$Response(params?: FindAllExperiences$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<ExperienceDto>>> {
    return findAllExperiences(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all experiences.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllExperiences$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllExperiences(params?: FindAllExperiences$Params, context?: HttpContext): Observable<Array<ExperienceDto>> {
    return this.findAllExperiences$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<ExperienceDto>>): Array<ExperienceDto> => r.body)
    );
  }

  /** Path part for operation `saveExperience()` */
  static readonly SaveExperiencePath = '/experiences';

  /**
   * Save experience.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveExperience()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveExperience$Response(params: SaveExperience$Params, context?: HttpContext): Observable<StrictHttpResponse<ExperienceDto>> {
    return saveExperience(this.http, this.rootUrl, params, context);
  }

  /**
   * Save experience.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveExperience$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveExperience(params: SaveExperience$Params, context?: HttpContext): Observable<ExperienceDto> {
    return this.saveExperience$Response(params, context).pipe(
      map((r: StrictHttpResponse<ExperienceDto>): ExperienceDto => r.body)
    );
  }

  /** Path part for operation `updateExperience()` */
  static readonly UpdateExperiencePath = '/experiences';

  /**
   * Update experience.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateExperience()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateExperience$Response(params: UpdateExperience$Params, context?: HttpContext): Observable<StrictHttpResponse<ExperienceDto>> {
    return updateExperience(this.http, this.rootUrl, params, context);
  }

  /**
   * Update experience.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateExperience$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateExperience(params: UpdateExperience$Params, context?: HttpContext): Observable<ExperienceDto> {
    return this.updateExperience$Response(params, context).pipe(
      map((r: StrictHttpResponse<ExperienceDto>): ExperienceDto => r.body)
    );
  }

  /** Path part for operation `findByCriteria11()` */
  static readonly FindByCriteria11Path = '/experiences/by-criteria/';

  /**
   * Find experience by criteria.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findByCriteria11()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  findByCriteria11$Response(params: FindByCriteria11$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<ExperienceDto>>> {
    return findByCriteria11(this.http, this.rootUrl, params, context);
  }

  /**
   * Find experience by criteria.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findByCriteria11$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  findByCriteria11(params: FindByCriteria11$Params, context?: HttpContext): Observable<Array<ExperienceDto>> {
    return this.findByCriteria11$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<ExperienceDto>>): Array<ExperienceDto> => r.body)
    );
  }

  /** Path part for operation `findExperienceById()` */
  static readonly FindExperienceByIdPath = '/experiences/{id}';

  /**
   * Find experience by id.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findExperienceById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findExperienceById$Response(params: FindExperienceById$Params, context?: HttpContext): Observable<StrictHttpResponse<ExperienceDto>> {
    return findExperienceById(this.http, this.rootUrl, params, context);
  }

  /**
   * Find experience by id.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findExperienceById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findExperienceById(params: FindExperienceById$Params, context?: HttpContext): Observable<ExperienceDto> {
    return this.findExperienceById$Response(params, context).pipe(
      map((r: StrictHttpResponse<ExperienceDto>): ExperienceDto => r.body)
    );
  }

  /** Path part for operation `deleteExperience()` */
  static readonly DeleteExperiencePath = '/experiences/{id}';

  /**
   * Delete experience.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteExperience()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteExperience$Response(params: DeleteExperience$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteExperience(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete experience.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteExperience$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteExperience(params: DeleteExperience$Params, context?: HttpContext): Observable<void> {
    return this.deleteExperience$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
