import {Component, Inject, OnInit, inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule, provideNativeDateAdapter} from '@angular/material/core';
import {MatRadioModule} from '@angular/material/radio';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {CommonModule} from '@angular/common';
import {CompanyService, JobHistoryService} from '../../services/services';
import {CompanyDto, JobHistoryDto} from '../../services/models';
import {companyNameValidator} from './companyNameValidator';

@Component({
  selector: 'app-add-form-dialog',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  templateUrl: './add-form-dialog.component.html',
  styleUrl: './add-form-dialog.component.scss',
  imports: [
    MatDialogModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDialogModule,
    MatRadioModule,
    MatAutocompleteModule,
    CommonModule
  ]
})
export class AddFormDialogComponent implements OnInit {
  //constructor(public dialogRef: MatDialogRef<AddFormDialogComponent>) {};
  form: FormGroup;

  selectedCompany: CompanyDto | null = null;
  companyMap: Map<string, CompanyDto> = new Map();
  options: CompanyDto[] = [];
  error: any = null;
  filteredOptions: Observable<CompanyDto[]> = new Observable();
  dialogRef = inject(MatDialogRef);
  showNewOptionInput = false;

  constructor(private jobHistoryService: JobHistoryService, private companyService: CompanyService, @Inject(MAT_DIALOG_DATA) public data: any) {

    const fb = new FormBuilder();
    this.form = fb.group({
      position: [data.job.position, [Validators.required]],
      description: [data.job.description, [Validators.required]],
      dateRange: fb.group({
        start: [data.job.startDate, [Validators.required]],
        end: [data.job.endDate]
      }),
      needsQualification: fb.group({
        booleanValue: [data.job.needQualification]
      }),
      company: [data.job.company.name, Validators.required],
      // companyName: [''],
      // address: [''],
      website: [data.job.company.website],
    });
  }

  ngOnInit() {
    this.getCompanyList().subscribe(companies => {
      this.companyMap = new Map(companies.map(company => [company.name, company]));
      this.form.get('website')?.setValidators([Validators.required, companyNameValidator(this.companyMap)]);
      this.options = companies;
      this.filteredOptions = this.form.get('company')!.valueChanges.pipe(
        startWith(''),
        map(value => this._filter(value))
      );
      console.log('Companies list', this.options);
    });
    this.form.get('company')!.valueChanges.subscribe(value => {
      this.selectedCompany = this.options.find(option => option.name === value) ?? null;
    });
  }

  jobHistoryDto: JobHistoryDto = {} as JobHistoryDto;

  initializeJobHistoryDto(): void {
    this.jobHistoryDto.id = this.data.job.id;
    this.jobHistoryDto.user = this.data.user;
    this.jobHistoryDto.position = this.form.get('position')?.value;
    this.jobHistoryDto.description = this.form.get('description')?.value;
    this.jobHistoryDto.startDate = this.form.get('dateRange.start')?.value;
    this.jobHistoryDto.endDate = this.form.get('dateRange.end')?.value;
    this.jobHistoryDto.needQualification = this.form.get('needsQualification.booleanValue')?.value;
    if (this.showNewOptionInput) {
      this.jobHistoryDto.company.website = this.form.get('website')?.value;
    } else {
      const selectedCompanyName = this.form.get('company')?.value;
      const selectedCompany = this.companyMap.get(selectedCompanyName) ?? {} as CompanyDto;
      this.jobHistoryDto.company = selectedCompany;
    }
  }


  onSubmit(): void {
    if (this.form.valid) {
      this.initializeJobHistoryDto();
      const params = {body: this.jobHistoryDto};
      console.log('params', params);
      if (this.data.job.id === '' || this.data.job.id === undefined) {
        this.jobHistoryService.saveJobHistory(params).subscribe(({
          next: (response: any) => {
            console.log('Job added successfully', response);
            this.dialogRef.close(this.form.value);
          },
          error: (error: any) => {
            console.error('Error adding job', error);
          }
        }));
      } else {
        this.jobHistoryService.updateJobHistory(params).subscribe(({
          next: (response: any) => {
            console.log('Job updated successfully', response);
            this.dialogRef.close(this.form.value);
          },
          error: (error: any) => {
            console.error('Error updating job', error);
          }
        }));
      }
    }
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  private _filter(value: string): CompanyDto[] {
    const filterValue = value.toLowerCase();
    return this.options
      .filter(option => option.name.toLowerCase().includes(filterValue))
      .sort((a, b) => a.name.localeCompare(b.name));
  }

  toggleNewOptionInput(found: boolean): void {
    this.showNewOptionInput = !found;
    this.form.get('company')?.setValue('defaultValue');
    this.form.get('website')?.setValidators([Validators.required, Validators.pattern(/^(https?:\/\/)?([\da-z.-]+)\.([a-z.]{2,6})([/\w .-]*)*\/?$/)]);
  }


  private getCompanyList(): Observable<CompanyDto[]> {
    return this.companyService.findAllCompanies().pipe(
      map(companies => {
        console.log('Companies', companies);
        return companies;
      })
    );
  }

}
