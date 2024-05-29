/* tslint:disable */
/* eslint-disable */
import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiConfiguration, ApiConfigurationParams } from './api-configuration';

import { UserService } from './services/user.service';
import { LanguageService } from './services/language.service';
import { UserSkillService } from './services/user-skill.service';
import { SpecializationService } from './services/specialization.service';
import { SkillService } from './services/skill.service';
import { ReviewService } from './services/review.service';
import { ReferralService } from './services/referral.service';
import { NotificationService } from './services/notification.service';
import { JobHistoryService } from './services/job-history.service';
import { JobCandidatesService } from './services/job-candidates.service';
import { FacultyService } from './services/faculty.service';
import { ExperienceService } from './services/experience.service';
import { EventService } from './services/event.service';
import { EmailService } from './services/email.service';
import { BibliographyService } from './services/bibliography.service';
import { CourseService } from './services/course.service';
import { CompanyService } from './services/company.service';
import { SearchService } from './services/search.service';
import { AuthenticationService } from './services/authentication.service';

/**
 * Module that provides all services and configuration.
 */
@NgModule({
  imports: [],
  exports: [],
  declarations: [],
  providers: [
    UserService,
    LanguageService,
    UserSkillService,
    SpecializationService,
    SkillService,
    ReviewService,
    ReferralService,
    NotificationService,
    JobHistoryService,
    JobCandidatesService,
    FacultyService,
    ExperienceService,
    EventService,
    EmailService,
    BibliographyService,
    CourseService,
    CompanyService,
    SearchService,
    AuthenticationService,
    ApiConfiguration
  ],
})
export class ApiModule {
  static forRoot(params: ApiConfigurationParams): ModuleWithProviders<ApiModule> {
    return {
      ngModule: ApiModule,
      providers: [
        {
          provide: ApiConfiguration,
          useValue: params
        }
      ]
    }
  }

  constructor( 
    @Optional() @SkipSelf() parentModule: ApiModule,
    @Optional() http: HttpClient
  ) {
    if (parentModule) {
      throw new Error('ApiModule is already loaded. Import in your base AppModule only.');
    }
    if (!http) {
      throw new Error('You need to import the HttpClientModule in your AppModule! \n' +
      'See also https://github.com/angular/angular/issues/20575');
    }
  }
}
