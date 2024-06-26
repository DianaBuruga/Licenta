import { AsyncPipe, CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, ElementRef, Inject, ViewChild, inject } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatAutocompleteModule, MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { CourseDto, FacultyDto, SkillDto, SpecializationDto, UserDto } from '../../services/models';
import { Observable, map, startWith } from 'rxjs';
import { CourseService, FileService, SearchService, SkillService, UserService } from '../../services/services';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import {MatChipInputEvent, MatChipsModule } from '@angular/material/chips';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatIconModule } from '@angular/material/icon';
import { requiredSkillsValidator } from './requiredSkillsValidator';
import { UploadFile$Params } from '../../services/fn/file/upload-file';
import { FileData } from '../../services/models/file-data';

@Component({
  selector: 'app-course-open-dialog',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatDialogModule,
    MatAutocompleteModule,
    ReactiveFormsModule,
    CommonModule,
    MatSelectModule,
    MatButtonModule,
    MatChipsModule,
    AsyncPipe,
    FormsModule,
    MatIconModule,
    MatChipsModule
  ],
  templateUrl: './course-open-dialog.component.html',
  styleUrl: './course-open-dialog.component.scss'
})
export class CourseOpenDialogComponent {
  form: FormGroup;
  specializations: string[] = [];
  facultyOptions: string[] = [];
  error: any = null;
  filteredFacultyOptions: Observable<string[]> = new Observable();
  specializationFilterOptions: Observable<string[]> = new Observable();
  facultyMap = new Map<string, FacultyDto>();
  dialogRef = inject(MatDialogRef);
  skillOptions: SkillDto[] = [];
  filteredOptions: Observable<string[]> = new Observable();
  skillMap: Map<string, SkillDto> = new Map();
  specializationMap: Map<string, SpecializationDto> = new Map();
  skillFilteredOptions: Observable<SkillDto[]> = new Observable();
  separatorKeysCodes: number[] = [ENTER, COMMA];
  skills: SkillDto[] = [];
  user: UserDto = {} as UserDto;
  fileInfo: string | undefined;

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

  createCourseDto(): CourseDto {
    return {
      id: this.data?.course?.id ?? '',
      name: this.form.get('name')?.value,
      specialization: this.specializationMap.get(this.form.get('specialization')?.value),
      year: this.form.get('year')?.value,
      semester: this.form.get('semester')?.value,
      skills: this.skills,
    } as CourseDto;
  }

  getMaxYear(): number {
    return this.facultyMap.get(this.form.get('faculty')?.value)?.yearsOfStudy ?? 0;
  }

  onSubmit(): void {
    if (this.form.valid) {
      const params = {body: this.createCourseDto()};
      console.log('params', params);
      if (this.data.course.id === '' || this.data.course.id === undefined) {
        this.courseService.saveCourse(params).subscribe(({
          next: (response: any) => {
            console.log('Course added successfully', response);
            response.user = this.data.user;
            this.form.get('course')?.setValue(response);
            this.uploadFileToDb();
            this.dialogRef.close(this.form.value);
          }, error: (error: any) => {
            console.error('Error adding course', error);
          }
        }));
      } else {
        this.courseService.updateCourse(params).subscribe(({
          next: (response: any) => {
            console.log('Course updated successfully', response);
            response.user = this.data.user;
            this.form.get('course')?.setValue(response);
            this.uploadFileToDb();
            this.dialogRef.close(this.form.value);
          }, error: (error: any) => {
            console.error('Error updating course', error);
          }
        }));
      }
    }
  }

