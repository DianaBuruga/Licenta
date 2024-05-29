/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { BibliographyDto } from '../../models/bibliography-dto';
import { SearchCriteria } from '../../models/search-criteria';

export interface FindByCriteria15$Params {
      body: Array<SearchCriteria>
}

export function findByCriteria15(http: HttpClient, rootUrl: string, params: FindByCriteria15$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<BibliographyDto>>> {
  const rb = new RequestBuilder(rootUrl, findByCriteria15.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<BibliographyDto>>;
    })
  );
}

findByCriteria15.PATH = '/bibliographies/by-criteria/';
