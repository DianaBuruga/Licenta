/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { deleteReview } from '../fn/review/delete-review';
import { DeleteReview$Params } from '../fn/review/delete-review';
import { findAllReviews } from '../fn/review/find-all-reviews';
import { FindAllReviews$Params } from '../fn/review/find-all-reviews';
import { findByCriteria4 } from '../fn/review/find-by-criteria-4';
import { FindByCriteria4$Params } from '../fn/review/find-by-criteria-4';
import { findReviewById } from '../fn/review/find-review-by-id';
import { FindReviewById$Params } from '../fn/review/find-review-by-id';
import { ReviewDto } from '../models/review-dto';
import { saveReview } from '../fn/review/save-review';
import { SaveReview$Params } from '../fn/review/save-review';
import { updateReview } from '../fn/review/update-review';
import { UpdateReview$Params } from '../fn/review/update-review';


/**
 * The Review API
 */
@Injectable({ providedIn: 'root' })
export class ReviewService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllReviews()` */
  static readonly FindAllReviewsPath = '/reviews';

  /**
   * Find all reviews.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllReviews()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllReviews$Response(params?: FindAllReviews$Params, context?: HttpContext): Observable<StrictHttpResponse<ReviewDto>> {
    return findAllReviews(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all reviews.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllReviews$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllReviews(params?: FindAllReviews$Params, context?: HttpContext): Observable<ReviewDto> {
    return this.findAllReviews$Response(params, context).pipe(
      map((r: StrictHttpResponse<ReviewDto>): ReviewDto => r.body)
    );
  }

  /** Path part for operation `saveReview()` */
  static readonly SaveReviewPath = '/reviews';

  /**
   * Save review.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveReview()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveReview$Response(params: SaveReview$Params, context?: HttpContext): Observable<StrictHttpResponse<ReviewDto>> {
    return saveReview(this.http, this.rootUrl, params, context);
  }

  /**
   * Save review.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveReview$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveReview(params: SaveReview$Params, context?: HttpContext): Observable<ReviewDto> {
    return this.saveReview$Response(params, context).pipe(
      map((r: StrictHttpResponse<ReviewDto>): ReviewDto => r.body)
    );
  }

  /** Path part for operation `deleteReview()` */
  static readonly DeleteReviewPath = '/reviews';

  /**
   * Delete review.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteReview()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteReview$Response(params: DeleteReview$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteReview(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete review.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteReview$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteReview(params: DeleteReview$Params, context?: HttpContext): Observable<void> {
    return this.deleteReview$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `updateReview()` */
  static readonly UpdateReviewPath = '/reviews';

  /**
   * Update review.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateReview()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateReview$Response(params: UpdateReview$Params, context?: HttpContext): Observable<StrictHttpResponse<ReviewDto>> {
    return updateReview(this.http, this.rootUrl, params, context);
  }

  /**
   * Update review.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateReview$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateReview(params: UpdateReview$Params, context?: HttpContext): Observable<ReviewDto> {
    return this.updateReview$Response(params, context).pipe(
      map((r: StrictHttpResponse<ReviewDto>): ReviewDto => r.body)
    );
  }

  /** Path part for operation `findReviewById()` */
  static readonly FindReviewByIdPath = '/reviews/{id}';

  /**
   * Find review by id.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findReviewById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findReviewById$Response(params: FindReviewById$Params, context?: HttpContext): Observable<StrictHttpResponse<ReviewDto>> {
    return findReviewById(this.http, this.rootUrl, params, context);
  }

  /**
   * Find review by id.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findReviewById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findReviewById(params: FindReviewById$Params, context?: HttpContext): Observable<ReviewDto> {
    return this.findReviewById$Response(params, context).pipe(
      map((r: StrictHttpResponse<ReviewDto>): ReviewDto => r.body)
    );
  }

  /** Path part for operation `findByCriteria4()` */
  static readonly FindByCriteria4Path = '/reviews/by-criteria/';

  /**
   * Find review by criteria.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findByCriteria4()` instead.
   *
   * This method doesn't expect any request body.
   */
  findByCriteria4$Response(params: FindByCriteria4$Params, context?: HttpContext): Observable<StrictHttpResponse<ReviewDto>> {
    return findByCriteria4(this.http, this.rootUrl, params, context);
  }

  /**
   * Find review by criteria.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findByCriteria4$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findByCriteria4(params: FindByCriteria4$Params, context?: HttpContext): Observable<ReviewDto> {
    return this.findByCriteria4$Response(params, context).pipe(
      map((r: StrictHttpResponse<ReviewDto>): ReviewDto => r.body)
    );
  }

}
