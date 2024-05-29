/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { BibliographyDto } from '../models/bibliography-dto';
import { deleteBibliography } from '../fn/bibliography/delete-bibliography';
import { DeleteBibliography$Params } from '../fn/bibliography/delete-bibliography';
import { EmailRequest } from '../models/email-request';
import { findAllBibliographies } from '../fn/bibliography/find-all-bibliographies';
import { FindAllBibliographies$Params } from '../fn/bibliography/find-all-bibliographies';
import { findBibliographiesBySkillIds } from '../fn/bibliography/find-bibliographies-by-skill-ids';
import { FindBibliographiesBySkillIds$Params } from '../fn/bibliography/find-bibliographies-by-skill-ids';
import { findByCriteria15 } from '../fn/bibliography/find-by-criteria-15';
import { FindByCriteria15$Params } from '../fn/bibliography/find-by-criteria-15';
import { HtmlEmailRequest } from '../models/html-email-request';
import { saveBibliography } from '../fn/bibliography/save-bibliography';
import { SaveBibliography$Params } from '../fn/bibliography/save-bibliography';
import { sendEmail } from '../fn/email/send-email';
import { SendEmail$Params } from '../fn/email/send-email';
import { sendHtmlEmail } from '../fn/email/send-html-email';
import { SendHtmlEmail$Params } from '../fn/email/send-html-email';
import { updateBibliography } from '../fn/bibliography/update-bibliography';
import { UpdateBibliography$Params } from '../fn/bibliography/update-bibliography';


/**
 * The Bibliography API
 */
@Injectable({ providedIn: 'root' })
export class BibliographyService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `sendEmail()` */
  static readonly SendEmailPath = '/email';

  /**
   * Find all bibliographies.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `sendEmail()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  sendEmail$Response(params: SendEmail$Params, context?: HttpContext): Observable<StrictHttpResponse<EmailRequest>> {
    return sendEmail(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all bibliographies.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `sendEmail$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  sendEmail(params: SendEmail$Params, context?: HttpContext): Observable<EmailRequest> {
    return this.sendEmail$Response(params, context).pipe(
      map((r: StrictHttpResponse<EmailRequest>): EmailRequest => r.body)
    );
  }

  /** Path part for operation `sendHtmlEmail()` */
  static readonly SendHtmlEmailPath = '/email/sendHTMLEmail';

  /**
   * Find all bibliographies.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `sendHtmlEmail()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  sendHtmlEmail$Response(params: SendHtmlEmail$Params, context?: HttpContext): Observable<StrictHttpResponse<HtmlEmailRequest>> {
    return sendHtmlEmail(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all bibliographies.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `sendHtmlEmail$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  sendHtmlEmail(params: SendHtmlEmail$Params, context?: HttpContext): Observable<HtmlEmailRequest> {
    return this.sendHtmlEmail$Response(params, context).pipe(
      map((r: StrictHttpResponse<HtmlEmailRequest>): HtmlEmailRequest => r.body)
    );
  }

  /** Path part for operation `findAllBibliographies()` */
  static readonly FindAllBibliographiesPath = '/bibliographies';

  /**
   * Find all bibliographies.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllBibliographies()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllBibliographies$Response(params?: FindAllBibliographies$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<BibliographyDto>>> {
    return findAllBibliographies(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all bibliographies.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllBibliographies$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllBibliographies(params?: FindAllBibliographies$Params, context?: HttpContext): Observable<Array<BibliographyDto>> {
    return this.findAllBibliographies$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<BibliographyDto>>): Array<BibliographyDto> => r.body)
    );
  }

  /** Path part for operation `saveBibliography()` */
  static readonly SaveBibliographyPath = '/bibliographies';

  /**
   * Save bibliography.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveBibliography()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveBibliography$Response(params: SaveBibliography$Params, context?: HttpContext): Observable<StrictHttpResponse<BibliographyDto>> {
    return saveBibliography(this.http, this.rootUrl, params, context);
  }

  /**
   * Save bibliography.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveBibliography$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveBibliography(params: SaveBibliography$Params, context?: HttpContext): Observable<BibliographyDto> {
    return this.saveBibliography$Response(params, context).pipe(
      map((r: StrictHttpResponse<BibliographyDto>): BibliographyDto => r.body)
    );
  }

  /** Path part for operation `updateBibliography()` */
  static readonly UpdateBibliographyPath = '/bibliographies';

  /**
   * Update bibliography.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateBibliography()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateBibliography$Response(params: UpdateBibliography$Params, context?: HttpContext): Observable<StrictHttpResponse<BibliographyDto>> {
    return updateBibliography(this.http, this.rootUrl, params, context);
  }

  /**
   * Update bibliography.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateBibliography$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateBibliography(params: UpdateBibliography$Params, context?: HttpContext): Observable<BibliographyDto> {
    return this.updateBibliography$Response(params, context).pipe(
      map((r: StrictHttpResponse<BibliographyDto>): BibliographyDto => r.body)
    );
  }

  /** Path part for operation `findByCriteria15()` */
  static readonly FindByCriteria15Path = '/bibliographies/by-criteria/';

  /**
   * Find bibliographies by criteria.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findByCriteria15()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  findByCriteria15$Response(params: FindByCriteria15$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<BibliographyDto>>> {
    return findByCriteria15(this.http, this.rootUrl, params, context);
  }

  /**
   * Find bibliographies by criteria.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findByCriteria15$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  findByCriteria15(params: FindByCriteria15$Params, context?: HttpContext): Observable<Array<BibliographyDto>> {
    return this.findByCriteria15$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<BibliographyDto>>): Array<BibliographyDto> => r.body)
    );
  }

  /** Path part for operation `findBibliographiesBySkillIds()` */
  static readonly FindBibliographiesBySkillIdsPath = '/bibliographies/by-skills/{skillIds}';

  /**
   * Find bibliographies by skill ids.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findBibliographiesBySkillIds()` instead.
   *
   * This method doesn't expect any request body.
   */
  findBibliographiesBySkillIds$Response(params: FindBibliographiesBySkillIds$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<BibliographyDto>>> {
    return findBibliographiesBySkillIds(this.http, this.rootUrl, params, context);
  }

  /**
   * Find bibliographies by skill ids.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findBibliographiesBySkillIds$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findBibliographiesBySkillIds(params: FindBibliographiesBySkillIds$Params, context?: HttpContext): Observable<Array<BibliographyDto>> {
    return this.findBibliographiesBySkillIds$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<BibliographyDto>>): Array<BibliographyDto> => r.body)
    );
  }

  /** Path part for operation `deleteBibliography()` */
  static readonly DeleteBibliographyPath = '/bibliographies/{id}';

  /**
   * Delete bibliography.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteBibliography()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteBibliography$Response(params: DeleteBibliography$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteBibliography(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete bibliography.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteBibliography$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteBibliography(params: DeleteBibliography$Params, context?: HttpContext): Observable<void> {
    return this.deleteBibliography$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
