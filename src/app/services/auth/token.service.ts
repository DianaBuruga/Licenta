import {Injectable} from '@angular/core';
import {CookieService} from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  private readonly tokenKey = 'token';
  private roles: string[] = [];

  constructor(private cookieService: CookieService) {
  }

  set token(token: string) {
    if (token && token !== "") {
      this.cookieService.set(this.tokenKey, token);
    }
  }

  get token(): string {
    return this.cookieService.get(this.tokenKey);
  }

  deleteToken(token: string): void {
    this.cookieService.delete(this.tokenKey);
  }

  refreshToken(): string {
    return this.token;
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

  get hasRole(role:string): boolean {
    return false;
  }
}
