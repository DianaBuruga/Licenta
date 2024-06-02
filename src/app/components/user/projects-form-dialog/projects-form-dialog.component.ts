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
import { Observable, map, startWith } from 'rxjs';
import { ExperienceService } from '../../../services/services';
import { ExperienceDto } from '../../../services/models';

@Component({
  selector: 'app-projects-form-dialog',
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
  providers: [provideNativeDateAdapter()],
  templateUrl: './projects-form-dialog.component.html',
  styleUrl: './projects-form-dialog.component.scss'
})
export class ProjectsFormDialogComponent {
  form: FormGroup;

  options: string[] = ['COMPETITION', 'PROJECT'];
  error: any = null;
  filteredOptions: Observable<string[]> = new Observable();
  dialogRef = inject(MatDialogRef);
  constructor(private experienceService: ExperienceService, @Inject(MAT_DIALOG_DATA) public data: any) {
    const fb = new FormBuilder();
    this.form = fb.group({
      title: [data.newExperience.title, [Validators.required]],
      description: [data.newExperience.description, [Validators.required]],
      start: [data.newExperience.date, [Validators.required]],
      website: [data.newExperience.url, [Validators.required, Validators.pattern(/^(https?:\/\/)?([\da-z.-]+)\.([a-z.]{2,6})([/\w .-]*)*\/?$/)]],
      type: [data.newExperience.type, Validators.required],
      experience: []
    });
    this.filteredOptions = this.form.get('type')!.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
  }

  experience: ExperienceDto = {} as ExperienceDto;
  initializeExperienceDto(): void {
    this.experience.id = this.data.newExperience.id;
    this.experience.userDTO = this.data.user;
    this.experience.title = this.form.get('title')?.value;
    this.experience.description = this.form.get('description')?.value;
    this.experience.type = this.form.get('type')?.value;
    this.experience.date = this.form.get('start')?.value;
    this.experience.url = this.form.get('website')?.value;
  }


  onSubmit(): void {
    if (this.form.valid) {
      this.initializeExperienceDto();
      const params = { body: this.experience };
      console.log('params', params);
      if (this.data.newExperience.id === '' || this.data.newExperience.id === undefined) {
        this.experienceService.saveExperience(params).subscribe(({
          next: (response: any) => {
            console.log('Experience added successfully', response);
            this.form.get('experience')?.setValue(response);
            this.dialogRef.close(this.form.value);
          },
          error: (error: any) => {
            console.error('Error adding experience', error);
          }
        }));
      } else {
        this.experienceService.updateExperience(params).subscribe(({
          next: (response: any) => {
            console.log('Experience updated successfully', response);
            this.form.get('experience')?.setValue(response);
            this.dialogRef.close(this.form.value);
          },
          error: (error: any) => {
            console.error('Error updating Experience', error);
          }
        }));
      }
    }
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.options
      .filter(option => option.toLowerCase().includes(filterValue))
      .sort((a, b) => a.localeCompare(b));
  }
}
