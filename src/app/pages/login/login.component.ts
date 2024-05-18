import { Component, inject } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { environment } from '../../../enviroment/enviroment';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MatButton,
    MatIconModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  private authService: AuthService = inject(AuthService);

  gray: String = 'gray';
  white: String = 'white';

  authenticateWithGoogle(): void {
    window.location.href = 'http://localhost:8081/oauth2/authorization/google';
    //this.authService.login();
  }
}
