/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { CourseDto } from '../../models/course-dto';

export interface FindAllCourses$Params {
}

export function findAllCourses(http: HttpClient, rootUrl: string, params?: FindAllCourses$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<CourseDto>>> {
  const rb = new RequestBuilder(rootUrl, findAllCourses.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<CourseDto>>;
    })
  );
}

findAllCourses.PATH = '/courses';
