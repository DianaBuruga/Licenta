/* tslint:disable */
/* eslint-disable */
import {HttpClient, HttpContext} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {BaseService} from '../base-service';
import {ApiConfiguration} from '../api-configuration';
import {StrictHttpResponse} from '../strict-http-response';

import {uploadAssets} from '../fn/file/upload-assets';
import {UploadAssets$Params} from '../fn/file/upload-assets';
import {uploadFile} from '../fn/file/upload-file';
import {UploadFile$Params} from '../fn/file/upload-file';
import {viewFileById1} from '../fn/file/view-file-by-id-1';
import {ViewFileById1$Params} from '../fn/file/view-file-by-id-1';
import {viewFileByName} from '../fn/file/view-file-by-name';
import {ViewFileByName$Params} from '../fn/file/view-file-by-name';
import { FileDto } from '../models';
import { FileData } from '../models/file-data';


/**
 * The File API
 */
@Injectable({providedIn: 'root'})
export class FileService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `uploadFile()` */
  static readonly UploadFilePath = '/files';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `uploadFile()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  uploadFile$Response(params: UploadFile$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return uploadFile(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `uploadFile$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  uploadFile(params: UploadFile$Params, context?: HttpContext): Observable<void> {
    return this.uploadFile$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `uploadAssets()` */
  static readonly UploadAssetsPath = '/files/assets';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `uploadAssets()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  uploadAssets$Response(params?: UploadAssets$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return uploadAssets(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `uploadAssets$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  uploadAssets(params?: UploadAssets$Params, context?: HttpContext): Observable<void> {
    return this.uploadAssets$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `viewFileByName()` */
  static readonly ViewFileByNamePath = '/files/view/{name}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `viewFileByName()` instead.
   *
   * This method doesn't expect any request body.
   */
  viewFileByName$Response(params: ViewFileByName$Params, context?: HttpContext): Observable<StrictHttpResponse<Blob>> {
    return viewFileByName(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `viewFileByName$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  viewFileByName(params: ViewFileByName$Params, context?: HttpContext): Observable<Blob> {
    return this.viewFileByName$Response(params, context).pipe(
      map((r: StrictHttpResponse<Blob>): Blob => r.body)
    );
  }

  /** Path part for operation `viewFileById1()` */
  static readonly ViewFileById1Path = '/files/view/{id}/{table}/{type}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `viewFileById1()` instead.
   *
   * This method doesn't expect any request body.
   */
  viewFileById1$Response(params: ViewFileById1$Params, context?: HttpContext): Observable<StrictHttpResponse<FileData>> {
    return viewFileById1(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `viewFileById1$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  viewFileById1(params: ViewFileById1$Params, context?: HttpContext): Observable<FileData> {
    return this.viewFileById1$Response(params, context).pipe(
      map((r: StrictHttpResponse<FileData>): FileData => {
        return r.body;
      })
    );
  }
}
