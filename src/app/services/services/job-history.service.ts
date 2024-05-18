/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { deleteJobHistory } from '../fn/job-history/delete-job-history';
import { DeleteJobHistory$Params } from '../fn/job-history/delete-job-history';
import { findAllJobHistories } from '../fn/job-history/find-all-job-histories';
import { FindAllJobHistories$Params } from '../fn/job-history/find-all-job-histories';
import { findByCriteria6 } from '../fn/job-history/find-by-criteria-6';
import { FindByCriteria6$Params } from '../fn/job-history/find-by-criteria-6';
import { findJobHistoryById } from '../fn/job-history/find-job-history-by-id';
import { FindJobHistoryById$Params } from '../fn/job-history/find-job-history-by-id';
import { JobHistoryDto } from '../models/job-history-dto';
import { saveJobHistory } from '../fn/job-history/save-job-history';
import { SaveJobHistory$Params } from '../fn/job-history/save-job-history';
import { updateJobHistory } from '../fn/job-history/update-job-history';
import { UpdateJobHistory$Params } from '../fn/job-history/update-job-history';


/**
 * The JobHistory API
 */
@Injectable({ providedIn: 'root' })
export class JobHistoryService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllJobHistories()` */
  static readonly FindAllJobHistoriesPath = '/jobs/history';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllJobHistories()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllJobHistories$Response(params?: FindAllJobHistories$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<JobHistoryDto>>> {
    return findAllJobHistories(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllJobHistories$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllJobHistories(params?: FindAllJobHistories$Params, context?: HttpContext): Observable<Array<JobHistoryDto>> {
    return this.findAllJobHistories$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<JobHistoryDto>>): Array<JobHistoryDto> => r.body)
    );
  }

  /** Path part for operation `saveJobHistory()` */
  static readonly SaveJobHistoryPath = '/jobs/history';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveJobHistory()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveJobHistory$Response(params: SaveJobHistory$Params, context?: HttpContext): Observable<StrictHttpResponse<JobHistoryDto>> {
    return saveJobHistory(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveJobHistory$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveJobHistory(params: SaveJobHistory$Params, context?: HttpContext): Observable<JobHistoryDto> {
    return this.saveJobHistory$Response(params, context).pipe(
      map((r: StrictHttpResponse<JobHistoryDto>): JobHistoryDto => r.body)
    );
  }

  /** Path part for operation `deleteJobHistory()` */
  static readonly DeleteJobHistoryPath = '/jobs/history';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteJobHistory()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteJobHistory$Response(params: DeleteJobHistory$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteJobHistory(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteJobHistory$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteJobHistory(params: DeleteJobHistory$Params, context?: HttpContext): Observable<void> {
    return this.deleteJobHistory$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `updateJobHistory()` */
  static readonly UpdateJobHistoryPath = '/jobs/history';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateJobHistory()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateJobHistory$Response(params: UpdateJobHistory$Params, context?: HttpContext): Observable<StrictHttpResponse<JobHistoryDto>> {
    return updateJobHistory(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateJobHistory$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateJobHistory(params: UpdateJobHistory$Params, context?: HttpContext): Observable<JobHistoryDto> {
    return this.updateJobHistory$Response(params, context).pipe(
      map((r: StrictHttpResponse<JobHistoryDto>): JobHistoryDto => r.body)
    );
  }

  /** Path part for operation `findJobHistoryById()` */
  static readonly FindJobHistoryByIdPath = '/jobs/history/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findJobHistoryById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findJobHistoryById$Response(params: FindJobHistoryById$Params, context?: HttpContext): Observable<StrictHttpResponse<JobHistoryDto>> {
    return findJobHistoryById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findJobHistoryById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findJobHistoryById(params: FindJobHistoryById$Params, context?: HttpContext): Observable<JobHistoryDto> {
    return this.findJobHistoryById$Response(params, context).pipe(
      map((r: StrictHttpResponse<JobHistoryDto>): JobHistoryDto => r.body)
    );
  }

  /** Path part for operation `findByCriteria6()` */
  static readonly FindByCriteria6Path = '/jobs/history/by-criteria/';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findByCriteria6()` instead.
   *
   * This method doesn't expect any request body.
   */
  findByCriteria6$Response(params: FindByCriteria6$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<JobHistoryDto>>> {
    return findByCriteria6(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findByCriteria6$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findByCriteria6(params: FindByCriteria6$Params, context?: HttpContext): Observable<Array<JobHistoryDto>> {
    return this.findByCriteria6$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<JobHistoryDto>>): Array<JobHistoryDto> => r.body)
    );
  }

}
