/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { UserDto } from '../../models/user-dto';

export interface SaveProfilePhoto$Params {

/**
 * User id
 */
  id: string;
      body?: {

/**
 * Profile photo
 */
'multipartFile': Blob;
}
}

export function saveProfilePhoto(http: HttpClient, rootUrl: string, params: SaveProfilePhoto$Params, context?: HttpContext): Observable<StrictHttpResponse<UserDto>> {
  const rb = new RequestBuilder(rootUrl, saveProfilePhoto.PATH, 'post');
  if (params) {
    rb.query('id', params.id, {});
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<UserDto>;
    })
  );
}

saveProfilePhoto.PATH = '/users/profile-photo';
