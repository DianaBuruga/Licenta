/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { BibliographyDto } from '../../models/bibliography-dto';

export interface FindAllBibliographies$Params {
}

export function findAllBibliographies(http: HttpClient, rootUrl: string, params?: FindAllBibliographies$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<BibliographyDto>>> {
  const rb = new RequestBuilder(rootUrl, findAllBibliographies.PATH, 'get');
  if (params) {
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

findAllBibliographies.PATH = '/bibliographies';
