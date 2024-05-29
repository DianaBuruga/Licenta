/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { deleteFaculty } from '../fn/faculty/delete-faculty';
import { DeleteFaculty$Params } from '../fn/faculty/delete-faculty';
import { FacultyDto } from '../models/faculty-dto';
import { findAllFaculties } from '../fn/faculty/find-all-faculties';
import { FindAllFaculties$Params } from '../fn/faculty/find-all-faculties';
import { findByCriteria10 } from '../fn/faculty/find-by-criteria-10';
import { FindByCriteria10$Params } from '../fn/faculty/find-by-criteria-10';
import { findFacultyById } from '../fn/faculty/find-faculty-by-id';
import { FindFacultyById$Params } from '../fn/faculty/find-faculty-by-id';
import { saveFaculty } from '../fn/faculty/save-faculty';
import { SaveFaculty$Params } from '../fn/faculty/save-faculty';
import { updateFaculty } from '../fn/faculty/update-faculty';
import { UpdateFaculty$Params } from '../fn/faculty/update-faculty';


/**
 * The Faculty API
 */
@Injectable({ providedIn: 'root' })
export class FacultyService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllFaculties()` */
  static readonly FindAllFacultiesPath = '/faculties';

  /**
   * Find all faculties.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllFaculties()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllFaculties$Response(params?: FindAllFaculties$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<FacultyDto>>> {
    return findAllFaculties(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all faculties.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllFaculties$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllFaculties(params?: FindAllFaculties$Params, context?: HttpContext): Observable<Array<FacultyDto>> {
    return this.findAllFaculties$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<FacultyDto>>): Array<FacultyDto> => r.body)
    );
  }

  /** Path part for operation `saveFaculty()` */
  static readonly SaveFacultyPath = '/faculties';

  /**
   * Save faculty.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveFaculty()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveFaculty$Response(params: SaveFaculty$Params, context?: HttpContext): Observable<StrictHttpResponse<FacultyDto>> {
    return saveFaculty(this.http, this.rootUrl, params, context);
  }

  /**
   * Save faculty.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveFaculty$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveFaculty(params: SaveFaculty$Params, context?: HttpContext): Observable<FacultyDto> {
    return this.saveFaculty$Response(params, context).pipe(
      map((r: StrictHttpResponse<FacultyDto>): FacultyDto => r.body)
    );
  }

  /** Path part for operation `deleteFaculty()` */
  static readonly DeleteFacultyPath = '/faculties';

  /**
   * Delete faculty.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteFaculty()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteFaculty$Response(params: DeleteFaculty$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteFaculty(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete faculty.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteFaculty$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteFaculty(params: DeleteFaculty$Params, context?: HttpContext): Observable<void> {
    return this.deleteFaculty$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `updateFaculty()` */
  static readonly UpdateFacultyPath = '/faculties';

  /**
   * Update faculty.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateFaculty()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateFaculty$Response(params: UpdateFaculty$Params, context?: HttpContext): Observable<StrictHttpResponse<FacultyDto>> {
    return updateFaculty(this.http, this.rootUrl, params, context);
  }

  /**
   * Update faculty.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateFaculty$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateFaculty(params: UpdateFaculty$Params, context?: HttpContext): Observable<FacultyDto> {
    return this.updateFaculty$Response(params, context).pipe(
      map((r: StrictHttpResponse<FacultyDto>): FacultyDto => r.body)
    );
  }

  /** Path part for operation `findByCriteria10()` */
  static readonly FindByCriteria10Path = '/faculties/by-criteria/';

  /**
   * Find faculty by criteria.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findByCriteria10()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  findByCriteria10$Response(params: FindByCriteria10$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<FacultyDto>>> {
    return findByCriteria10(this.http, this.rootUrl, params, context);
  }

  /**
   * Find faculty by criteria.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findByCriteria10$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  findByCriteria10(params: FindByCriteria10$Params, context?: HttpContext): Observable<Array<FacultyDto>> {
    return this.findByCriteria10$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<FacultyDto>>): Array<FacultyDto> => r.body)
    );
  }

  /** Path part for operation `findFacultyById()` */
  static readonly FindFacultyByIdPath = '/faculties/{id}';

  /**
   * Find faculty by id.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findFacultyById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findFacultyById$Response(params: FindFacultyById$Params, context?: HttpContext): Observable<StrictHttpResponse<FacultyDto>> {
    return findFacultyById(this.http, this.rootUrl, params, context);
  }

  /**
   * Find faculty by id.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findFacultyById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findFacultyById(params: FindFacultyById$Params, context?: HttpContext): Observable<FacultyDto> {
    return this.findFacultyById$Response(params, context).pipe(
      map((r: StrictHttpResponse<FacultyDto>): FacultyDto => r.body)
    );
  }

}
