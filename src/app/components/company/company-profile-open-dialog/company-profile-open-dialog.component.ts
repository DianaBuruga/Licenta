import { Component, Inject, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { CommonModule } from '@angular/common';
import { CompanyDto } from '../../../services/models';
import { CompanyService } from '../../../services/services';
@Component({
  selector: 'app-company-profile-open-dialog',
  standalone: true,
  imports: [MatDialogModule, MatButtonModule, MatInputModule, MatFormFieldModule, ReactiveFormsModule, MatDialogModule, MatAutocompleteModule, CommonModule],
  templateUrl: './company-profile-open-dialog.component.html',
  styleUrl: './company-profile-open-dialog.component.scss'
})
export class CompanyProfileOpenDialogComponent {
  dialogRef = inject(MatDialogRef);
  form: FormGroup;
  company: CompanyDto = {} as CompanyDto;

  constructor(private companyService: CompanyService, @Inject(MAT_DIALOG_DATA) public data: any) {
    console.log('Data', data);
    const fb = new FormBuilder();
    this.form = fb.group({
      name: [data.company.name, [Validators.required]],
      description: [data.company.description, [Validators.required]],
      address: [data.company.address, [Validators.required]],
      website: [data.company.website, [Validators.required, Validators.pattern(/^(https?:\/\/)?([\da-z.-]+)\.([a-z.]{2,6})([/\w .-]*)*\/?$/)]],
      company: []
    });
  }

  closeDialog() {
    this.dialogRef.close();
  }

  initializeEntity(): void {
    this.company = this.data.company;
    this.company.name = this.form.get('name')?.value;
    this.company.description = this.form.get('description')?.value;
    this.company.address = this.form.get('address')?.value;
    this.company.website = this.form.get('website')?.value;
  }

  onSubmit(): void {
    if (this.form.valid) {
      this.initializeEntity();
      const params = { body: this.company };
      console.log('params', params);
      this.companyService.updateCompany(params).subscribe({
        next: (response: any) => {
          console.log(' Company updated successfully', response);
          this.form.get('company')?.setValue(response);
          this.dialogRef.close(this.form.value);
        },
        error: (error: any) => {
          console.error('Error adding company', error);
        }
      });
    }
  }
}
