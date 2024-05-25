/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { deleteUser } from '../fn/user/delete-user';
import { DeleteUser$Params } from '../fn/user/delete-user';
import { downloadFileById } from '../fn/user/download-file-by-id';
import { DownloadFileById$Params } from '../fn/user/download-file-by-id';
import { exportUserPdf } from '../fn/user/export-user-pdf';
import { ExportUserPdf$Params } from '../fn/user/export-user-pdf';
import { findAllUsers } from '../fn/user/find-all-users';
import { FindAllUsers$Params } from '../fn/user/find-all-users';
import { findByCriteria1 } from '../fn/user/find-by-criteria-1';
import { FindByCriteria1$Params } from '../fn/user/find-by-criteria-1';
import { findUserByEmail } from '../fn/user/find-user-by-email';
import { FindUserByEmail$Params } from '../fn/user/find-user-by-email';
import { findUserById } from '../fn/user/find-user-by-id';
import { FindUserById$Params } from '../fn/user/find-user-by-id';
import { getAuthenticatedUser } from '../fn/user/get-authenticated-user';
import { GetAuthenticatedUser$Params } from '../fn/user/get-authenticated-user';
import { saveProfilePhoto } from '../fn/user/save-profile-photo';
import { SaveProfilePhoto$Params } from '../fn/user/save-profile-photo';
import { saveUser } from '../fn/user/save-user';
import { SaveUser$Params } from '../fn/user/save-user';
import { updateUser } from '../fn/user/update-user';
import { UpdateUser$Params } from '../fn/user/update-user';
import { UserDto } from '../models/user-dto';
import { viewFileById } from '../fn/user/view-file-by-id';
import { ViewFileById$Params } from '../fn/user/view-file-by-id';


/**
 * The User API
 */
