import { Component, inject } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { AuthenticationService } from '../../services/services/authentication.service';

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
  private authService: AuthenticationService = inject(AuthenticationService);

  gray: string = 'gray';
  white: string = 'white';
  url: string = '' ;
  // get authenticateWithGoogle(): string {
    //window.location.href = 'http://localhost:8081/oauth2/authorization/google';
    // this.authService.login();
    // this.authService.login().subscribe((response) => {
    //   console.log('Login response:', response);
    // });
 ngOnInit(): void {
       this.authService.auth().subscribe((response) => {console.log(response);this.url = response.authURL??'';});
  }
}
