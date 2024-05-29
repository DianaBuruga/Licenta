/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ReferralDto } from '../../models/referral-dto';

export interface UpdateReferral$Params {
      body: ReferralDto
}

export function updateReferral(http: HttpClient, rootUrl: string, params: UpdateReferral$Params, context?: HttpContext): Observable<StrictHttpResponse<ReferralDto>> {
  const rb = new RequestBuilder(rootUrl, updateReferral.PATH, 'patch');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ReferralDto>;
    })
  );
}

updateReferral.PATH = '/referrals';
