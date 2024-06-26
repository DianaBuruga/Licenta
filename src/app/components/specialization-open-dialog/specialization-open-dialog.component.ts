import {CommonModule} from '@angular/common';
import {Component, Inject, inject} from '@angular/core';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatNativeDateModule, provideNativeDateAdapter} from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {SearchService, SpecializationService} from '../../services/services';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {startWith, map, Observable} from 'rxjs';
import {FacultyDto, SpecializationDto} from '../../services/models';
import {MatSelectModule} from '@angular/material/select';
import {MatButtonModule} from '@angular/material/button';

@Component({
  selector: 'app-specialization-open-dialog',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDialogModule,
    MatAutocompleteModule,
    ReactiveFormsModule,
    CommonModule,
    MatSelectModule,
    MatButtonModule
  ],
  providers: [provideNativeDateAdapter()],
  templateUrl: './specialization-open-dialog.component.html',
  styleUrl: './specialization-open-dialog.component.scss'
})
export class SpecializationOpenDialogComponent {
  form: FormGroup;
  specializations: string[] = [];
  facultyOptions: string[] = [];
  error: any = null;
  filteredFacultyOptions: Observable<string[]> = new Observable();
  specializationFilterOptions: Observable<string[]> = new Observable();
  dialogRef = inject(MatDialogRef);

  createSpecializationDto(): SpecializationDto {
    return {
      id: this.data?.specialization?.id ?? '',
      name: this.form.get('name')?.value,
      degree: this.form.get('degree')?.value,
      startedDate: this.form.get('startedDate')?.value,
      finishedDate: this.form.get('finishedDate')?.value,
      faculty: {
        name: this.form.get('faculty')?.value
      },
      user: this.data?.user
    } as SpecializationDto;
  }

  onSubmit(): void {
    if (this.form.valid) {
      const params = {body: this.createSpecializationDto()};
      console.log('params', params);
      if (this.data.specialization.id === '' || this.data.specialization.id === undefined) {
        this.specializationService.saveSpecialization(params).subscribe(({
          next: (response: any) => {
            console.log('Specialization added successfully', response);
            response.user = this.data.user;
            this.form.get('specialization')?.setValue(response);
            this.dialogRef.close(this.form.value);
          }, error: (error: any) => {
            console.error('Error adding specialization', error);
          }
        }));
      } else {
        this.specializationService.updateSpecialization(params).subscribe(({
          next: (response: any) => {
            console.log('Specialization updated successfully', response);
            response.user = this.data.user;
            this.form.get('specializazation')?.setValue(response);
            this.dialogRef.close(this.form.value);
          }, error: (error: any) => {
            console.error('Error updating specialization', error);
          }
        }));
      }
    }
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  constructor(private specializationService: SpecializationService, @Inject(MAT_DIALOG_DATA) public data: any, private searchService: SearchService) {
    const fb = new FormBuilder();
    this.form = fb.group({
      name: [data?.specialization?.name ?? '', [Validators.required]],
      degree: [data?.specialization?.degree ?? '', [Validators.required]],
      startedDate: [this.convertDate(data?.specialization?.startedDate) ?? '', [Validators.required]],
      finishedDate: [this.convertDate(data?.specialization?.finishedDate) ?? '', [Validators.required]],
      faculty: [data?.specialization?.faculty?.name ?? '', [Validators.required]],
    });


    this.getFacultyList().subscribe(faculties => {
      this.facultyOptions = faculties.map(faculty => faculty.name);
      this.filteredFacultyOptions = this.form.get('faculty')!.valueChanges.pipe(startWith(''), map(value => this.facultyFilter(value)));
      console.log('Faculties list', this.facultyOptions);
    });

    this.getSpecilizations().subscribe(specializations => {
      this.specializations = specializations.map(specialization => specialization.name);
      this.specializationFilterOptions = this.form.get('name')!.valueChanges.pipe(startWith(''), map(value => this.specializationFilter(value)));
      console.log('Specializations list', this.specializations);
    });
  }

  private getSpecilizations(): Observable<SpecializationDto[]> {
    const param = {
      endpoint: "specializations",
      criteria: {
        "faculty.name": this.form.get('faculty')?.value
      }
    };
    return this.searchService.search(param).pipe(map(specializations => {
      console.log('Specializations', specializations);
      return specializations;
    }));
  }

  private specializationFilter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.specializations
      .filter(option => option.toLowerCase().includes(filterValue))
      .sort((a, b) => a.localeCompare(b));
  }

  private getFacultyList(): Observable<FacultyDto[]> {
    const param = {
      endpoint: "faculties",
      criteria: {}
    };
    return this.searchService.search(param).pipe(map(faculties => {
      console.log('Faculties', faculties);
      return faculties;
    }));
  }

  private facultyFilter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.facultyOptions
      .filter(option => option.toLowerCase().includes(filterValue))
      .sort((a, b) => a.localeCompare(b));
  }

  private convertDate(dateString: string): Date | null {
    if (!dateString) {
      return null;
    }

    const [day, month, year] = dateString.split('.').map(part => parseInt(part, 10));
    if (isNaN(day) || isNaN(month) || isNaN(year)) {
      return null;
    }

    return new Date(year, month - 1, day);
  }
}
