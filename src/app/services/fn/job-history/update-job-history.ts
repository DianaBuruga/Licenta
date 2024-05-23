/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { JobHistoryDto } from '../../models/job-history-dto';

export interface UpdateJobHistory$Params {
      body: JobHistoryDto
}

export function updateJobHistory(http: HttpClient, rootUrl: string, params: UpdateJobHistory$Params, context?: HttpContext): Observable<StrictHttpResponse<JobHistoryDto>> {
  const rb = new RequestBuilder(rootUrl, updateJobHistory.PATH, 'patch');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<JobHistoryDto>;
    })
  );
}

updateJobHistory.PATH = '/jobs/history';
