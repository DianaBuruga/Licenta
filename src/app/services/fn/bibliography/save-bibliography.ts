/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { BibliographyDto } from '../../models/bibliography-dto';

export interface SaveBibliography$Params {
      body: BibliographyDto
}

export function saveBibliography(http: HttpClient, rootUrl: string, params: SaveBibliography$Params, context?: HttpContext): Observable<StrictHttpResponse<BibliographyDto>> {
  const rb = new RequestBuilder(rootUrl, saveBibliography.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
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

saveBibliography.PATH = '/bibliographies';
