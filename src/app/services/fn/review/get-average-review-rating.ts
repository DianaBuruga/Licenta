/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { AverageRating } from '../../models/average-rating';

export interface GetAverageReviewRating$Params {

/**
 * Id of the company
 */
  companyId: string;
}

export function getAverageReviewRating(http: HttpClient, rootUrl: string, params: GetAverageReviewRating$Params, context?: HttpContext): Observable<StrictHttpResponse<AverageRating>> {
  const rb = new RequestBuilder(rootUrl, getAverageReviewRating.PATH, 'get');
  if (params) {
    rb.path('companyId', params.companyId, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<AverageRating>;
    })
  );
}

getAverageReviewRating.PATH = '/reviews/average-rating/{companyId}';
