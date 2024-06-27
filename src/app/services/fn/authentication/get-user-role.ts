/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Role } from '../../models/role';

export interface GetUserRole$Params {
}

export function getUserRole(http: HttpClient, rootUrl: string, params?: GetUserRole$Params, context?: HttpContext): Observable<StrictHttpResponse<Role>> {
  const rb = new RequestBuilder(rootUrl, getUserRole.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Role>;
    })
  );
}

getUserRole.PATH = '/auth/role';
