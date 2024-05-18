/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { JobHistoryDto } from '../../models/job-history-dto';

export interface FindAllJobHistories$Params {
}

export function findAllJobHistories(http: HttpClient, rootUrl: string, params?: FindAllJobHistories$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<JobHistoryDto>>> {
  const rb = new RequestBuilder(rootUrl, findAllJobHistories.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<JobHistoryDto>>;
    })
  );
}

findAllJobHistories.PATH = '/jobs/history';
