import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { provideOAuthClient } from 'angular-oauth2-oidc';
import { authInterceptor } from './services/auth/auth.interceptor';
import { CookieService } from 'ngx-cookie-service';
import { TokenService } from './services/auth/token.service';

export const appConfig: ApplicationConfig = {
  providers: [CookieService, TokenService, provideRouter(routes), provideClientHydration(), provideAnimationsAsync(),  provideHttpClient(withFetch(), withInterceptors([authInterceptor])), provideOAuthClient(), 
  ]
};
