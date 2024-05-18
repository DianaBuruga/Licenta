/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { CourseDto } from '../../models/course-dto';
import { SearchCriteria } from '../../models/search-criteria';

export interface FindByCriteria10$Params {

/**
 * List of search criteria
 */
  criteria: Array<SearchCriteria>;
}

export function findByCriteria10(http: HttpClient, rootUrl: string, params: FindByCriteria10$Params, context?: HttpContext): Observable<StrictHttpResponse<CourseDto>> {
  const rb = new RequestBuilder(rootUrl, findByCriteria10.PATH, 'get');
  if (params) {
    rb.query('criteria', params.criteria, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<CourseDto>;
    })
  );
}

findByCriteria10.PATH = '/courses/by-criteria/';
