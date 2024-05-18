/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { JobCandidatesDto } from '../../models/job-candidates-dto';

export interface SaveJobCandidates$Params {
      body: JobCandidatesDto
}

export function saveJobCandidates(http: HttpClient, rootUrl: string, params: SaveJobCandidates$Params, context?: HttpContext): Observable<StrictHttpResponse<JobCandidatesDto>> {
  const rb = new RequestBuilder(rootUrl, saveJobCandidates.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<JobCandidatesDto>;
    })
  );
}

saveJobCandidates.PATH = '/jobCandidates';
