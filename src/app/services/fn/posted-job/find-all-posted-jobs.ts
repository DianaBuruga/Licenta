/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PostedJobDto } from '../../models/posted-job-dto';

export interface FindAllPostedJobs$Params {
}

export function findAllPostedJobs(http: HttpClient, rootUrl: string, params?: FindAllPostedJobs$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<PostedJobDto>>> {
  const rb = new RequestBuilder(rootUrl, findAllPostedJobs.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<PostedJobDto>>;
    })
  );
}

findAllPostedJobs.PATH = '/company/jobs';
