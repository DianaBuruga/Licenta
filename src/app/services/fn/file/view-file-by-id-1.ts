/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';
import { FileData } from '../../models/file-data';


export interface ViewFileById1$Params {
  id: string;
  table: string;
  type: 'CV' | 'CERTIFICATE' | 'POST_PHOTO' | 'PROFILE_PHOTO' | 'ASSETS';
}

export function viewFileById1(http: HttpClient, rootUrl: string, params: ViewFileById1$Params, context?: HttpContext): Observable<StrictHttpResponse<FileData>> {
  const rb = new RequestBuilder(rootUrl, viewFileById1.PATH, 'get');
  if (params) {
    rb.path('id', params.id, {});
    rb.path('table', params.table, {});
    rb.path('type', params.type, {});
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      console.log('Response', r);
      const contentDisposition = r.headers.get('Content-Disposition');
      console.log('Content-Disposition', contentDisposition);
      let filename = 'unknown';
      if (contentDisposition) {
        const matches = /filename=([^;]*)/.exec(contentDisposition);
        console.log('Matches', matches);
        if (matches != null && matches[1]) {
          filename = matches[1];
        }
      }

      const fileData: FileData = {
        blob: r.body!,
        name: filename
      };

      return {
        ...r,
        body: fileData
      } as StrictHttpResponse<FileData>;
    })
  );
}

viewFileById1.PATH = '/files/view/{id}/{table}/{type}';
