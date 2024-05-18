import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'app-add-company-form',
  standalone: true,
  imports: [
    MatFormFieldModule
  ],
  templateUrl: './add-company-form.component.html',
  styleUrl: './add-company-form.component.scss'
})
export class AddCompanyFormComponent {
  form: FormGroup;
  constructor() {
    const fb = new FormBuilder();
    this.form = fb.group({
      companyName: ['', Validators.required],
      address: ['', Validators.required],
      website: ['', Validators.required, Validators.pattern('https?://.+')]
    })
  }
}
