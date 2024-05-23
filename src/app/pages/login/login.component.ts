import { Component, inject } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { AuthenticationControllerService } from '../../services/services/authentication-controller.service';

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
  private authService: AuthenticationControllerService = inject(AuthenticationControllerService);

  gray: string = 'gray';
  white: string = 'white';

  authenticateWithGoogle(): void {
    //window.location.href = 'http://localhost:8081/oauth2/authorization/google';
    //this.authService.login();
    // this.authService.login().subscribe((response) => {
    //   console.log('Login response:', response);
    // });
    window.location.href ="http://localhost:8081/oauth2/authorization/google";
  }
}
