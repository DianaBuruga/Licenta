import { isPlatformBrowser } from '@angular/common';
import { Injectable, PLATFORM_ID, inject } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  private readonly tokenKey = 'token';
  constructor(private cookieService: CookieService) {}
  
  set token(token: string) {  
    if (token && token !== "") {
      this.cookieService.set(this.tokenKey, token);
    }
  }

  get token(): string{
    return this.cookieService.get(this.tokenKey);
  }

  isTokenValid() {
    const token = this.token;
    if (!token) {
      return false;
    }
    return true;
  }

  isTokenNotValid() {
    return !this.isTokenValid();
  }

  get userRoles(): string[] {
    return [];
  }
}
