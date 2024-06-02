import { CUSTOM_ELEMENTS_SCHEMA, Component, ElementRef, Inject, ViewChild, inject } from '@angular/core';
import { CompanyDto, PostedJobDto, ReviewDto, SkillDto } from '../../../services/models';
import { FormsModule, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable, map, startWith } from 'rxjs';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { PostedJobService, ReviewService, SkillService } from '../../../services/services';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatAutocompleteModule, MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { CommonModule } from '@angular/common';
import { MatNativeDateModule, provideNativeDateAdapter } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatChipInputEvent, MatChipsModule } from '@angular/material/chips';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import { AsyncPipe } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-posted-job-open-dialog',
  standalone: true,
  imports: [
    MatDialogModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatAutocompleteModule,
    CommonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatChipsModule,
    AsyncPipe,
    FormsModule,
    MatIconModule
  ],
  providers: [provideNativeDateAdapter()],
  templateUrl: './posted-job-open-dialog.component.html',
  styleUrl: './posted-job-open-dialog.component.scss'
})
export class PostedJobOpenDialogComponent {
  form: FormGroup;
  job: PostedJobDto = {} as PostedJobDto;
  options: string[] = ['REMOTE', 'ONSITE', 'HYBRID'];
  skillOptions: SkillDto[] = [];
  error: any = null;
  filteredOptions: Observable<string[]> = new Observable();
  skillMap: Map<string, SkillDto> = new Map();
  skillFilteredOptions: Observable<SkillDto[]> = new Observable();
  dialogRef = inject(MatDialogRef);
  separatorKeysCodes: number[] = [ENTER, COMMA];
  skills: SkillDto[] = [];

  @ViewChild('skillInput') skillInput!: ElementRef<HTMLInputElement>;
  announcer = inject(LiveAnnouncer);

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();
    if (value) {
      if (!this.skills.includes(this.skillMap.get(value) as SkillDto)) {
        this.skills.push(this.skillMap.get(value) as SkillDto ?? { name: value } as SkillDto);
      }
    }
    event.chipInput!.clear();
    this.form.get('skill')?.setValue(null);
  }

  remove(skill: SkillDto): void {
    const index = this.skills.indexOf(skill);

    if (index >= 0) {
      this.skills.splice(index, 1);
      this.announcer.announce(`Removed ${skill.name}`);
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    const value = event.option.viewValue;
    if (!this.skills.includes(this.skillMap.get(value) as SkillDto)) {
      this.skills.push(this.skillMap.get(value) as SkillDto ?? { name: value } as SkillDto);
    }
    this.skillInput.nativeElement.value = '';
    this.form.get('skill')?.setValue('');
  }

  constructor(private postedJobService: PostedJobService, @Inject(MAT_DIALOG_DATA) public data: any, private skillService: SkillService) {
    const fb = new FormBuilder();
    this.skills = data.job.skills ?? [];
    this.form = fb.group({
      position: [data.job.position, [Validators.required]],
      description: [data.job.description, [Validators.required]],
      location: [data.job.location, [Validators.required]],
      openUntil: [data.job.openUntil, [Validators.required]],
      type: [data.job.type, [Validators.required]],
      skill: ['', []],
      job: []
    });
    this.filteredOptions = this.form.get('type')!.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
    this.getSkillList().subscribe(skills => {
      this.skillMap = new Map(skills.map(skill => [skill.name, skill]));
      console.log('Skill Map', this.skillMap);
      this.skillOptions = skills;
      this.skillFilteredOptions = this.form.get('skill')!.valueChanges.pipe(
        startWith(''),
        map(value => this._skillFilter(value))
      );
      console.log('Skill list', this.skillOptions);
    });
  }

  private _skillFilter(value: string): SkillDto[] {
    const filterValue = value?.toLowerCase();
    return this.skillOptions
      .filter(option => option.name.toLowerCase().includes(filterValue))
      .sort((a, b) => a.name.localeCompare(b.name));
  }


  private getSkillList(): Observable<SkillDto[]> {
    return this.skillService.findAllSkills().pipe(
      map(skills => {
        console.log('Skills', skills);
        return skills;
      })
    );
  }

  initialize(): void {
    this.job.id = this.data.job.id ?? '';
    this.job.company = {} as CompanyDto;
    this.job.company.id = this.data.companyId;
    this.job.description = this.form.get('description')?.value;
    this.job.position = this.form.get('position')?.value;
    this.job.location = this.form.get('location')?.value;
    this.job.type = this.form.get('type')?.value;
    this.job.openUntil = this.form.get('openUntil')?.value;
    this.job.skills = this.skills;
  }


  onSubmit(): void {
    if (this.form.valid && this.skills.length > 0){
      this.initialize();
      const params = { body: this.job };
      console.log('params', params);
      if (this.data.job.id === null || this.data.job.id === undefined || this.data.job.id === '') {
        this.postedJobService.savePostedJob(params).subscribe(({
          next: (response: any) => {
            console.log('Job added successfully', response);
            this.form.get('job')?.setValue(response);
            this.dialogRef.close(this.form.value);
          }, error: (error: any) => {
            console.error('Error adding job', error);
          }
        }));
      } else {
        this.postedJobService.updatePostedJob(params).subscribe(({
          next: (response: any) => {
            console.log('Job added successfully', response);
            this.form.get('job')?.setValue(response);
            this.dialogRef.close(this.form.value);
          }, error: (error: any) => {
            console.error('Error adding job', error);
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

  updateRating(event: any): void {
    const newRating = event.target ? event.target.value : event;
    console.log('New Rating', newRating);
    this.form.controls['rating'].setValue(newRating);
  }
}
