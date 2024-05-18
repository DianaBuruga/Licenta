/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { deleteEvent } from '../fn/event/delete-event';
import { DeleteEvent$Params } from '../fn/event/delete-event';
import { EventDto } from '../models/event-dto';
import { findAllEvents } from '../fn/event/find-all-events';
import { FindAllEvents$Params } from '../fn/event/find-all-events';
import { findByCriteria9 } from '../fn/event/find-by-criteria-9';
import { FindByCriteria9$Params } from '../fn/event/find-by-criteria-9';
import { findEventById } from '../fn/event/find-event-by-id';
import { FindEventById$Params } from '../fn/event/find-event-by-id';
import { saveEvent } from '../fn/event/save-event';
import { SaveEvent$Params } from '../fn/event/save-event';
import { updateEvent } from '../fn/event/update-event';
import { UpdateEvent$Params } from '../fn/event/update-event';


/**
 * The Event API
 */
@Injectable({ providedIn: 'root' })
export class EventService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllEvents()` */
  static readonly FindAllEventsPath = '/events';

  /**
   * Find all events.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllEvents()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllEvents$Response(params?: FindAllEvents$Params, context?: HttpContext): Observable<StrictHttpResponse<EventDto>> {
    return findAllEvents(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all events.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllEvents$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllEvents(params?: FindAllEvents$Params, context?: HttpContext): Observable<EventDto> {
    return this.findAllEvents$Response(params, context).pipe(
      map((r: StrictHttpResponse<EventDto>): EventDto => r.body)
    );
  }

  /** Path part for operation `saveEvent()` */
  static readonly SaveEventPath = '/events';

  /**
   * Save event.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveEvent()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveEvent$Response(params: SaveEvent$Params, context?: HttpContext): Observable<StrictHttpResponse<EventDto>> {
    return saveEvent(this.http, this.rootUrl, params, context);
  }

  /**
   * Save event.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveEvent$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveEvent(params: SaveEvent$Params, context?: HttpContext): Observable<EventDto> {
    return this.saveEvent$Response(params, context).pipe(
      map((r: StrictHttpResponse<EventDto>): EventDto => r.body)
    );
  }

  /** Path part for operation `updateEvent()` */
  static readonly UpdateEventPath = '/events';

  /**
   * Update event.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateEvent()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateEvent$Response(params: UpdateEvent$Params, context?: HttpContext): Observable<StrictHttpResponse<EventDto>> {
    return updateEvent(this.http, this.rootUrl, params, context);
  }

  /**
   * Update event.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateEvent$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateEvent(params: UpdateEvent$Params, context?: HttpContext): Observable<EventDto> {
    return this.updateEvent$Response(params, context).pipe(
      map((r: StrictHttpResponse<EventDto>): EventDto => r.body)
    );
  }

  /** Path part for operation `findEventById()` */
  static readonly FindEventByIdPath = '/events/{id}';

  /**
   * Find event by id.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findEventById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findEventById$Response(params: FindEventById$Params, context?: HttpContext): Observable<StrictHttpResponse<EventDto>> {
    return findEventById(this.http, this.rootUrl, params, context);
  }

  /**
   * Find event by id.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findEventById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findEventById(params: FindEventById$Params, context?: HttpContext): Observable<EventDto> {
    return this.findEventById$Response(params, context).pipe(
      map((r: StrictHttpResponse<EventDto>): EventDto => r.body)
    );
  }

  /** Path part for operation `deleteEvent()` */
  static readonly DeleteEventPath = '/events/{id}';

  /**
   * Delete event.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteEvent()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteEvent$Response(params: DeleteEvent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteEvent(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete event.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteEvent$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteEvent(params: DeleteEvent$Params, context?: HttpContext): Observable<void> {
    return this.deleteEvent$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `findByCriteria9()` */
  static readonly FindByCriteria9Path = '/events/by-criteria/';

  /**
   * Find event by criteria.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findByCriteria9()` instead.
   *
   * This method doesn't expect any request body.
   */
  findByCriteria9$Response(params: FindByCriteria9$Params, context?: HttpContext): Observable<StrictHttpResponse<EventDto>> {
    return findByCriteria9(this.http, this.rootUrl, params, context);
  }

  /**
   * Find event by criteria.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findByCriteria9$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findByCriteria9(params: FindByCriteria9$Params, context?: HttpContext): Observable<EventDto> {
    return this.findByCriteria9$Response(params, context).pipe(
      map((r: StrictHttpResponse<EventDto>): EventDto => r.body)
    );
  }

}
