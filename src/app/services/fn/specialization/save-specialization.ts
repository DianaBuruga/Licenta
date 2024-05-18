/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { SpecializationDto } from '../../models/specialization-dto';

export interface SaveSpecialization$Params {
      body: SpecializationDto
}

export function saveSpecialization(http: HttpClient, rootUrl: string, params: SaveSpecialization$Params, context?: HttpContext): Observable<StrictHttpResponse<SpecializationDto>> {
  const rb = new RequestBuilder(rootUrl, saveSpecialization.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
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

saveSpecialization.PATH = '/specializations';
