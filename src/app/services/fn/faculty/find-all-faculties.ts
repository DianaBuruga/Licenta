/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { FacultyDto } from '../../models/faculty-dto';

export interface FindAllFaculties$Params {
}

export function findAllFaculties(http: HttpClient, rootUrl: string, params?: FindAllFaculties$Params, context?: HttpContext): Observable<StrictHttpResponse<FacultyDto>> {
  const rb = new RequestBuilder(rootUrl, findAllFaculties.PATH, 'get');
  if (params) {
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

findAllFaculties.PATH = '/faculties';
