/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { SearchCriteria } from '../../models/search-criteria';
import { UserSkillsDto } from '../../models/user-skills-dto';

export interface FindByCriteria2$Params {
      body: Array<SearchCriteria>
}

export function findByCriteria2(http: HttpClient, rootUrl: string, params: FindByCriteria2$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<UserSkillsDto>>> {
  const rb = new RequestBuilder(rootUrl, findByCriteria2.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<UserSkillsDto>>;
    })
  );
}

findByCriteria2.PATH = '/userSkills/by-criteria/';
