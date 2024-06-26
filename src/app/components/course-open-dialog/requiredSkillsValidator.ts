import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';
import { SkillDto } from '../../services/models';

export function requiredSkillsValidator(skills: SkillDto[]): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    return skills.length > 0 ? null : { noSkills: true };
  };
}