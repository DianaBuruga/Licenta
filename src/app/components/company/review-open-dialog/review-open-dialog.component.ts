import { CUSTOM_ELEMENTS_SCHEMA, Component, Inject, inject } from '@angular/core';
import { CompanyDto, ReviewDto } from '../../../services/models';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable, map, startWith } from 'rxjs';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { ReviewService } from '../../../services/services';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { CommonModule } from '@angular/common';
import { defineComponents, IgcRatingComponent } from 'igniteui-webcomponents';
import 'igniteui-webcomponents/themes/light/bootstrap.css';

defineComponents(IgcRatingComponent);

@Component({
  selector: 'app-review-open-dialog',
  standalone: true,
  imports: [
    MatDialogModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatAutocompleteModule,
    CommonModule
  ],
  templateUrl: './review-open-dialog.component.html',
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  styleUrl: './review-open-dialog.component.scss'
})
export class ReviewOpenDialogComponent {
  form: FormGroup;
  review: ReviewDto = {} as ReviewDto;
  options: string[] = ['JOB', 'INTERVIEW'];
  error: any = null;
  filteredOptions: Observable<string[]> = new Observable();
  dialogRef = inject(MatDialogRef);

  constructor(private reviewService: ReviewService, @Inject(MAT_DIALOG_DATA) public data: any) {
    const fb = new FormBuilder();
    this.form = fb.group({
      position: [data.review.position, [Validators.required]],
      description: [data.review.description, [Validators.required]],
      rating: [data.review.rating, [Validators.required]],
      type: [data.review.type, [Validators.required]],
      review: []
    });
    this.filteredOptions = this.form.get('type')!.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
  }

  initialize(): void {
    this.review.id = this.data.review.id;
    this.review.company = {} as CompanyDto;
    this.review.company.id = this.data.companyId;
    this.review.description = this.form.get('description')?.value;
    this.review.position = this.form.get('position')?.value;
    this.review.rating = this.form.controls['rating'].value;
    this.review.type = this.form.get('type')?.value;
  }


  onSubmit(): void {
    if (this.form.valid) {
      this.initialize();
      const params = { body: this.review };
      console.log('params', params);
      if (this.data.review.id === null || this.data.review.id === undefined || this.data.review.id === '') {
        this.reviewService.saveReview(params).subscribe(({
          next: (response: any) => {
            console.log('Review added successfully', response);
            response.user = this.data.user;
            this.form.get('review')?.setValue(response);
            this.dialogRef.close(this.form.value);
          }, error: (error: any) => {
            console.error('Error adding review', error);
          }
        }));
      } else {
        this.reviewService.updateReview(params).subscribe(({
          next: (response: any) => {
            console.log('Review added successfully', response);
            response.user = this.data.user;
            this.form.get('review')?.setValue(response);
            this.dialogRef.close(this.form.value);
          }, error: (error: any) => {
            console.error('Error adding review', error);
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
