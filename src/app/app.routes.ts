import {Routes} from '@angular/router';
import {canActivate} from './services/auth/auth.guard';

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
    loadComponent: () => import('./pages/company/company.component').then(m => m.CompanyComponent),
    canActivate: [canActivate], 
    data: { requiredRole: ['STUDENT', 'COMPANY_REPRESENTATIVE', 'ADMIN', 'TEACHER']}
  }
  ,
  {
    path: 'user/:id',
    loadComponent: () => import('./pages/user/user.component').then(m => m.UserComponent),
    canActivate: [canActivate], 
    data: { requiredRole: ['STUDENT', 'COMPANY_REPRESENTATIVE', 'ADMIN', 'TEACHER']}
  }
  ,
  {
    path: 'users',
    loadComponent: () => import('./pages/users/users.component').then(m => m.UsersComponent),
    canActivate: [canActivate], 
    data: { requiredRole: ['STUDENT', 'COMPANY_REPRESENTATIVE', 'ADMIN', 'TEACHER']}
  }
  ,
  {
    path: 'companies',
    loadComponent: () => import('./pages/companies/companies.component').then(m => m.CompaniesComponent),
    canActivate: [canActivate], 
    data: { requiredRole: ['STUDENT', 'COMPANY_REPRESENTATIVE', 'ADMIN', 'TEACHER']}
  }
  ,
  {
    path: 'jobs',
    loadComponent: () => import('./pages/jobs/jobs.component').then(m => m.JobsComponent),
    canActivate: [canActivate], 
    data: { requiredRole: ['STUDENT', 'COMPANY_REPRESENTATIVE', 'ADMIN', 'TEACHER']}
  },
  {
    path: 'job/:id',
    loadComponent: () => import('./pages/job/job.component').then(m => m.JobComponent),
    canActivate: [canActivate], 
    data: { requiredRole: ['STUDENT', 'COMPANY_REPRESENTATIVE', 'ADMIN', 'TEACHER']}
  },
  {
    path: 'bibliographies',
    loadComponent: () => import('./pages/bibliography/bibliography.component').then(m => m.BibliographyComponent),
    canActivate: [canActivate], 
    data: { requiredRole: ['STUDENT', 'COMPANY_REPRESENTATIVE', 'ADMIN', 'TEACHER']}
  }
];
