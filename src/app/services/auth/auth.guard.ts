import { CanActivateFn, Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard {
  constructor(private router: Router, private tokenService: TokenService) {}

  canActivate: CanActivateFn = (route, state) => {
    if (this.tokenService.isTokenNotValid()) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
    }
}
