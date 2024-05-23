/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { SearchCriteria } from '../../models/search-criteria';
import { SkillDto } from '../../models/skill-dto';

export interface FindByCriteria4$Params {

/**
 * List of search criteria
 */
  criteria: Array<SearchCriteria>;
}

export function findByCriteria4(http: HttpClient, rootUrl: string, params: FindByCriteria4$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<SkillDto>>> {
  const rb = new RequestBuilder(rootUrl, findByCriteria4.PATH, 'get');
  if (params) {
    rb.query('criteria', params.criteria, {});
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

findByCriteria4.PATH = '/skills/by-criteria/';
