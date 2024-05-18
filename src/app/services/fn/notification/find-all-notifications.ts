/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { NotificationDto } from '../../models/notification-dto';

export interface FindAllNotifications$Params {
}

export function findAllNotifications(http: HttpClient, rootUrl: string, params?: FindAllNotifications$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<NotificationDto>>> {
  const rb = new RequestBuilder(rootUrl, findAllNotifications.PATH, 'get');
  if (params) {
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

findAllNotifications.PATH = '/notifications';
