import { Routes } from '@angular/router';
import { AuthGuard } from './services/auth/auth.guard';

export const routes: Routes = [
{
    path: 'profile',
    loadComponent: () => import('./pages/profile/profile.component').then(m => m.ProfileComponent),
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
},
{
    path: 'company/:id',
    loadComponent: () => import('./pages/company/company.component').then(m => m.CompanyComponent)
}
,
{
    path: 'users',
    loadComponent: () => import('./pages/users/users.component').then(m => m.UsersComponent)
}
,
{
    path: 'companies',
    loadComponent: () => import('./pages/companies/companies.component').then(m => m.CompaniesComponent)
}
];
