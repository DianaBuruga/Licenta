/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { SkillDto } from '../../models/skill-dto';

export interface FindSkillById$Params {

/**
 * Id of the skill that will be received
 */
  id: string;
}

export function findSkillById(http: HttpClient, rootUrl: string, params: FindSkillById$Params, context?: HttpContext): Observable<StrictHttpResponse<SkillDto>> {
  const rb = new RequestBuilder(rootUrl, findSkillById.PATH, 'get');
  if (params) {
    rb.path('id', params.id, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<SkillDto>;
    })
  );
}

findSkillById.PATH = '/skills/{id}';
