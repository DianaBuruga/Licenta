/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { FacultyDto } from '../../models/faculty-dto';

export interface UpdateFaculty$Params {
      body: FacultyDto
}

export function updateFaculty(http: HttpClient, rootUrl: string, params: UpdateFaculty$Params, context?: HttpContext): Observable<StrictHttpResponse<FacultyDto>> {
  const rb = new RequestBuilder(rootUrl, updateFaculty.PATH, 'patch');
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

updateFaculty.PATH = '/faculties';
