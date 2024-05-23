/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { SkillDto } from '../../models/skill-dto';

export interface FindAllSkills$Params {
}

export function findAllSkills(http: HttpClient, rootUrl: string, params?: FindAllSkills$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<SkillDto>>> {
  const rb = new RequestBuilder(rootUrl, findAllSkills.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<SkillDto>>;
    })
  );
}

findAllSkills.PATH = '/skills';
