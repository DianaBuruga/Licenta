/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { SearchCriteria } from '../../models/search-criteria';
import { UserDto } from '../../models/user-dto';

export interface FindByCriteria1$Params {

/**
 * List of search criteria
 */
  criteria: Array<SearchCriteria>;
}

export function findByCriteria1(http: HttpClient, rootUrl: string, params: FindByCriteria1$Params, context?: HttpContext): Observable<StrictHttpResponse<UserDto>> {
  const rb = new RequestBuilder(rootUrl, findByCriteria1.PATH, 'post');
  if (params) {
    rb.query('criteria', params.criteria, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<UserDto>;
    })
  );
}

findByCriteria1.PATH = '/users/by-criteria/';
