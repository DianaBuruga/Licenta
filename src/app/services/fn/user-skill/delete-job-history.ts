/* tslint:disable */
/* eslint-disable */
import {HttpClient, HttpContext, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {StrictHttpResponse} from '../../strict-http-response';
import {RequestBuilder} from '../../request-builder';


export interface DeleteJobHistory$Params {

  /**
   * UserId
   */
  userId: string;

  /**
   * SkillId
   */
  skillId: string;
}

export function deleteJobHistory(http: HttpClient, rootUrl: string, params: DeleteJobHistory$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, deleteJobHistory.PATH, 'delete');
  if (params) {
    rb.path('userId', params.userId, {});
    rb.path('skillId', params.skillId, {});
  }

  return http.request(
    rb.build({responseType: 'text', accept: '*/*', context})
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return (r as HttpResponse<any>).clone({body: undefined}) as StrictHttpResponse<void>;
    })
  );
}

deleteJobHistory.PATH = '/userSkills/{userId}/{skillId}';
