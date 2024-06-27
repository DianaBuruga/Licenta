import {Injectable} from '@angular/core';
import {CookieService} from 'ngx-cookie-service';
import { AuthenticationService } from '../services';
import { IsOwner$Params } from '../fn/authentication/is-owner';
import { Observable } from 'rxjs';
import { Role } from '../models';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  private readonly tokenKey = 'token';
  private roles: string[] = [];

  constructor(private cookieService: CookieService, private authService: AuthenticationService) {
  }

  set token(token: string) {
    if (token && token !== "") {
      this.cookieService.set(this.tokenKey, token);
    }
  }

  get token(): string {
    return this.cookieService.get(this.tokenKey);
  }

  deleteToken(): void {
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

  userRoles(): Observable<Role> {
    return this.authService.getUserRole({});
  }

}
