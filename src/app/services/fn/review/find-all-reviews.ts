/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ReviewDto } from '../../models/review-dto';

export interface FindAllReviews$Params {
}

export function findAllReviews(http: HttpClient, rootUrl: string, params?: FindAllReviews$Params, context?: HttpContext): Observable<StrictHttpResponse<ReviewDto>> {
  const rb = new RequestBuilder(rootUrl, findAllReviews.PATH, 'get');
  if (params) {
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

findAllReviews.PATH = '/reviews';
