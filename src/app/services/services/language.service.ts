/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { deleteLanguage } from '../fn/language/delete-language';
import { DeleteLanguage$Params } from '../fn/language/delete-language';
import { findAllLanguages } from '../fn/language/find-all-languages';
import { FindAllLanguages$Params } from '../fn/language/find-all-languages';
import { findLanguageById } from '../fn/language/find-language-by-id';
import { FindLanguageById$Params } from '../fn/language/find-language-by-id';
import { LanguageDto } from '../models/language-dto';
import { saveLanguage } from '../fn/language/save-language';
import { SaveLanguage$Params } from '../fn/language/save-language';
import { updateLanguage } from '../fn/language/update-language';
import { UpdateLanguage$Params } from '../fn/language/update-language';


/**
 * The Language API
 */
@Injectable({ providedIn: 'root' })
export class LanguageService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllLanguages()` */
  static readonly FindAllLanguagesPath = '/users/languages';

  /**
   * Find all languages.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllLanguages()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllLanguages$Response(params?: FindAllLanguages$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<LanguageDto>>> {
    return findAllLanguages(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all languages.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllLanguages$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllLanguages(params?: FindAllLanguages$Params, context?: HttpContext): Observable<Array<LanguageDto>> {
    return this.findAllLanguages$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<LanguageDto>>): Array<LanguageDto> => r.body)
    );
  }

  /** Path part for operation `saveLanguage()` */
  static readonly SaveLanguagePath = '/users/languages';

  /**
   * Save language.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveLanguage()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveLanguage$Response(params: SaveLanguage$Params, context?: HttpContext): Observable<StrictHttpResponse<LanguageDto>> {
    return saveLanguage(this.http, this.rootUrl, params, context);
  }

  /**
   * Save language.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveLanguage$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveLanguage(params: SaveLanguage$Params, context?: HttpContext): Observable<LanguageDto> {
    return this.saveLanguage$Response(params, context).pipe(
      map((r: StrictHttpResponse<LanguageDto>): LanguageDto => r.body)
    );
  }

  /** Path part for operation `updateLanguage()` */
  static readonly UpdateLanguagePath = '/users/languages';

  /**
   * Update language.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateLanguage()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateLanguage$Response(params: UpdateLanguage$Params, context?: HttpContext): Observable<StrictHttpResponse<LanguageDto>> {
    return updateLanguage(this.http, this.rootUrl, params, context);
  }

  /**
   * Update language.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateLanguage$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateLanguage(params: UpdateLanguage$Params, context?: HttpContext): Observable<LanguageDto> {
    return this.updateLanguage$Response(params, context).pipe(
      map((r: StrictHttpResponse<LanguageDto>): LanguageDto => r.body)
    );
  }

  /** Path part for operation `findLanguageById()` */
  static readonly FindLanguageByIdPath = '/users/languages/{id}';

  /**
   * Find language by id.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findLanguageById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findLanguageById$Response(params: FindLanguageById$Params, context?: HttpContext): Observable<StrictHttpResponse<LanguageDto>> {
    return findLanguageById(this.http, this.rootUrl, params, context);
  }

  /**
   * Find language by id.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findLanguageById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findLanguageById(params: FindLanguageById$Params, context?: HttpContext): Observable<LanguageDto> {
    return this.findLanguageById$Response(params, context).pipe(
      map((r: StrictHttpResponse<LanguageDto>): LanguageDto => r.body)
    );
  }

  /** Path part for operation `deleteLanguage()` */
  static readonly DeleteLanguagePath = '/users/languages/{id}';

  /**
   * Delete language.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteLanguage()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteLanguage$Response(params: DeleteLanguage$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteLanguage(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete language.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteLanguage$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteLanguage(params: DeleteLanguage$Params, context?: HttpContext): Observable<void> {
    return this.deleteLanguage$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
