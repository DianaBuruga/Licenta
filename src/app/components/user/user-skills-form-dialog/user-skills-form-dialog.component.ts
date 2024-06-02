import { Component, Inject, OnInit, inject } from '@angular/core';
import { MatSliderModule } from '@angular/material/slider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SkillService, UserSkillService } from '../../../services/services';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { SkillDto, UserSkillsDto } from '../../../services/models';
import { Observable, map, startWith } from 'rxjs';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-user-skills-form-dialog',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatSliderModule,
    MatFormFieldModule,
    MatAutocompleteModule,
    CommonModule,
    MatDialogModule,
    MatButtonModule,
    MatInputModule
  ],
  templateUrl: './user-skills-form-dialog.component.html',
  styleUrls: ['./user-skills-form-dialog.component.scss']
})
export class UserSkillsFormDialogComponent implements OnInit {
  form: FormGroup;
  dialogRef = inject(MatDialogRef);
  error: any = null;
  color: string = "red";
  skillMap: Map<string, SkillDto> = new Map();
  filteredOptions: Observable<SkillDto[]> = new Observable();
  options: SkillDto[] = [];

  constructor(private userSkillService: UserSkillService, private skillService: SkillService, @Inject(MAT_DIALOG_DATA) public data: any) {
    console.log('Data', data);
    const fb = new FormBuilder();
    this.form = fb.group({
      skill: [data.userSkill.skill.name, [Validators.required]],
      proficiency: [data.userSkill.proficiency | 0, [Validators.required, Validators.min(0), Validators.max(100)]],
      userSkills: [data.userSkill.userDTO]
    });
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

  closeDialog() {
    this.dialogRef.close();
  }

  userSkill: UserSkillsDto = {} as UserSkillsDto;

  initializeEntity(): void {
    this.userSkill.userId = this.data.user.id;
    const selectedSkilledName = this.form.get('skill')?.value;
    const newSkill: SkillDto = {
      name: selectedSkilledName,
      bibliographies: [],
      courses: [],
      userSkills: []
    };
    this.userSkill.skill = this.skillMap.get(selectedSkilledName) ?? newSkill;
    const id = this.userSkill.skill.id;
    this.userSkill.skillId = id;
    this.userSkill.proficiency = this.form.get('proficiency')?.value;
    this.userSkill.userDTO = this.data.user;
  }

  onSubmit(): void {
    if (this.form.valid) {
      this.initializeEntity();
      const params = { body: this.userSkill };
      console.log('params', params);
      if (this.userSkill.skillId === '' || this.userSkill.skillId === undefined) {
        this.userSkillService.saveUserSkill(params).subscribe({
          next: (response: any) => {
            console.log('UserSkill added successfully', response);
            this.form.get('userSkills')?.setValue(response);
            this.dialogRef.close(this.form.value);
          },
          error: (error: any) => {
            console.error('Error adding userSkill', error);
          }
        });
      } else {
        this.userSkillService.updateUserSkill(params).subscribe({
          next: (response: any) => {
            console.log('UserSkill updated successfully', response);
            this.form.get('userSkills')?.setValue(response);
            this.dialogRef.close(this.form.value);
          },
          error: (error: any) => {
            console.error('Error updating userSkill', error);
          }
        });
      }
    }
  }

  private getSkillList(): Observable<SkillDto[]> {
    return this.skillService.findAllSkills().pipe(
      map(skills => {
        console.log('Skills', skills);
        return skills;
      })
    );
  }

  formatLabel(value: number): string {
    return value + '%';
  }
}
