import { Injectable, inject } from '@angular/core';
import { AuthConfig, OAuthService} from 'angular-oauth2-oidc';
//import { Router } from '@angular/router';
import { environment } from '../../../enviroment/enviroment';
//import { HttpClient } from '@angular/common/http';
// import { OAuthService } from 'angular-oauth2-oidc/oauth-service';
// import { AuthConfig } from 'angular-oauth2-oidc';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  //isLogged: boolean = false;

//   constructor(private httpClient: HttpClient) { }
  private oauthService = inject(OAuthService);
//   private router = inject(Router);

  constructor() {
    this.initConfiguration();
  }

  initConfiguration() {
  const oauthConfig: AuthConfig = {
    issuer: 'https://accounts.google.com',
    //issuer: environment.oauth2AuthorizationUrl,
    redirectUri: 'http://localhost:8081/login/oauth2/code/google',
    clientId: '163665588524-9ohb7pg3mkglqrtjnqj71i3omgaten8d.apps.googleusercontent.com', 
    strictDiscoveryDocumentValidation: false,
    scope: 'openid profile email',  
    showDebugInformation: true, 
    useSilentRefresh: true,
    responseType: 'code',
    customQueryParams: {
      prompt: 'select_account'
    }
  };
  this.oauthService.configure(oauthConfig);
}


public login(): void {
  this.oauthService.loadDiscoveryDocumentAndLogin();
  this.oauthService.initImplicitFlow();
}

  public logout(): void {
    this.oauthService.revokeTokenAndLogout();
    this.oauthService.logOut();
  }

  public getToken() {
    return this.oauthService.getAccessToken();
  }

  public isLoggedIn(): boolean {
    return this.oauthService.hasValidAccessToken();
  }
// public login(): void {
// window.location.href = environment.oauth2AuthorizationUrl;
// }

// public isLoggedIn(): boolean {
//   return this.httpClient.get('http://localhost:8081/isLoggedIn') as unknown as boolean;
// }
// public logout(): void {
//   this.httpClient.get('http://localhost:8081/logout');
//}
}