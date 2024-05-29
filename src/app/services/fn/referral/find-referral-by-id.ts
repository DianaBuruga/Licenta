/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ReferralDto } from '../../models/referral-dto';

export interface FindReferralById$Params {

/**
 * Id of the referral that will be received
 */
  id: string;
}

export function findReferralById(http: HttpClient, rootUrl: string, params: FindReferralById$Params, context?: HttpContext): Observable<StrictHttpResponse<ReferralDto>> {
  const rb = new RequestBuilder(rootUrl, findReferralById.PATH, 'get');
  if (params) {
    rb.path('id', params.id, {});
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

findReferralById.PATH = '/referrals/{id}';
