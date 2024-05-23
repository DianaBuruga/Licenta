/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ExperienceDto } from '../../models/experience-dto';

export interface FindExperienceById$Params {

/**
 * Id of the experience that will be received
 */
  id: string;
}

export function findExperienceById(http: HttpClient, rootUrl: string, params: FindExperienceById$Params, context?: HttpContext): Observable<StrictHttpResponse<ExperienceDto>> {
  const rb = new RequestBuilder(rootUrl, findExperienceById.PATH, 'get');
  if (params) {
    rb.path('id', params.id, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ExperienceDto>;
    })
  );
}

findExperienceById.PATH = '/experiences/{id}';
