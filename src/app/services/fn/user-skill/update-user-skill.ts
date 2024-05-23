/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { UserSkillsDto } from '../../models/user-skills-dto';

export interface UpdateUserSkill$Params {
      body: UserSkillsDto
}

export function updateUserSkill(http: HttpClient, rootUrl: string, params: UpdateUserSkill$Params, context?: HttpContext): Observable<StrictHttpResponse<UserSkillsDto>> {
  const rb = new RequestBuilder(rootUrl, updateUserSkill.PATH, 'patch');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<UserSkillsDto>;
    })
  );
}

updateUserSkill.PATH = '/userSkills/';
