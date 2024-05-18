/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { EmailRequest } from '../../models/email-request';

export interface SendEmail$Params {
      body: EmailRequest
}

export function sendEmail(http: HttpClient, rootUrl: string, params: SendEmail$Params, context?: HttpContext): Observable<StrictHttpResponse<EmailRequest>> {
  const rb = new RequestBuilder(rootUrl, sendEmail.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<EmailRequest>;
    })
  );
}

sendEmail.PATH = '/email';
