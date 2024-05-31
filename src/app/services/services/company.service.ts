/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { CompanyDto } from '../models/company-dto';
import { deleteCompany } from '../fn/company/delete-company';
import { DeleteCompany$Params } from '../fn/company/delete-company';
import { findAllCompanies } from '../fn/company/find-all-companies';
import { FindAllCompanies$Params } from '../fn/company/find-all-companies';
import { findByCriteria13 } from '../fn/company/find-by-criteria-13';
import { FindByCriteria13$Params } from '../fn/company/find-by-criteria-13';
import { findCompanyById } from '../fn/company/find-company-by-id';
import { FindCompanyById$Params } from '../fn/company/find-company-by-id';
import { getCompanyIcon } from '../fn/company/get-company-icon';
import { GetCompanyIcon$Params } from '../fn/company/get-company-icon';
import { saveCompany } from '../fn/company/save-company';
import { SaveCompany$Params } from '../fn/company/save-company';
import { updateCompany } from '../fn/company/update-company';
import { UpdateCompany$Params } from '../fn/company/update-company';


/**
 * The Company API
 */
@Injectable({ providedIn: 'root' })
export class CompanyService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllCompanies()` */
  static readonly FindAllCompaniesPath = '/companies';

  /**
   * Find all companies.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllCompanies()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllCompanies$Response(params?: FindAllCompanies$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<CompanyDto>>> {
    return findAllCompanies(this.http, this.rootUrl, params, context);
  }

  /**
   * Find all companies.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllCompanies$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllCompanies(params?: FindAllCompanies$Params, context?: HttpContext): Observable<Array<CompanyDto>> {
    return this.findAllCompanies$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<CompanyDto>>): Array<CompanyDto> => r.body)
    );
  }

  /** Path part for operation `saveCompany()` */
  static readonly SaveCompanyPath = '/companies';

  /**
   * Save company.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveCompany()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveCompany$Response(params: SaveCompany$Params, context?: HttpContext): Observable<StrictHttpResponse<CompanyDto>> {
    return saveCompany(this.http, this.rootUrl, params, context);
  }

  /**
   * Save company.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveCompany$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveCompany(params: SaveCompany$Params, context?: HttpContext): Observable<CompanyDto> {
    return this.saveCompany$Response(params, context).pipe(
      map((r: StrictHttpResponse<CompanyDto>): CompanyDto => r.body)
    );
  }

  /** Path part for operation `deleteCompany()` */
  static readonly DeleteCompanyPath = '/companies';

  /**
   * Delete company.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteCompany()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteCompany$Response(params: DeleteCompany$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteCompany(this.http, this.rootUrl, params, context);
  }

  /**
   * Delete company.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteCompany$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  deleteCompany(params: DeleteCompany$Params, context?: HttpContext): Observable<void> {
    return this.deleteCompany$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `updateCompany()` */
  static readonly UpdateCompanyPath = '/companies';

  /**
   * Update company.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateCompany()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateCompany$Response(params: UpdateCompany$Params, context?: HttpContext): Observable<StrictHttpResponse<CompanyDto>> {
    return updateCompany(this.http, this.rootUrl, params, context);
  }

  /**
   * Update company.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateCompany$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateCompany(params: UpdateCompany$Params, context?: HttpContext): Observable<CompanyDto> {
    return this.updateCompany$Response(params, context).pipe(
      map((r: StrictHttpResponse<CompanyDto>): CompanyDto => r.body)
    );
  }

  /** Path part for operation `findByCriteria13()` */
  static readonly FindByCriteria13Path = '/companies/by-criteria/';

  /**
   * Find companies by criteria.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findByCriteria13()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  findByCriteria13$Response(params: FindByCriteria13$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<CompanyDto>>> {
    return findByCriteria13(this.http, this.rootUrl, params, context);
  }

  /**
   * Find companies by criteria.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findByCriteria13$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  findByCriteria13(params: FindByCriteria13$Params, context?: HttpContext): Observable<Array<CompanyDto>> {
    return this.findByCriteria13$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<CompanyDto>>): Array<CompanyDto> => r.body)
    );
  }

  /** Path part for operation `findCompanyById()` */
  static readonly FindCompanyByIdPath = '/companies/{id}';

  /**
   * Find company by id.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findCompanyById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findCompanyById$Response(params: FindCompanyById$Params, context?: HttpContext): Observable<StrictHttpResponse<CompanyDto>> {
    return findCompanyById(this.http, this.rootUrl, params, context);
  }

  /**
   * Find company by id.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findCompanyById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findCompanyById(params: FindCompanyById$Params, context?: HttpContext): Observable<CompanyDto> {
    return this.findCompanyById$Response(params, context).pipe(
      map((r: StrictHttpResponse<CompanyDto>): CompanyDto => r.body)
    );
  }

  /** Path part for operation `getCompanyIcon()` */
  static readonly GetCompanyIconPath = '/companies/{id}/icon';

  /**
   * Find company icon by id.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getCompanyIcon()` instead.
   *
   * This method doesn't expect any request body.
   */
  getCompanyIcon$Response(params: GetCompanyIcon$Params, context?: HttpContext): Observable<StrictHttpResponse<CompanyDto>> {
    return getCompanyIcon(this.http, this.rootUrl, params, context);
  }

  /**
   * Find company icon by id.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getCompanyIcon$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getCompanyIcon(params: GetCompanyIcon$Params, context?: HttpContext): Observable<CompanyDto> {
    return this.getCompanyIcon$Response(params, context).pipe(
      map((r: StrictHttpResponse<CompanyDto>): CompanyDto => r.body)
    );
  }

}
