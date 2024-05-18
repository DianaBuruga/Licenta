/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { JobCandidatesDto } from '../../models/job-candidates-dto';

export interface FindJobById$Params {

/**
 * Id of the user that candidates for job
 */
  jobId: string;

/**
 * Id of the job for that user candidates
 */
  id: string;
}

export function findJobById(http: HttpClient, rootUrl: string, params: FindJobById$Params, context?: HttpContext): Observable<StrictHttpResponse<JobCandidatesDto>> {
  const rb = new RequestBuilder(rootUrl, findJobById.PATH, 'get');
  if (params) {
    rb.path('jobId', params.jobId, {});
    rb.path('id', params.id, {});
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

findJobById.PATH = '/jobCandidates/{id}/{jobId}';
