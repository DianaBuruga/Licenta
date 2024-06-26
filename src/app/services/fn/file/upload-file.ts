/* tslint:disable */
/* eslint-disable */
import {HttpClient, HttpContext, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {StrictHttpResponse} from '../../strict-http-response';
import {RequestBuilder} from '../../request-builder';


export interface UploadFile$Params {
  id: string;
  table: string;
  fileType: 'CV' | 'CERTIFICATE' | 'POST_PHOTO' | 'PROFILE_PHOTO' | 'ASSETS';
  body?: {
    'multipartFile': Blob;
  }
}

export function uploadFile(http: HttpClient, rootUrl: string, params: UploadFile$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, uploadFile.PATH, 'post');
  if (params.id && params.body) {
    const formData = new FormData();
    formData.append('multipartFile', params.body.multipartFile);
    formData.append('id', params.id);
    formData.append('table', params.table);
    formData.append('fileType', params.fileType);
    rb.body(formData);
  }

  return http.request(
    rb.build({responseType: 'json', accept: 'application/json', context})
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return (r as HttpResponse<any>).clone({body: undefined}) as StrictHttpResponse<void>;
    })
  );
}

uploadFile.PATH = '/files';
