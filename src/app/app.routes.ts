import { Routes } from '@angular/router';
//import { AuthGuard } from './services/auth/auth.guard';

export const routes: Routes = [
{
    path: 'profile',
    loadComponent: () => import('./pages/profile/profile.component').then(m => m.ProfileComponent),
    //canActivate: [AuthGuard]
},
{
    path: '',
    loadComponent: () => import('./pages/login/login.component').then(m => m.LoginComponent)
},
{
    path: 'login',
    loadComponent: () => import('./pages/login/login.component').then(m => m.LoginComponent)
},
{
    path: 'v1/callback',
    loadComponent: () => import('./pages/callback/callback/callback.component').then(m => m.CallbackComponent)
}
];
