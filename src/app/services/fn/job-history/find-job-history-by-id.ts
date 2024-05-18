/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { JobHistoryDto } from '../../models/job-history-dto';

export interface FindJobHistoryById$Params {
  id: string;
}

export function findJobHistoryById(http: HttpClient, rootUrl: string, params: FindJobHistoryById$Params, context?: HttpContext): Observable<StrictHttpResponse<JobHistoryDto>> {
  const rb = new RequestBuilder(rootUrl, findJobHistoryById.PATH, 'get');
  if (params) {
    rb.path('id', params.id, {});
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<JobHistoryDto>;
    })
  );
}

findJobHistoryById.PATH = '/jobs/history/{id}';
