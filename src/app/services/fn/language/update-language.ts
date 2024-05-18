/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { LanguageDto } from '../../models/language-dto';

export interface UpdateLanguage$Params {
      body: LanguageDto
}

export function updateLanguage(http: HttpClient, rootUrl: string, params: UpdateLanguage$Params, context?: HttpContext): Observable<StrictHttpResponse<LanguageDto>> {
  const rb = new RequestBuilder(rootUrl, updateLanguage.PATH, 'patch');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<LanguageDto>;
    })
  );
}

updateLanguage.PATH = '/users/languages';
