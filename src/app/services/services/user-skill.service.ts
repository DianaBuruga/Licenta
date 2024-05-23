/* tslint:disable */
/* eslint-disable */
import {HttpClient, HttpContext} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

import {BaseService} from '../base-service';
import {ApiConfiguration} from '../api-configuration';
import {StrictHttpResponse} from '../strict-http-response';

import {deleteJobHistory, DeleteJobHistory$Params} from '../fn/user-skill/delete-job-history';
import {findByCriteria2, FindByCriteria2$Params} from '../fn/user-skill/find-by-criteria-2';
import {saveUserSkill, SaveUserSkill$Params} from '../fn/user-skill/save-user-skill';
import {updateUserSkill, UpdateUserSkill$Params} from '../fn/user-skill/update-user-skill';
import {UserSkillsDto} from '../models/user-skills-dto';


/**
 * The UserSkill API
 */
@Injectable({providedIn: 'root'})
export class UserSkillService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `saveUserSkill()` */
  static readonly SaveUserSkillPath = '/userSkills/';

  /**
   * Save user skill.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveUserSkill()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveUserSkill$Response(params: SaveUserSkill$Params, context?: HttpContext): Observable<StrictHttpResponse<UserSkillsDto>> {
    return saveUserSkill(this.http, this.rootUrl, params, context);
  }

  /**
   * Save user skill.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveUserSkill$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveUserSkill(params: SaveUserSkill$Params, context?: HttpContext): Observable<UserSkillsDto> {
    return this.saveUserSkill$Response(params, context).pipe(
      map((r: StrictHttpResponse<UserSkillsDto>): UserSkillsDto => r.body)
    );
  }

  /** Path part for operation `updateUserSkill()` */
  static readonly UpdateUserSkillPath = '/userSkills/';

  /**
   * Update user skill.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateUserSkill()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateUserSkill$Response(params: UpdateUserSkill$Params, context?: HttpContext): Observable<StrictHttpResponse<UserSkillsDto>> {
    return updateUserSkill(this.http, this.rootUrl, params, context);
  }

  /**
   * Update user skill.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateUserSkill$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateUserSkill(params: UpdateUserSkill$Params, context?: HttpContext): Observable<UserSkillsDto> {
    return this.updateUserSkill$Response(params, context).pipe(
      map((r: StrictHttpResponse<UserSkillsDto>): UserSkillsDto => r.body)
    );
  }

  /** Path part for operation `findByCriteria2()` */
  static readonly FindByCriteria2Path = '/userSkills/by-criteria/';

  /**
   * Find UserSkill by criteria.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findByCriteria2()` instead.
   *
   * This method doesn't expect any request body.
   */
  findByCriteria2$Response(params: FindByCriteria2$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<UserSkillsDto>>> {
    return findByCriteria2(this.http, this.rootUrl, params, context);
  }

  /**
   * Find UserSkill by criteria.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findByCriteria2$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findByCriteria2(params: FindByCriteria2$Params, context?: HttpContext): Observable<Array<UserSkillsDto>> {
    return this.findByCriteria2$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<UserSkillsDto>>): Array<UserSkillsDto> => r.body)
    );
  }

  /** Path part for operation `deleteJobHistory()` */
  static readonly DeleteJobHistoryPath = '/userSkills/{userId}/{skillId}';

  /**
   * Delete UserSkill.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteJobHistory()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteJobHistory$Response(params: DeleteJobHistory$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteJobHistory(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete UserSkill.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteJobHistory$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteJobHistory(params: DeleteJobHistory$Params, context?: HttpContext): Observable<void> {
    return this.deleteJobHistory$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
