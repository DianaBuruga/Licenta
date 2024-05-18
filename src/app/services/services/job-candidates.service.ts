/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { deleteJobCandidates } from '../fn/job-candidates/delete-job-candidates';
import { DeleteJobCandidates$Params } from '../fn/job-candidates/delete-job-candidates';
import { findAllJobCandidates } from '../fn/job-candidates/find-all-job-candidates';
import { FindAllJobCandidates$Params } from '../fn/job-candidates/find-all-job-candidates';
import { findByCriteria7 } from '../fn/job-candidates/find-by-criteria-7';
import { FindByCriteria7$Params } from '../fn/job-candidates/find-by-criteria-7';
import { findJobById } from '../fn/job-candidates/find-job-by-id';
import { FindJobById$Params } from '../fn/job-candidates/find-job-by-id';
import { JobCandidatesDto } from '../models/job-candidates-dto';
import { saveJobCandidates } from '../fn/job-candidates/save-job-candidates';
import { SaveJobCandidates$Params } from '../fn/job-candidates/save-job-candidates';
import { updateJobCandidates } from '../fn/job-candidates/update-job-candidates';
import { UpdateJobCandidates$Params } from '../fn/job-candidates/update-job-candidates';


/**
 * The JobCandidates API
 */
@Injectable({ providedIn: 'root' })
export class JobCandidatesService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllJobCandidates()` */
  static readonly FindAllJobCandidatesPath = '/jobCandidates';

  /**
   * Find all job candidates.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllJobCandidates()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllJobCandidates$Response(params?: FindAllJobCandidates$Params, context?: HttpContext): Observable<StrictHttpResponse<JobCandidatesDto>> {
    return findAllJobCandidates(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all job candidates.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllJobCandidates$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllJobCandidates(params?: FindAllJobCandidates$Params, context?: HttpContext): Observable<JobCandidatesDto> {
    return this.findAllJobCandidates$Response(params, context).pipe(
      map((r: StrictHttpResponse<JobCandidatesDto>): JobCandidatesDto => r.body)
    );
  }

  /** Path part for operation `saveJobCandidates()` */
  static readonly SaveJobCandidatesPath = '/jobCandidates';

  /**
   * Save job candidate.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveJobCandidates()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveJobCandidates$Response(params: SaveJobCandidates$Params, context?: HttpContext): Observable<StrictHttpResponse<JobCandidatesDto>> {
    return saveJobCandidates(this.http, this.rootUrl, params, context);
  }

  /**
   * Save job candidate.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveJobCandidates$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveJobCandidates(params: SaveJobCandidates$Params, context?: HttpContext): Observable<JobCandidatesDto> {
    return this.saveJobCandidates$Response(params, context).pipe(
      map((r: StrictHttpResponse<JobCandidatesDto>): JobCandidatesDto => r.body)
    );
  }

  /** Path part for operation `deleteJobCandidates()` */
  static readonly DeleteJobCandidatesPath = '/jobCandidates';

  /**
   * Delete job candidate.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteJobCandidates()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteJobCandidates$Response(params: DeleteJobCandidates$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteJobCandidates(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete job candidate.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteJobCandidates$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteJobCandidates(params: DeleteJobCandidates$Params, context?: HttpContext): Observable<void> {
    return this.deleteJobCandidates$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `updateJobCandidates()` */
  static readonly UpdateJobCandidatesPath = '/jobCandidates';

  /**
   * Update job candidate.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateJobCandidates()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateJobCandidates$Response(params: UpdateJobCandidates$Params, context?: HttpContext): Observable<StrictHttpResponse<JobCandidatesDto>> {
    return updateJobCandidates(this.http, this.rootUrl, params, context);
  }

  /**
   * Update job candidate.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateJobCandidates$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateJobCandidates(params: UpdateJobCandidates$Params, context?: HttpContext): Observable<JobCandidatesDto> {
    return this.updateJobCandidates$Response(params, context).pipe(
      map((r: StrictHttpResponse<JobCandidatesDto>): JobCandidatesDto => r.body)
    );
  }

  /** Path part for operation `findJobById()` */
  static readonly FindJobByIdPath = '/jobCandidates/{id}/{jobId}';

  /**
   * Find job candidate by id.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findJobById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findJobById$Response(params: FindJobById$Params, context?: HttpContext): Observable<StrictHttpResponse<JobCandidatesDto>> {
    return findJobById(this.http, this.rootUrl, params, context);
  }

  /**
   * Find job candidate by id.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findJobById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findJobById(params: FindJobById$Params, context?: HttpContext): Observable<JobCandidatesDto> {
    return this.findJobById$Response(params, context).pipe(
      map((r: StrictHttpResponse<JobCandidatesDto>): JobCandidatesDto => r.body)
    );
  }

  /** Path part for operation `findByCriteria7()` */
  static readonly FindByCriteria7Path = '/jobCandidates/by-criteria/';

  /**
   * Find job candidate by criteria.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findByCriteria7()` instead.
   *
   * This method doesn't expect any request body.
   */
  findByCriteria7$Response(params: FindByCriteria7$Params, context?: HttpContext): Observable<StrictHttpResponse<JobCandidatesDto>> {
    return findByCriteria7(this.http, this.rootUrl, params, context);
  }

  /**
   * Find job candidate by criteria.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findByCriteria7$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findByCriteria7(params: FindByCriteria7$Params, context?: HttpContext): Observable<JobCandidatesDto> {
    return this.findByCriteria7$Response(params, context).pipe(
      map((r: StrictHttpResponse<JobCandidatesDto>): JobCandidatesDto => r.body)
    );
  }

}
