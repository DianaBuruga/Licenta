/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { deletePostedJob } from '../fn/posted-job/delete-posted-job';
import { DeletePostedJob$Params } from '../fn/posted-job/delete-posted-job';
import { findAllPostedJobs } from '../fn/posted-job/find-all-posted-jobs';
import { FindAllPostedJobs$Params } from '../fn/posted-job/find-all-posted-jobs';
import { findPostedJobById } from '../fn/posted-job/find-posted-job-by-id';
import { FindPostedJobById$Params } from '../fn/posted-job/find-posted-job-by-id';
import { PostedJobDto } from '../models/posted-job-dto';
import { savePostedJob } from '../fn/posted-job/save-posted-job';
import { SavePostedJob$Params } from '../fn/posted-job/save-posted-job';
import { updatePostedJob } from '../fn/posted-job/update-posted-job';
import { UpdatePostedJob$Params } from '../fn/posted-job/update-posted-job';


/**
 * The PostedJob API
 */
@Injectable({ providedIn: 'root' })
export class PostedJobService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllPostedJobs()` */
  static readonly FindAllPostedJobsPath = '/company/jobs';

  /**
   * Find all posted jobs.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllPostedJobs()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllPostedJobs$Response(params?: FindAllPostedJobs$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<PostedJobDto>>> {
    return findAllPostedJobs(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all posted jobs.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllPostedJobs$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllPostedJobs(params?: FindAllPostedJobs$Params, context?: HttpContext): Observable<Array<PostedJobDto>> {
    return this.findAllPostedJobs$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<PostedJobDto>>): Array<PostedJobDto> => r.body)
    );
  }

  /** Path part for operation `savePostedJob()` */
  static readonly SavePostedJobPath = '/company/jobs';

  /**
   * Save posted job.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `savePostedJob()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  savePostedJob$Response(params: SavePostedJob$Params, context?: HttpContext): Observable<StrictHttpResponse<PostedJobDto>> {
    return savePostedJob(this.http, this.rootUrl, params, context);
  }

  /**
   * Save posted job.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `savePostedJob$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  savePostedJob(params: SavePostedJob$Params, context?: HttpContext): Observable<PostedJobDto> {
    return this.savePostedJob$Response(params, context).pipe(
      map((r: StrictHttpResponse<PostedJobDto>): PostedJobDto => r.body)
    );
  }

  /** Path part for operation `updatePostedJob()` */
  static readonly UpdatePostedJobPath = '/company/jobs';

  /**
   * Update posted job.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updatePostedJob()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updatePostedJob$Response(params: UpdatePostedJob$Params, context?: HttpContext): Observable<StrictHttpResponse<PostedJobDto>> {
    return updatePostedJob(this.http, this.rootUrl, params, context);
  }

  /**
   * Update posted job.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updatePostedJob$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updatePostedJob(params: UpdatePostedJob$Params, context?: HttpContext): Observable<PostedJobDto> {
    return this.updatePostedJob$Response(params, context).pipe(
      map((r: StrictHttpResponse<PostedJobDto>): PostedJobDto => r.body)
    );
  }

  /** Path part for operation `findPostedJobById()` */
  static readonly FindPostedJobByIdPath = '/company/jobs/{id}';

  /**
   * Find posted job by id.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findPostedJobById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findPostedJobById$Response(params: FindPostedJobById$Params, context?: HttpContext): Observable<StrictHttpResponse<PostedJobDto>> {
    return findPostedJobById(this.http, this.rootUrl, params, context);
  }

  /**
   * Find posted job by id.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findPostedJobById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findPostedJobById(params: FindPostedJobById$Params, context?: HttpContext): Observable<PostedJobDto> {
    return this.findPostedJobById$Response(params, context).pipe(
      map((r: StrictHttpResponse<PostedJobDto>): PostedJobDto => r.body)
    );
  }

  /** Path part for operation `deletePostedJob()` */
  static readonly DeletePostedJobPath = '/company/jobs/{id}';

  /**
   * Delete posted job.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deletePostedJob()` instead.
   *
   * This method doesn't expect any request body.
   */
  deletePostedJob$Response(params: DeletePostedJob$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deletePostedJob(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete posted job.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deletePostedJob$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deletePostedJob(params: DeletePostedJob$Params, context?: HttpContext): Observable<void> {
    return this.deletePostedJob$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
