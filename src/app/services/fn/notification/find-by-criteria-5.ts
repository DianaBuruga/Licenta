/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { NotificationDto } from '../../models/notification-dto';
import { SearchCriteria } from '../../models/search-criteria';

export interface FindByCriteria5$Params {
  criteria: Array<SearchCriteria>;
}

export function findByCriteria5(http: HttpClient, rootUrl: string, params: FindByCriteria5$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<NotificationDto>>> {
  const rb = new RequestBuilder(rootUrl, findByCriteria5.PATH, 'get');
  if (params) {
    rb.query('criteria', params.criteria, {});
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<NotificationDto>>;
    })
  );
}

findByCriteria5.PATH = '/notifications/by-criteria/';
