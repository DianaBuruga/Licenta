/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { NotificationDto } from '../../models/notification-dto';

export interface UpdateNotification$Params {
      body: NotificationDto
}

export function updateNotification(http: HttpClient, rootUrl: string, params: UpdateNotification$Params, context?: HttpContext): Observable<StrictHttpResponse<NotificationDto>> {
  const rb = new RequestBuilder(rootUrl, updateNotification.PATH, 'patch');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<NotificationDto>;
    })
  );
}

updateNotification.PATH = '/notifications';
