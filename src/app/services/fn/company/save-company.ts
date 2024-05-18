/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { CompanyDto } from '../../models/company-dto';

export interface SaveCompany$Params {
      body: CompanyDto
}

export function saveCompany(http: HttpClient, rootUrl: string, params: SaveCompany$Params, context?: HttpContext): Observable<StrictHttpResponse<CompanyDto>> {
  const rb = new RequestBuilder(rootUrl, saveCompany.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
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

saveCompany.PATH = '/companies';
