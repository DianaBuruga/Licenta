/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { IsOwner } from '../../models/is-owner';

export interface IsOwner$Params {
  endpoint: string;
  id: string;
}

export function isOwner(http: HttpClient, rootUrl: string, params: IsOwner$Params, context?: HttpContext): Observable<StrictHttpResponse<IsOwner>> {
  const rb = new RequestBuilder(rootUrl, isOwner.PATH, 'get');
  if (params) {
    rb.path('endpoint', params.endpoint, {});
    rb.path('id', params.id, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      console.log(r);
      return r as StrictHttpResponse<IsOwner>;
    })
  );
}

isOwner.PATH = '/isOwner/{endpoint}/{id}';
