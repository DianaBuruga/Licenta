import { AbstractControl, ValidatorFn } from '@angular/forms';
import { CompanyDto } from '../../services/models';

export function companyNameValidator(companyMap: Map<string,CompanyDto>): ValidatorFn {
    console.log("companyMap",companyMap);
  return (control: AbstractControl): { [key: string]: any } | null => {
    const forbidden = !companyMap.get(control.value);
    return forbidden ? { 'invalidCompanyName': { value: control.value } } : null;
  };
}
