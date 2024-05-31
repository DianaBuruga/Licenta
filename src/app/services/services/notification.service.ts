/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { deleteNotification } from '../fn/notification/delete-notification';
import { DeleteNotification$Params } from '../fn/notification/delete-notification';
import { findAllNotifications } from '../fn/notification/find-all-notifications';
import { FindAllNotifications$Params } from '../fn/notification/find-all-notifications';
import { findByCriteria6 } from '../fn/notification/find-by-criteria-6';
import { FindByCriteria6$Params } from '../fn/notification/find-by-criteria-6';
import { findNotificationById } from '../fn/notification/find-notification-by-id';
import { FindNotificationById$Params } from '../fn/notification/find-notification-by-id';
import { NotificationDto } from '../models/notification-dto';
import { saveNotification } from '../fn/notification/save-notification';
import { SaveNotification$Params } from '../fn/notification/save-notification';
import { updateNotification } from '../fn/notification/update-notification';
import { UpdateNotification$Params } from '../fn/notification/update-notification';


/**
 * The Notification API
 */
@Injectable({ providedIn: 'root' })
export class NotificationService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllNotifications()` */
  static readonly FindAllNotificationsPath = '/notifications';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllNotifications()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllNotifications$Response(params?: FindAllNotifications$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<NotificationDto>>> {
    return findAllNotifications(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllNotifications$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllNotifications(params?: FindAllNotifications$Params, context?: HttpContext): Observable<Array<NotificationDto>> {
    return this.findAllNotifications$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<NotificationDto>>): Array<NotificationDto> => r.body)
    );
  }

  /** Path part for operation `saveNotification()` */
  static readonly SaveNotificationPath = '/notifications';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveNotification()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveNotification$Response(params: SaveNotification$Params, context?: HttpContext): Observable<StrictHttpResponse<NotificationDto>> {
    return saveNotification(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveNotification$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveNotification(params: SaveNotification$Params, context?: HttpContext): Observable<NotificationDto> {
    return this.saveNotification$Response(params, context).pipe(
      map((r: StrictHttpResponse<NotificationDto>): NotificationDto => r.body)
    );
  }

  /** Path part for operation `deleteNotification()` */
  static readonly DeleteNotificationPath = '/notifications';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteNotification()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteNotification$Response(params: DeleteNotification$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteNotification(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteNotification$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteNotification(params: DeleteNotification$Params, context?: HttpContext): Observable<void> {
    return this.deleteNotification$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `updateNotification()` */
  static readonly UpdateNotificationPath = '/notifications';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateNotification()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateNotification$Response(params: UpdateNotification$Params, context?: HttpContext): Observable<StrictHttpResponse<NotificationDto>> {
    return updateNotification(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateNotification$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateNotification(params: UpdateNotification$Params, context?: HttpContext): Observable<NotificationDto> {
    return this.updateNotification$Response(params, context).pipe(
      map((r: StrictHttpResponse<NotificationDto>): NotificationDto => r.body)
    );
  }

  /** Path part for operation `findByCriteria6()` */
  static readonly FindByCriteria6Path = '/notifications/by-criteria/';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findByCriteria6()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  findByCriteria6$Response(params: FindByCriteria6$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<NotificationDto>>> {
    return findByCriteria6(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findByCriteria6$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  findByCriteria6(params: FindByCriteria6$Params, context?: HttpContext): Observable<Array<NotificationDto>> {
    return this.findByCriteria6$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<NotificationDto>>): Array<NotificationDto> => r.body)
    );
  }

  /** Path part for operation `findNotificationById()` */
  static readonly FindNotificationByIdPath = '/notifications/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findNotificationById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findNotificationById$Response(params: FindNotificationById$Params, context?: HttpContext): Observable<StrictHttpResponse<NotificationDto>> {
    return findNotificationById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findNotificationById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findNotificationById(params: FindNotificationById$Params, context?: HttpContext): Observable<NotificationDto> {
    return this.findNotificationById$Response(params, context).pipe(
      map((r: StrictHttpResponse<NotificationDto>): NotificationDto => r.body)
    );
  }

}
