import { Component } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { SearchBarComponent } from '../search-bar/search-bar.component';
import { MatMenuModule } from '@angular/material/menu';
import { Router, RouterModule } from '@angular/router'; // Necessary for using routerLink
import { AuthenticationService } from '../../services/services';
import { BehaviorSubject } from 'rxjs';
import { Role } from '../../services/models';
import { CommonModule } from '@angular/common';
import { TokenService } from '../../services/auth/token.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
  standalone: true,
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    SearchBarComponent,
    MatMenuModule,
    CommonModule,
    RouterModule
  ]
})
export class NavbarComponent {
  onFocus(): void {
  }
  isVisible: boolean = false;
  performSearch(value: string): void {
    console.log('Searching for:', value);
  }
 constructor(public tokenService:TokenService, private router: Router){
 }
 logout() {
  this.tokenService.deleteToken();
  console.log("Token=", this.tokenService.token);

  this.router.navigate(['/login']);
}
}
