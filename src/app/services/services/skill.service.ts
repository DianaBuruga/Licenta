/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { deleteSkill } from '../fn/skill/delete-skill';
import { DeleteSkill$Params } from '../fn/skill/delete-skill';
import { findAllSkills } from '../fn/skill/find-all-skills';
import { FindAllSkills$Params } from '../fn/skill/find-all-skills';
import { findByCriteria4 } from '../fn/skill/find-by-criteria-4';
import { FindByCriteria4$Params } from '../fn/skill/find-by-criteria-4';
import { findSkillById } from '../fn/skill/find-skill-by-id';
import { FindSkillById$Params } from '../fn/skill/find-skill-by-id';
import { saveSkill } from '../fn/skill/save-skill';
import { SaveSkill$Params } from '../fn/skill/save-skill';
import { SkillDto } from '../models/skill-dto';
import { updateSkill } from '../fn/skill/update-skill';
import { UpdateSkill$Params } from '../fn/skill/update-skill';


/**
 * The Skill API
 */
@Injectable({ providedIn: 'root' })
export class SkillService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllSkills()` */
  static readonly FindAllSkillsPath = '/skills';

  /**
   * Find all skills.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllSkills()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllSkills$Response(params?: FindAllSkills$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<SkillDto>>> {
    return findAllSkills(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all skills.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllSkills$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllSkills(params?: FindAllSkills$Params, context?: HttpContext): Observable<Array<SkillDto>> {
    return this.findAllSkills$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<SkillDto>>): Array<SkillDto> => r.body)
    );
  }

  /** Path part for operation `saveSkill()` */
  static readonly SaveSkillPath = '/skills';

  /**
   * Save skill.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveSkill()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveSkill$Response(params: SaveSkill$Params, context?: HttpContext): Observable<StrictHttpResponse<SkillDto>> {
    return saveSkill(this.http, this.rootUrl, params, context);
  }

  /**
   * Save skill.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveSkill$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveSkill(params: SaveSkill$Params, context?: HttpContext): Observable<SkillDto> {
    return this.saveSkill$Response(params, context).pipe(
      map((r: StrictHttpResponse<SkillDto>): SkillDto => r.body)
    );
  }

  /** Path part for operation `deleteSkill()` */
  static readonly DeleteSkillPath = '/skills';

  /**
   * Delete skill.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteSkill()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteSkill$Response(params: DeleteSkill$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteSkill(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete skill.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteSkill$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteSkill(params: DeleteSkill$Params, context?: HttpContext): Observable<void> {
    return this.deleteSkill$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `updateSkill()` */
  static readonly UpdateSkillPath = '/skills';

  /**
   * Update skill.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateSkill()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateSkill$Response(params: UpdateSkill$Params, context?: HttpContext): Observable<StrictHttpResponse<SkillDto>> {
    return updateSkill(this.http, this.rootUrl, params, context);
  }

  /**
   * Update skill.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateSkill$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateSkill(params: UpdateSkill$Params, context?: HttpContext): Observable<SkillDto> {
    return this.updateSkill$Response(params, context).pipe(
      map((r: StrictHttpResponse<SkillDto>): SkillDto => r.body)
    );
  }

  /** Path part for operation `findByCriteria4()` */
  static readonly FindByCriteria4Path = '/skills/by-criteria/';

  /**
   * Find skill by criteria.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findByCriteria4()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  findByCriteria4$Response(params: FindByCriteria4$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<SkillDto>>> {
    return findByCriteria4(this.http, this.rootUrl, params, context);
  }

  /**
   * Find skill by criteria.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findByCriteria4$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  findByCriteria4(params: FindByCriteria4$Params, context?: HttpContext): Observable<Array<SkillDto>> {
    return this.findByCriteria4$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<SkillDto>>): Array<SkillDto> => r.body)
    );
  }

  /** Path part for operation `findSkillById()` */
  static readonly FindSkillByIdPath = '/skills/{id}';

  /**
   * Find skill by id.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findSkillById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findSkillById$Response(params: FindSkillById$Params, context?: HttpContext): Observable<StrictHttpResponse<SkillDto>> {
    return findSkillById(this.http, this.rootUrl, params, context);
  }

  /**
   * Find skill by id.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findSkillById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findSkillById(params: FindSkillById$Params, context?: HttpContext): Observable<SkillDto> {
    return this.findSkillById$Response(params, context).pipe(
      map((r: StrictHttpResponse<SkillDto>): SkillDto => r.body)
    );
  }

}
