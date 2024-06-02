/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PostedJobDto } from '../../models/posted-job-dto';

export interface FindPostedJobById$Params {

/**
 * Id of the posted job that will be received
 */
  id: string;
}

export function findPostedJobById(http: HttpClient, rootUrl: string, params: FindPostedJobById$Params, context?: HttpContext): Observable<StrictHttpResponse<PostedJobDto>> {
  const rb = new RequestBuilder(rootUrl, findPostedJobById.PATH, 'get');
  if (params) {
    rb.path('id', params.id, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PostedJobDto>;
    })
  );
}

findPostedJobById.PATH = '/company/jobs/{id}';
