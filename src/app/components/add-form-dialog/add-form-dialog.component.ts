import { Component, Inject, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators, FormControl } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule, provideNativeDateAdapter} from '@angular/material/core'; 
import {MatRadioModule} from '@angular/material/radio';
import { Observable, Observer } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { CommonModule } from '@angular/common';
import { AddCompanyFormComponent } from "../add-company-form/add-company-form.component";
import { CompanyService, JobHistoryService, UserService } from '../../services/services';
import { CompanyDto, JobHistoryDto, UserDto } from '../../services/models';

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
        CommonModule,
        AddCompanyFormComponent
    ]
})
export class AddFormDialogComponent{
  //constructor(public dialogRef: MatDialogRef<AddFormDialogComponent>) {};
  form: FormGroup;
  
  options: string[];
  companies: CompanyDto={} as CompanyDto;
  error: any = null;
  filteredOptions: Observable<string[]>;
  dialogRef = inject(MatDialogRef);
  showNewOptionInput = false;
  constructor(private jobHistoryService: JobHistoryService, private companyService: CompanyService) {
    this.getCompanyList();
    this.options = [this.companies.name];
    const fb = new FormBuilder();
    this.form = fb.group({
      position: ['', [Validators.required]],
      description: ['', [Validators.required]],
      dateRange: fb.group({
        start: ['', [Validators.required]],
        end: ['']
      }),
      needsQualification: fb.group({
        booleanValue: [false]
      }),
      company: ['', Validators.required],
      companyName: [''],
      address: [''],
      website: [''],
    });
    this.filteredOptions = (this.form.get('company') as FormControl).valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
  }

  jobHistoryDto: JobHistoryDto = {} as JobHistoryDto;
  initializeJobHistoryDto(): void {
    this.jobHistoryDto.position = this.form.get('position')?.value;
    this.jobHistoryDto.description = this.form.get('description')?.value;
    this.jobHistoryDto.startDate = this.form.get('dateRange.start')?.value;
    this.jobHistoryDto.endDate = this.form.get('dateRange.end')?.value;
    this.jobHistoryDto.needQualification = this.form.get('needsQualification.booleanValue')?.value;
    const company = {} as CompanyDto;
    if(this.showNewOptionInput){
      company.name = this.form.get('companyName')?.value;
      company.address = this.form.get('address')?.value;
      company.website = this.form.get('website')?.value;

    }else{
      company.name = this.form.get('company')?.value;
    }
    this.jobHistoryDto.company = company;
    //this.jobHistoryDto.user = this.userDto;
  }
  

  onSubmit(): void {
    if (this.form.valid) {
      this.initializeJobHistoryDto();
      const params = { body: this.jobHistoryDto };
      console.log('params', params);
      this.jobHistoryService.saveJobHistory(params).subscribe(({
        next: (response) => {
          console.log('Job added successfully', response);
          this.dialogRef.close(this.form.value);
        },
        error: (error) => {
          console.error('Error adding job', error);
        }
      }));
    }
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.options.filter(option =>
      option.toLowerCase().includes(filterValue)
    ).sort((a,b) => a.localeCompare(b));
  }

  toggleNewOptionInput(found: boolean): void {
    this.showNewOptionInput = !found;
    this.form.get('company')?.setValue('defaultValue');
    this.form.get('companyName')?.setValidators(Validators.required);
    this.form.get('address')?.setValidators(Validators.required);
    this.form.get('website')?.setValidators([Validators.required, Validators.pattern(/^(https?:\/\/)?([\da-z.-]+)\.([a-z.]{2,6})([/\w .-]*)*\/?$/)]);
  }

  
  private getCompanyList(): void {
    const observer: Observer<CompanyDto> = { 
      next: (companies: CompanyDto) => {
        this.companies = companies;
        console.log('Companies', companies);
      },
      error: (error: any) => {
        this.error = error;
        console.error('Error fetching companies:', error);
      },
      complete: () => {
        console.log('Completed fetching companies');
      }
    };

    this.companyService.findAllCompanies().subscribe(observer);
  }
}
