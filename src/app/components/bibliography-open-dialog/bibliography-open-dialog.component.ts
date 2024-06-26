import { ChangeDetectorRef, Component, Inject, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { BibliographyDto, SkillDto, UserDto } from '../../services/models';
import { BibliographyService, FileService, SkillService } from '../../services/services';
import {MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { CommonModule } from '@angular/common';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { Observable, map, startWith } from 'rxjs';
import { UploadFile$Params } from '../../services/fn/file/upload-file';
import { FileData } from '../../services/models/file-data';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';

@Component({
  selector: 'app-bibliography-open-dialog',
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
    MatIconModule
  ],
  templateUrl: './bibliography-open-dialog.component.html',
  styleUrl: './bibliography-open-dialog.component.scss'
})
export class BibliographyOpenDialogComponent {
  form: FormGroup;
  specializations: string[] = [];
  facultyOptions: string[] = [];
  error: any = null;
  dialogRef = inject(MatDialogRef);
  skillMap: Map<string, SkillDto> = new Map();
  filteredOptions: Observable<SkillDto[]> = new Observable();
  options: SkillDto[] = [];
  fileInfo: string | undefined;

  createSpecializationDto(): BibliographyDto {
    let bibliography: BibliographyDto = {
      id: this.data?.bibliography?.id ?? '',
      title: this.form.get('title')?.value,
      skill: this.skillMap.get(this.form.get('skill')?.value) ?? {} as SkillDto,
      text: this.form.get('text')?.value,
      writer: {} as UserDto,
      date: ''
    };
    console.log('Bibliography', bibliography);
    return bibliography;
  }

  onSubmit(): void {
    if (this.form.valid) {
      const params = {body: this.createSpecializationDto()};
      console.log('params', params);
      if (params.body.id === '') {
        this.bibliographyService.saveBibliography(params).subscribe(({
          next: (response: any) => {
            console.log('Bibliography added successfully', response);
            this.form.get('bibliography')?.setValue(response);
            this.uploadFileToDb();
            this.dialogRef.close(this.form.value);
          }, error: (error: any) => {
            console.error('Error adding bibliography', error);
          }
        }));
      } else {
        this.bibliographyService.updateBibliography(params).subscribe(({
          next: (response: any) => {
            console.log('Bibliography updated successfully', response);
            this.form.get('bibliography')?.setValue(response);
            this.uploadFileToDb();
            this.dialogRef.close(this.form.value);
          }, error: (error: any) => {
            console.error('Error updating bibliography', error);
          }
        }));
      }
    }
  }

  uploadFileToDb(): void {
    const params = {
      id: this.form.get('bibliography')?.value.id,
      table: 'bibliography',
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

  constructor(private fileService: FileService, private cdr: ChangeDetectorRef, private bibliographyService: BibliographyService, @Inject(MAT_DIALOG_DATA) public data: any, private skillService: SkillService) {
    console.log('Data', data);
    const fb = new FormBuilder();
    this.form = fb.group({
      title: [data?.bibliography?.title ?? '', [Validators.required]],
      skill: [data?.bibliography?.skill?.name ?? '', [Validators.required]],
      text: [data?.bibliography?.text ?? '', [Validators.required]],
      bibliography: [data?.bibliography ?? {} as BibliographyDto],
      file: []
    });

    if(data?.file){
      this.fileInfo = `${data?.file.name} (${this.formatBytes(data?.file.blob.size)})`;
      this.form.get('file')?.setValue(data?.file);
    }
  }

  ngOnInit(): void {
    this.getSkillList().subscribe(skills => {
      this.skillMap = new Map(skills.map(skill => [skill.name, skill]));
      this.options = skills;
      this.filteredOptions = this.form.get('skill')!.valueChanges.pipe(
        startWith(''),
        map(value => this._filter(value))
      );
      console.log('Skill list', this.options);
    });
  }

  private _filter(value: string): SkillDto[] {
    const filterValue = value.toLowerCase();
    return this.options
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
