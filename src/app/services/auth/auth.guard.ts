import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { TokenService } from './token.service';
import { AuthenticationService } from '../services';
import { map } from 'rxjs';

export const canActivate: CanActivateFn = (route, state) => {
    const tokenService = inject(TokenService);
    const authService = inject(AuthenticationService);
    const router = inject(Router);
    if (tokenService.isTokenNotValid()) {
      router.navigate(['login']);
      return false;
    }
    
    const roles: string[] = ['STUDENT', 'COMPANY_REPRESENTATIVE', 'ADMIN', 'TEACHER'];
    return authService.getUserRole().pipe(
      map(userRole => {
        console.log(roles);
        if(userRole.role){
        if (roles.includes(userRole.role)) {
          return true;
        }}

          router.navigate(['login']);
          return false;
      })
    );
  }
