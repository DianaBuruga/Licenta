/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { UrlDto } from '../../models/url-dto';

export interface Auth$Params {
}

export function auth(http: HttpClient, rootUrl: string, params?: Auth$Params, context?: HttpContext): Observable<StrictHttpResponse<UrlDto>> {
  const rb = new RequestBuilder(rootUrl, auth.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<UrlDto>;
    })
  );
}

auth.PATH = '/auth/url';
