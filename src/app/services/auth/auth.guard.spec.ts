import { TestBed } from '@angular/core/testing';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { AuthService } from './auth.service';

describe('AuthGuard', () => {
  let guard: AuthGuard;
  let authService: jasmine.SpyObj<AuthService>;
  let router: jasmine.SpyObj<Router>;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let mockRouterStateSnapshot: jasmine.SpyObj<RouterStateSnapshot>;

  beforeEach(() => {
    // Create spies for the dependencies
    const authServiceSpy = jasmine.createSpyObj('AuthService', ['isLoggedIn']);
    const routerSpy = jasmine.createSpyObj('Router', ['navigate']);
    mockRouterStateSnapshot = jasmine.createSpyObj('RouterStateSnapshot', ['toString']);

    TestBed.configureTestingModule({
      // Provide both the guard and the spied services
      providers: [
        AuthGuard,
        { provide: AuthService, useValue: authServiceSpy },
        { provide: Router, useValue: routerSpy }
      ]
    });

    // Inject both the service and its dependencies
    guard = TestBed.inject(AuthGuard);
    authService = TestBed.inject(AuthService as any);
    router = TestBed.inject(Router as any);
    mockActivatedRouteSnapshot = new ActivatedRouteSnapshot();  // Mock instance
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  it('should return true for canActivate when user is logged in', () => {
    authService.isLoggedIn.and.returnValue(true);
    expect(guard.canActivate(mockActivatedRouteSnapshot, mockRouterStateSnapshot as any)).toBeTrue();
  });

  it('should navigate to login when user is not logged in', () => {
    authService.isLoggedIn.and.returnValue(false);
    expect(guard.canActivate(mockActivatedRouteSnapshot, mockRouterStateSnapshot as any)).toBeFalse();
    expect(router.navigate).toHaveBeenCalledWith(['/login']);
  });
});
