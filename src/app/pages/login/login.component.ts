import {ChangeDetectorRef, Component} from '@angular/core';
import {MatButton} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {AuthenticationService} from '../../services/services/authentication.service';

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
  constructor(private authService: AuthenticationService, private cdr: ChangeDetectorRef) {
  }

  gray: string = 'gray';
  white: string = 'white';
  url: string = '';

  ngOnInit(): void {
    this.authService.auth().subscribe((response) => {
      console.log(response);
      this.url = response.authURL ?? '';
    });
    this.cdr.detectChanges();
  }

  openAuthUrl(): void {
    console.log("Ai apasat butonul");
    if (this.url) {
      window.location.href = this.url;
    }
  }
}