@Injectable({ providedIn: 'root' })
export class UserService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllUsers()` */
  static readonly FindAllUsersPath = '/users';

  /**
   * Find all users.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllUsers()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllUsers$Response(params?: FindAllUsers$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<UserDto>>> {
    return findAllUsers(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all users.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllUsers$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllUsers(params?: FindAllUsers$Params, context?: HttpContext): Observable<Array<UserDto>> {
    return this.findAllUsers$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<UserDto>>): Array<UserDto> => r.body)
    );
  }

  /** Path part for operation `saveUser()` */
  static readonly SaveUserPath = '/users';

  /**
   * Save user.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveUser()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveUser$Response(params: SaveUser$Params, context?: HttpContext): Observable<StrictHttpResponse<UserDto>> {
    return saveUser(this.http, this.rootUrl, params, context);
  }

  /**
   * Save user.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveUser$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveUser(params: SaveUser$Params, context?: HttpContext): Observable<UserDto> {
    return this.saveUser$Response(params, context).pipe(
      map((r: StrictHttpResponse<UserDto>): UserDto => r.body)
    );
  }

  /** Path part for operation `updateUser()` */
  static readonly UpdateUserPath = '/users';

  /**
   * Update user.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateUser()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateUser$Response(params: UpdateUser$Params, context?: HttpContext): Observable<StrictHttpResponse<UserDto>> {
    return updateUser(this.http, this.rootUrl, params, context);
  }

  /**
   * Update user.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateUser$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateUser(params: UpdateUser$Params, context?: HttpContext): Observable<UserDto> {
    return this.updateUser$Response(params, context).pipe(
      map((r: StrictHttpResponse<UserDto>): UserDto => r.body)
    );
  }

  /** Path part for operation `saveProfilePhoto()` */
  static readonly SaveProfilePhotoPath = '/users/profile-photo';

  /**
   * Upload user profile photo.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveProfilePhoto()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveProfilePhoto$Response(params: SaveProfilePhoto$Params, context?: HttpContext): Observable<StrictHttpResponse<UserDto>> {
    return saveProfilePhoto(this.http, this.rootUrl, params, context);
  }

  /**
   * Upload user profile photo.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveProfilePhoto$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveProfilePhoto(params: SaveProfilePhoto$Params, context?: HttpContext): Observable<UserDto> {
    return this.saveProfilePhoto$Response(params, context).pipe(
      map((r: StrictHttpResponse<UserDto>): UserDto => r.body)
    );
  }

  /** Path part for operation `viewFileById()` */
  static readonly ViewFileByIdPath = '/users/{id}/profilePhoto/view/';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `viewFileById()` instead.
   *
   * This method doesn't expect any request body.
   */
  viewFileById$Response(params: ViewFileById$Params, context?: HttpContext): Observable<StrictHttpResponse<Blob>> {
    return viewFileById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `viewFileById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  viewFileById(params: ViewFileById$Params, context?: HttpContext): Observable<Blob> {
    return this.viewFileById$Response(params, context).pipe(
      map((r: StrictHttpResponse<Blob>): Blob => r.body)
    );
  }

  /** Path part for operation `getAuthenticatedUser()` */
  static readonly GetAuthenticatedUserPath = '/users/userinfo';

  /**
   * Get user info.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAuthenticatedUser()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAuthenticatedUser$Response(params?: GetAuthenticatedUser$Params, context?: HttpContext): Observable<StrictHttpResponse<UserDto>> {
    return getAuthenticatedUser(this.http, this.rootUrl, params, context);
  }

  /**
   * Get user info.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAuthenticatedUser$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAuthenticatedUser(params?: GetAuthenticatedUser$Params, context?: HttpContext): Observable<UserDto> {
    return this.getAuthenticatedUser$Response(params, context).pipe(
      map((r: StrictHttpResponse<UserDto>): UserDto => r.body)
    );
  }

  /** Path part for operation `findUserById()` */
  static readonly FindUserByIdPath = '/users/id/{id}';

  /**
   * Find user by id.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findUserById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findUserById$Response(params: FindUserById$Params, context?: HttpContext): Observable<StrictHttpResponse<UserDto>> {
    return findUserById(this.http, this.rootUrl, params, context);
  }

  /**
   * Find user by id.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findUserById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findUserById(params: FindUserById$Params, context?: HttpContext): Observable<UserDto> {
    return this.findUserById$Response(params, context).pipe(
      map((r: StrictHttpResponse<UserDto>): UserDto => r.body)
    );
  }

  /** Path part for operation `downloadFileById()` */
  static readonly DownloadFileByIdPath = '/users/id/{id}/profilePhoto/download/';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `downloadFileById()` instead.
   *
   * This method doesn't expect any request body.
   */
  downloadFileById$Response(params: DownloadFileById$Params, context?: HttpContext): Observable<StrictHttpResponse<Blob>> {
    return downloadFileById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `downloadFileById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  downloadFileById(params: DownloadFileById$Params, context?: HttpContext): Observable<Blob> {
    return this.downloadFileById$Response(params, context).pipe(
      map((r: StrictHttpResponse<Blob>): Blob => r.body)
    );
  }

  /** Path part for operation `findUserByEmail()` */
  static readonly FindUserByEmailPath = '/users/email/{email}';

  /**
   * Find user by email.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findUserByEmail()` instead.
   *
   * This method doesn't expect any request body.
   */
  findUserByEmail$Response(params: FindUserByEmail$Params, context?: HttpContext): Observable<StrictHttpResponse<UserDto>> {
    return findUserByEmail(this.http, this.rootUrl, params, context);
  }

  /**
   * Find user by email.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findUserByEmail$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findUserByEmail(params: FindUserByEmail$Params, context?: HttpContext): Observable<UserDto> {
    return this.findUserByEmail$Response(params, context).pipe(
      map((r: StrictHttpResponse<UserDto>): UserDto => r.body)
    );
  }

  /** Path part for operation `findByCriteria1()` */
  static readonly FindByCriteria1Path = '/users/by-criteria/';

  /**
   * Find user by criteria.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findByCriteria1()` instead.
   *
   * This method doesn't expect any request body.
   */
  findByCriteria1$Response(params: FindByCriteria1$Params, context?: HttpContext): Observable<StrictHttpResponse<UserDto>> {
    return findByCriteria1(this.http, this.rootUrl, params, context);
  }

  /**
   * Find user by criteria.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findByCriteria1$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findByCriteria1(params: FindByCriteria1$Params, context?: HttpContext): Observable<UserDto> {
    return this.findByCriteria1$Response(params, context).pipe(
      map((r: StrictHttpResponse<UserDto>): UserDto => r.body)
    );
  }

  /** Path part for operation `exportUserPdf()` */
  static readonly ExportUserPdfPath = '/users/CV/{id}';

  /**
   * Export a file in pdf format.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `exportUserPdf()` instead.
   *
   * This method doesn't expect any request body.
   */
  exportUserPdf$Response(params: ExportUserPdf$Params, context?: HttpContext): Observable<StrictHttpResponse<UserDto>> {
    return exportUserPdf(this.http, this.rootUrl, params, context);
  }

  /**
   * Export a file in pdf format.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `exportUserPdf$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  exportUserPdf(params: ExportUserPdf$Params, context?: HttpContext): Observable<UserDto> {
    return this.exportUserPdf$Response(params, context).pipe(
      map((r: StrictHttpResponse<UserDto>): UserDto => r.body)
    );
  }

  /** Path part for operation `deleteUser()` */
  static readonly DeleteUserPath = '/users/{email}';

  /**
   * Delete user.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteUser()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteUser$Response(params: DeleteUser$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteUser(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete user.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteUser$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteUser(params: DeleteUser$Params, context?: HttpContext): Observable<void> {
    return this.deleteUser$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
