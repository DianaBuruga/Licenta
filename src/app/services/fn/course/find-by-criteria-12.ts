/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { CourseDto } from '../../models/course-dto';
import { SearchCriteria } from '../../models/search-criteria';

export interface FindByCriteria12$Params {
      body: Array<SearchCriteria>
}

export function findByCriteria12(http: HttpClient, rootUrl: string, params: FindByCriteria12$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<CourseDto>>> {
  const rb = new RequestBuilder(rootUrl, findByCriteria12.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
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

findByCriteria12.PATH = '/courses/by-criteria/';