  uploadFileToDb(): void {
    const params = {
      id: this.form.get('course')?.value.id,
      table: 'course',
      fileType: 'POST_PHOTO',
      body: {
        'multipartFile': this.form.get('file')?.value
      }} as UploadFile$Params;
    console.log('params', params);
    this.fileService.uploadFile(params).subscribe(({
      next: (response: any) => {
        const file: File = this.form.get('file')?.value;
        if (file) {
          const reader = new FileReader();
          reader.onload = (e: any) => {
            const blob = new Blob([e.target.result], { type: file.type });
            this.form.get('file')?.setValue({
              name: file.name,
              blob: blob
            } as FileData);
          };
          reader.readAsArrayBuffer(file);
          this.fileInfo = '';
        }
        console.log('File uploaded successfully', response);
      }, error: (error: any) => {
        console.error('Error uploading file', error);
      }
    }));
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  constructor(private fileService: FileService, private cdr: ChangeDetectorRef, private courseService: CourseService, private userService: UserService, private skillService: SkillService, @Inject(MAT_DIALOG_DATA) public data: any, private searchService: SearchService) {
    const fb = new FormBuilder();
    this.skills = data?.course?.skills ?? [];
    this.form = fb.group({
      name: [data?.course?.name ?? '', [Validators.required]],
      specialization: [data?.course?.specialization?.name ?? '', [Validators.required]],
      faculty: [data?.course?.specialization?.faculty?.name ?? '', [Validators.required]],
      skill: ['', [requiredSkillsValidator(this.skills)]],
      year: [data?.course?.year ?? '', [Validators.required]],
      semester: [data?.course?.semester ?? '', [Validators.required]],
      course: [],
      file: []
    });

    if(data?.file){
      this.fileInfo = `${data?.file.name} (${this.formatBytes(data?.file.blob.size)})`;
      this.form.get('file')?.setValue(data?.file);
    }

    this.userService.getAuthenticatedUser().subscribe({
      next: (user: UserDto) => {
        console.log('User fetched', user);
        this.user = user;
      },
      error: (error: any) => {
        this.error = error;
        console.error('Error fetching user:', error);
      },
      complete: () => {
        console.log('Completed fetching user');
    this.getFacultyList().subscribe(faculties => {
      this.facultyMap = new Map(faculties.map(faculty => [faculty.name, faculty]));
      this.facultyOptions = faculties.map(faculty => faculty.name);
      this.filteredFacultyOptions = this.form.get('faculty')!.valueChanges.pipe(startWith(''), map(value => this.facultyFilter(value)));
      console.log('Faculties list', this.facultyOptions);
    });

    this.getSpecilizations().subscribe(specializations => {
      this.specializationMap = new Map(specializations.map(specialization => [specialization.name, specialization]));
      this.specializations = specializations.map(specialization => specialization.name);
      this.specializationFilterOptions = this.form.get('name')!.valueChanges.pipe(startWith(''), map(value => this.specializationFilter(value)));
      console.log('Specializations list', this.specializations);
    });

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

  private getSpecilizations(): Observable<SpecializationDto[]> {
    const param = {
      endpoint: "specializations",
      criteria: {
        "faculty.name": this.form.get('faculty')?.value,
        "user.id": this.user.id as string
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
    const id = this.user.id as string;
    console.log('User id', id);
    const param = {
      endpoint: "faculties",
      criteria: {
        "specializations.user.id": id
      }
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

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      this.fileInfo = `${file.name} (${this.formatBytes(file.size)})`;
      this.uploadFile(file);
      this.cdr.detectChanges();
    }
  }

  formatBytes(bytes: number): string {
    const UNITS = ['Bytes', 'kB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
    const factor = 1024;
    let index = 0;

    while (bytes >= factor) {
      bytes /= factor;
      index++;
    }
    return `${parseFloat(bytes.toFixed(2))} ${UNITS[index]}`;
  }

  uploadFile(file: File) {
    console.log('File', file);
    this.form.get('file')?.setValue(file);
  }

  openFile(): void {
    if (this.form.get('file')?.value) {
      const fileURL = URL.createObjectURL(this.form.get('file')?.value);
      window.open(fileURL, '_blank');
    }
  }
  removeFile(): void {
    this.form.get('file')?.reset();
    this.fileInfo = undefined;
    this.cdr.detectChanges();
  }
}
