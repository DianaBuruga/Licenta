/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { TokenDto } from '../../models/token-dto';

export interface Callback$Params {
  code: string;
}

export function callback(http: HttpClient, rootUrl: string, params: Callback$Params, context?: HttpContext): Observable<StrictHttpResponse<TokenDto>> {
  const rb = new RequestBuilder(rootUrl, callback.PATH, 'get');
  if (params) {
    rb.query('code', params.code, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<TokenDto>;
    })
  );
}

callback.PATH = '/auth/callback';
