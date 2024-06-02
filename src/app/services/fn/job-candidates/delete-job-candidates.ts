/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface DeleteJobCandidates$Params {

/**
 * Job candidate that will be deleted
 */
  candidateId: string;
  jobId: string;
}

export function deleteJobCandidates(http: HttpClient, rootUrl: string, params: DeleteJobCandidates$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, deleteJobCandidates.PATH, 'delete');
  if (params) {
    rb.path('candidateId', params.candidateId, {});
    rb.query('jobId', params.jobId, {});
  }

  return http.request(
    rb.build({ responseType: 'text', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return (r as HttpResponse<any>).clone({ body: undefined }) as StrictHttpResponse<void>;
    })
  );
}

deleteJobCandidates.PATH = '/jobCandidates/job/{jobId}/candidate/{candidateId}/';
