/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PostedJobDto } from '../../models/posted-job-dto';

export interface SavePostedJob$Params {
      body: PostedJobDto
}

export function savePostedJob(http: HttpClient, rootUrl: string, params: SavePostedJob$Params, context?: HttpContext): Observable<StrictHttpResponse<PostedJobDto>> {
  const rb = new RequestBuilder(rootUrl, savePostedJob.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
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

savePostedJob.PATH = '/company/jobs';
