import {Component, Inject, inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {CommonModule} from '@angular/common';
import {UserDto} from '../../../services/models';
import {UserService} from '../../../services/services';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-profile-open-dialog',
  standalone: true,
  imports: [MatDialogModule, MatButtonModule, MatInputModule, MatFormFieldModule, ReactiveFormsModule, MatDialogModule, MatAutocompleteModule, CommonModule],
  templateUrl: './profile-open-dialog.component.html',
  styleUrl: './profile-open-dialog.component.scss'
})
export class ProfileOpenDialogComponent {
  status: string[] = [''];
  dialogRef = inject(MatDialogRef);
  filteredStatusOptions: Observable<string[]> = new Observable();
  form: FormGroup;
  user: UserDto = {} as UserDto;
  selectedFile: File | null = null;

  ngOnInit() {
    this.filteredStatusOptions = this.form.get('status')!.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value, this.status))
    );
  }

  constructor(private userService: UserService, @Inject(MAT_DIALOG_DATA) public data: any) {
    console.log('Data', data);
    const fb = new FormBuilder();
    this.form = fb.group({
      name: [data.user.name, [Validators.required]],
      description: [data.user.description, [Validators.required]],
      phone: [data.user.phone, [Validators.required, Validators.pattern('^(\\+?[0-9]{1,5})?[0]?[7-9][0-9]{8}$'), Validators.maxLength(10)]],
      website: [data.user.website, [Validators.required, Validators.pattern(/^(https?:\/\/)?([\da-z.-]+)\.([a-z.]{2,6})([/\w .-]*)*\/?$/)]],
      status: [data.user.status, [Validators.required]],
      user: []
    });
  }

  private _filter(value: string, list: string[]): string[] {
    const filterValue = value.toLowerCase();
    return list
      .filter(option => option.toLowerCase().includes(filterValue))
      .sort((a, b) => a.localeCompare(b));
  }

  closeDialog() {
    this.dialogRef.close();
  }

  initializeEntity(): void {
    this.user = this.data.user;
    this.user.name = this.form.get('name')?.value;
    this.user.description = this.form.get('description')?.value;
    this.user.phone = this.form.get('phone')?.value;
    this.user.website = this.form.get('website')?.value;
    this.user.status = this.form.get('status')?.value;
  }

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
    }
  }

  onUpload(): void {
    console.log("upload");
    if (this.selectedFile && this.user.id) {
      const param = {
        id: this.user.id,
        body: {'multipartFile': this.selectedFile}
      }
      this.userService.saveProfilePhoto(param).subscribe({
        next: (response) => {
          console.log('Upload successful', response);
        },
        error: (err) => {
          console.error('Upload failed', err);
        }
      });
    } else {
      console.error('No file selected');
    }
  }

  onSubmit(): void {
    this.onUpload();
    if (this.form.valid) {
      this.initializeEntity();
      const params = {body: this.user};
      console.log('params', params);
      this.userService.updateUser(params).subscribe({
        next: (response: any) => {
          console.log('User updated successfully', response);
          this.form.get('user')?.setValue(response);
          this.dialogRef.close(this.form.value);
          Swal.fire({
            icon: "success",
            title: "Information was successfully saved",
            showConfirmButton: false,
            timer: 1500
          });
        },
        error: (error: any) => {
          console.error('Error updating user', error);
        }
      });
    }
  }
}
