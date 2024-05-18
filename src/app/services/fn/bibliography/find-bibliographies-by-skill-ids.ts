/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { BibliographyDto } from '../../models/bibliography-dto';

export interface FindBibliographiesBySkillIds$Params {

/**
 * List of skills ids
 */
  skillIds: Array<string>;
}

export function findBibliographiesBySkillIds(http: HttpClient, rootUrl: string, params: FindBibliographiesBySkillIds$Params, context?: HttpContext): Observable<StrictHttpResponse<BibliographyDto>> {
  const rb = new RequestBuilder(rootUrl, findBibliographiesBySkillIds.PATH, 'get');
  if (params) {
    rb.path('skillIds', params.skillIds, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<BibliographyDto>;
    })
  );
}

findBibliographiesBySkillIds.PATH = '/bibliographies/by-skills/{skillIds}';
