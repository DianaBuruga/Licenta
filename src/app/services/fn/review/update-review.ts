/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ReviewDto } from '../../models/review-dto';

export interface UpdateReview$Params {
      body: ReviewDto
}

export function updateReview(http: HttpClient, rootUrl: string, params: UpdateReview$Params, context?: HttpContext): Observable<StrictHttpResponse<ReviewDto>> {
  const rb = new RequestBuilder(rootUrl, updateReview.PATH, 'patch');
  if (params) {
    rb.body(params.body, 'application/json');
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

updateReview.PATH = '/reviews';
