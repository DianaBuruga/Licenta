/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ReviewDto } from '../../models/review-dto';

export interface FindReviewById$Params {

/**
 * Id of the review that will be received
 */
  id: string;
}

export function findReviewById(http: HttpClient, rootUrl: string, params: FindReviewById$Params, context?: HttpContext): Observable<StrictHttpResponse<ReviewDto>> {
  const rb = new RequestBuilder(rootUrl, findReviewById.PATH, 'get');
  if (params) {
    rb.path('id', params.id, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ReviewDto>;
    })
  );
}

findReviewById.PATH = '/reviews/{id}';
