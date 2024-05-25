/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { CourseDto } from '../models/course-dto';
import { deleteCourse } from '../fn/course/delete-course';
import { DeleteCourse$Params } from '../fn/course/delete-course';
import { findAllCourses } from '../fn/course/find-all-courses';
import { FindAllCourses$Params } from '../fn/course/find-all-courses';
import { findByCriteria12 } from '../fn/course/find-by-criteria-12';
import { FindByCriteria12$Params } from '../fn/course/find-by-criteria-12';
import { findCourseById } from '../fn/course/find-course-by-id';
import { FindCourseById$Params } from '../fn/course/find-course-by-id';
import { saveCourse } from '../fn/course/save-course';
import { SaveCourse$Params } from '../fn/course/save-course';
import { updateCourse } from '../fn/course/update-course';
import { UpdateCourse$Params } from '../fn/course/update-course';


/**
 * The Course API
 */
@Injectable({ providedIn: 'root' })
export class CourseService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllCourses()` */
  static readonly FindAllCoursesPath = '/courses';

  /**
   * Find all courses.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllCourses()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllCourses$Response(params?: FindAllCourses$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<CourseDto>>> {
    return findAllCourses(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all courses.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllCourses$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllCourses(params?: FindAllCourses$Params, context?: HttpContext): Observable<Array<CourseDto>> {
    return this.findAllCourses$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<CourseDto>>): Array<CourseDto> => r.body)
    );
  }

  /** Path part for operation `saveCourse()` */
  static readonly SaveCoursePath = '/courses';

  /**
   * Save course.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveCourse()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveCourse$Response(params: SaveCourse$Params, context?: HttpContext): Observable<StrictHttpResponse<CourseDto>> {
    return saveCourse(this.http, this.rootUrl, params, context);
  }

  /**
   * Save course.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveCourse$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveCourse(params: SaveCourse$Params, context?: HttpContext): Observable<CourseDto> {
    return this.saveCourse$Response(params, context).pipe(
      map((r: StrictHttpResponse<CourseDto>): CourseDto => r.body)
    );
  }

  /** Path part for operation `updateCourse()` */
  static readonly UpdateCoursePath = '/courses';

  /**
   * Update course.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateCourse()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateCourse$Response(params: UpdateCourse$Params, context?: HttpContext): Observable<StrictHttpResponse<CourseDto>> {
    return updateCourse(this.http, this.rootUrl, params, context);
  }

  /**
   * Update course.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateCourse$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateCourse(params: UpdateCourse$Params, context?: HttpContext): Observable<CourseDto> {
    return this.updateCourse$Response(params, context).pipe(
      map((r: StrictHttpResponse<CourseDto>): CourseDto => r.body)
    );
  }

  /** Path part for operation `findCourseById()` */
  static readonly FindCourseByIdPath = '/courses/{id}';

  /**
   * Find course by id.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findCourseById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findCourseById$Response(params: FindCourseById$Params, context?: HttpContext): Observable<StrictHttpResponse<CourseDto>> {
    return findCourseById(this.http, this.rootUrl, params, context);
  }

  /**
   * Find course by id.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findCourseById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findCourseById(params: FindCourseById$Params, context?: HttpContext): Observable<CourseDto> {
    return this.findCourseById$Response(params, context).pipe(
      map((r: StrictHttpResponse<CourseDto>): CourseDto => r.body)
    );
  }

  /** Path part for operation `deleteCourse()` */
  static readonly DeleteCoursePath = '/courses/{id}';

  /**
   * Delete course.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteCourse()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteCourse$Response(params: DeleteCourse$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteCourse(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete course.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteCourse$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteCourse(params: DeleteCourse$Params, context?: HttpContext): Observable<void> {
    return this.deleteCourse$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `findByCriteria12()` */
  static readonly FindByCriteria12Path = '/courses/by-criteria/';

  /**
   * Find course by criteria.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findByCriteria12()` instead.
   *
   * This method doesn't expect any request body.
   */
  findByCriteria12$Response(params: FindByCriteria12$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<CourseDto>>> {
    return findByCriteria12(this.http, this.rootUrl, params, context);
  }

  /**
   * Find course by criteria.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findByCriteria12$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findByCriteria12(params: FindByCriteria12$Params, context?: HttpContext): Observable<Array<CourseDto>> {
    return this.findByCriteria12$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<CourseDto>>): Array<CourseDto> => r.body)
    );
  }

}
