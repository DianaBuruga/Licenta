/* tslint:disable */
/* eslint-disable */
import {HttpClient, HttpContext, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {StrictHttpResponse} from '../../strict-http-response';
import {RequestBuilder} from '../../request-builder';

import {ApplicationEmailRequest} from '../../models/application-email-request';

export interface SendHtmlEmail$Params {
  body: ApplicationEmailRequest
}

export function sendHtmlEmail(http: HttpClient, rootUrl: string, params: SendHtmlEmail$Params, context?: HttpContext): Observable<StrictHttpResponse<ApplicationEmailRequest>> {
  const rb = new RequestBuilder(rootUrl, sendHtmlEmail.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({responseType: 'json', accept: 'application/json', context})
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ApplicationEmailRequest>;
    })
  );
}

sendHtmlEmail.PATH = '/email/sendHTMLEmail';
