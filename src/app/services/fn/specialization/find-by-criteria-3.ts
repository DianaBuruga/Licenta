/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { SearchCriteria } from '../../models/search-criteria';
import { SpecializationDto } from '../../models/specialization-dto';

export interface FindByCriteria3$Params {
      body: Array<SearchCriteria>
}

export function findByCriteria3(http: HttpClient, rootUrl: string, params: FindByCriteria3$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<SpecializationDto>>> {
  const rb = new RequestBuilder(rootUrl, findByCriteria3.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<SpecializationDto>>;
    })
  );
}

findByCriteria3.PATH = '/specializations/by-criteria/';
