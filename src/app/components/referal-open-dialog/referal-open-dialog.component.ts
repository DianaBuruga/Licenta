import { CommonModule } from '@angular/common';
import { Component, Inject, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule, provideNativeDateAdapter } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ReferralService } from '../../services/services';
import { ReferralDto } from '../../services/models';

@Component({
  selector: 'app-referal-open-dialog',
  standalone: true,
  imports: [
    MatDialogModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDialogModule,
    MatAutocompleteModule,
    CommonModule
  ],
  templateUrl: './referal-open-dialog.component.html',
  styleUrl: './referal-open-dialog.component.scss'
})
export class ReferalOpenDialogComponent {
  form: FormGroup;
  error: any = null; 
  dialogRef = inject(MatDialogRef);
  constructor(private referralService: ReferralService, @Inject(MAT_DIALOG_DATA) public data: any) {
    console.log('Data Constr', data.user);
    console.log('Data Constr', data.referral);
    const fb = new FormBuilder();
    this.form = fb.group({
      title: [data.referral.title, [Validators.required]],
      description: [data.referral.description, [Validators.required]],
      referral : []
    });
  }

  referral: ReferralDto = {} as ReferralDto;
  initializeExperienceDto(): void {
    console.log('Data', this.data.user);
    this.referral = this.data.referral;
    this.referral.teacher = this.data.user;
    this.referral.student = this.data.user;
    this.referral.description = this.form.get('description')?.value;
    this.referral.title = this.form.get('title')?.value;
  }
  

  onSubmit(): void {
    if (this.form.valid) {
      this.initializeExperienceDto();
      const params = { body: this.referral };
      console.log('params', params);
      if(this.data.referral.id === '' || this.data.referral.id === undefined) {
        this.referralService.saveReferral(params).subscribe(({
          next: (response: any) => {
            console.log('Referral added successfully', response);
            this.dialogRef.close(this.form.value);
            response.user = this.data.user;
            this.form.get('referral')?.setValue(response);
          },
          error: (error: any) => {
            console.error('Error adding experience', error);
          }
        }));
      }else{
        this.referralService.updateReferral(params).subscribe(({
          next: (response: any) => {
            console.log('Referral updated successfully', response);
            this.dialogRef.close(this.form.value);
            response.user = this.data.user;
            this.form.get('referral')?.setValue(response);
          },
          error: (error: any) => {
            console.error('Error updating referral', error);
          }
        }));
      }
    }
  }

  closeDialog(): void {
    this.dialogRef.close();
  }
}
