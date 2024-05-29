/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { CompanyDto } from '../../models/company-dto';
import { SearchCriteria } from '../../models/search-criteria';

export interface FindByCriteria14$Params {
      body: Array<SearchCriteria>
}

export function findByCriteria14(http: HttpClient, rootUrl: string, params: FindByCriteria14$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<CompanyDto>>> {
  const rb = new RequestBuilder(rootUrl, findByCriteria14.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<CompanyDto>>;
    })
  );
}

findByCriteria14.PATH = '/companies/by-criteria/';
