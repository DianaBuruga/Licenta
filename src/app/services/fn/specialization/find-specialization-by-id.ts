/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { SpecializationDto } from '../../models/specialization-dto';

export interface FindSpecializationById$Params {

/**
 * Id of the specialization that will be received
 */
  id: string;
}

export function findSpecializationById(http: HttpClient, rootUrl: string, params: FindSpecializationById$Params, context?: HttpContext): Observable<StrictHttpResponse<SpecializationDto>> {
  const rb = new RequestBuilder(rootUrl, findSpecializationById.PATH, 'get');
  if (params) {
    rb.path('id', params.id, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<SpecializationDto>;
    })
  );
}

findSpecializationById.PATH = '/specializations/{id}';
