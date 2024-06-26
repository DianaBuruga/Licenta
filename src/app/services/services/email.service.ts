/* tslint:disable */
/* eslint-disable */
import {HttpClient, HttpContext} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {BaseService} from '../base-service';
import {ApiConfiguration} from '../api-configuration';
import {StrictHttpResponse} from '../strict-http-response';

import {ApplicationEmailRequest} from '../models/application-email-request';
import {EmailRequest} from '../models/email-request';
import {sendEmail} from '../fn/email/send-email';
import {SendEmail$Params} from '../fn/email/send-email';
import {sendHtmlEmail} from '../fn/email/send-html-email';
import {SendHtmlEmail$Params} from '../fn/email/send-html-email';


/**
 * The Email API
 */
@Injectable({providedIn: 'root'})
export class EmailService extends BaseService {
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
  sendHtmlEmail$Response(params: SendHtmlEmail$Params, context?: HttpContext): Observable<StrictHttpResponse<ApplicationEmailRequest>> {
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
  sendHtmlEmail(params: SendHtmlEmail$Params, context?: HttpContext): Observable<ApplicationEmailRequest> {
    return this.sendHtmlEmail$Response(params, context).pipe(
      map((r: StrictHttpResponse<ApplicationEmailRequest>): ApplicationEmailRequest => r.body)
    );
  }

}
