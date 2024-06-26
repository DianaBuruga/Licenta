import {CUSTOM_ELEMENTS_SCHEMA, Component, Input, SimpleChanges} from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {UserDto} from '../../../services/models/user-dto';
import {OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {UserService} from '../../../services/services';
import {ProfileOpenDialogComponent} from '../profile-open-dialog/profile-open-dialog.component';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {CommonModule} from '@angular/common';
import "@uploadcare/blocks/web/lr-file-uploader-regular.min.css"
import {SaveProfilePhoto$Params} from '../../../services/fn/user/save-profile-photo';


@Component({
  selector: 'app-profile-card',
  standalone: true,
  templateUrl: './profile-card.component.html',
  styleUrls: ['./profile-card.component.scss'],
  imports: [
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    CommonModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProfileCardComponent implements OnInit {
  profilePhotoUrl: string | undefined;

  refreshProfilePhoto() {
    const url = 'http://localhost:8081/users/profilePhoto/view/' + this.profileCardUser.id + '?timestamp=' + new Date().getTime();
    this.profilePhotoUrl = url;
  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      this.uploadFile(file);
    }
  }

  uploadFile(file: File) {
    console.log('File', file);
    const param = {
      id: this.profileCardUser.id,
      body: {
        'multipartFile': file
      }
    } as SaveProfilePhoto$Params;

    if (this.profileCardUser.id) {
      this.userService.saveProfilePhoto(param).subscribe({
        next: (response: UserDto) => {
          console.log('Successful response:', response);
          this.refreshProfilePhoto();
        },
        error: error => {
          console.error('Failed to navigate:', error);
        }
      });
    }
  }

  generateCV(user: UserDto) {
    if (user.id) {
      const param = {id: user.id};
      this.userService.exportUserPdf(param).subscribe({
        next: (response: Blob) => {
          console.log('Successful response:', response);
          const blobUrl = URL.createObjectURL(response);
          window.open(blobUrl, '_blank');
        },
        error: error => {
          console.error('Failed to navigate:', error);
        }
      });
    }
  }

  @Input() profileCardUser: UserDto = {} as UserDto;

  ngOnInit(): void {
    console.log('ProfileCardUser2', this.profileCardUser);
  }

  ngOnChanges(changes: SimpleChanges) {
    if (this.profileCardUser.id) {
      this.refreshProfilePhoto();
    }
  }

  constructor(public dialog: MatDialog, private userService: UserService) {
  }

  openDialog(user: UserDto): void {
    const dialogRef = this.dialog.open(ProfileOpenDialogComponent, {
      width: '50%',
      data: {user: this.profileCardUser}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Result', result);
      if (result.user !== undefined) {
        this.profileCardUser = result.user;
        console.log('User', this.profileCardUser);
      }
      console.log('Dialog was closed');
    });
  }
}
