import {ChangeDetectorRef, Component} from '@angular/core';
import {MatButton} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {AuthenticationService} from '../../services/services/authentication.service';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';
import { MessageService } from '../../services/services/message.service';

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
  constructor(private authService: AuthenticationService, private cdr: ChangeDetectorRef, private _snackBar: MatSnackBar, private messageService: MessageService) {
  }

  openSnackBar(message: string) {
    const snackBarRef = this._snackBar.open(message, 'OK', {
      horizontalPosition: 'center',
      verticalPosition: 'top',
    });

    snackBarRef.afterDismissed().subscribe(() => {
      this.messageService.clearMessage();
    });

    snackBarRef.onAction().subscribe(() => {
      this.messageService.clearMessage();
    });
  }

  gray: string = 'gray';
  white: string = 'white';
  url: string = '';

  ngOnInit(): void {
    const message = this.messageService.getMessage();
    console.log(message);
    if (message) {
      this.openSnackBar(message);
    }
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
