/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { LanguageDto } from '../../models/language-dto';

export interface FindAllLanguages$Params {
}

export function findAllLanguages(http: HttpClient, rootUrl: string, params?: FindAllLanguages$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<LanguageDto>>> {
  const rb = new RequestBuilder(rootUrl, findAllLanguages.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<LanguageDto>>;
    })
  );
}

findAllLanguages.PATH = '/users/languages';
