/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface Search$Params {

/**
 * Endpoint where to search
 */
  endpoint: string;
  criteria: {
[key: string]: string;
};
}

export function search(http: HttpClient, rootUrl: string, params: Search$Params, context?: HttpContext): Observable<StrictHttpResponse<any>> {
  const rb = new RequestBuilder(rootUrl, search.PATH, 'get');
  if (params) {
    rb.path('endpoint', params.endpoint, {});
    rb.query('criteria', params.criteria, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<any>;
    })
  );
}

search.PATH = '/search/{endpoint}';
