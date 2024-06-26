/* tslint:disable */
/* eslint-disable */
import {HttpClient, HttpContext, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {StrictHttpResponse} from '../../strict-http-response';
import {RequestBuilder} from '../../request-builder';

import {UserDto} from '../../models/user-dto';

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
  if (params.id && params.body) {
    const formData = new FormData();
    formData.append('multipartFile', params.body.multipartFile);
    formData.append('id', params.id);

    const rb = new RequestBuilder(rootUrl, saveProfilePhoto.PATH, 'post');
    rb.body(formData);

    return http.request(
      rb.build({responseType: 'json', accept: 'application/json', context})
    ).pipe(
      filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<UserDto>;
      })
    );
  }
  throw new Error('Required parameter body was null or undefined when calling saveProfilePhoto');
}

saveProfilePhoto.PATH = '/users/profile-photo';
