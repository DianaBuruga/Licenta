/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { CompanyDto } from '../../models/company-dto';
import { SearchCriteria } from '../../models/search-criteria';

export interface FindByCriteria11$Params {

/**
 * List of search criteria
 */
  criteria: Array<SearchCriteria>;
}

export function findByCriteria11(http: HttpClient, rootUrl: string, params: FindByCriteria11$Params, context?: HttpContext): Observable<StrictHttpResponse<CompanyDto>> {
  const rb = new RequestBuilder(rootUrl, findByCriteria11.PATH, 'get');
  if (params) {
    rb.query('criteria', params.criteria, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<CompanyDto>;
    })
  );
}

findByCriteria11.PATH = '/companies/by-criteria/';
