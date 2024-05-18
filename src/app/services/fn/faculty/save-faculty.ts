/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { FacultyDto } from '../../models/faculty-dto';

export interface SaveFaculty$Params {
      body: FacultyDto
}

export function saveFaculty(http: HttpClient, rootUrl: string, params: SaveFaculty$Params, context?: HttpContext): Observable<StrictHttpResponse<FacultyDto>> {
  const rb = new RequestBuilder(rootUrl, saveFaculty.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<FacultyDto>;
    })
  );
}

saveFaculty.PATH = '/faculties';
