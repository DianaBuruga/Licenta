/* tslint:disable */
/* eslint-disable */
import {HttpClient, HttpContext} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {BaseService} from '../base-service';
import {ApiConfiguration} from '../api-configuration';
import {StrictHttpResponse} from '../strict-http-response';

import {generatePdf} from '../fn/zip-generator-controller/generate-pdf';
import {GeneratePdf$Params} from '../fn/zip-generator-controller/generate-pdf';

@Injectable({providedIn: 'root'})
export class ZipGeneratorControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `generatePdf()` */
  static readonly GeneratePdfPath = '/generate-zip';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `generatePdf()` instead.
   *
   * This method doesn't expect any request body.
   */
  generatePdf$Response(params?: GeneratePdf$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return generatePdf(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `generatePdf$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  generatePdf(params?: GeneratePdf$Params, context?: HttpContext): Observable<void> {
    return this.generatePdf$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
