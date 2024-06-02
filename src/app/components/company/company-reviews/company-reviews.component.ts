import { CUSTOM_ELEMENTS_SCHEMA, Component } from '@angular/core';
import { ReviewDto } from '../../../services/models';
import { MatCardModule } from '@angular/material/card';
import { MatDialog } from '@angular/material/dialog';
import { MatIcon } from '@angular/material/icon';
import { ReviewService, SearchService } from '../../../services/services';
import { ActivatedRoute } from '@angular/router';
import { CommonModule, NgFor } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { defineComponents, IgcRatingComponent } from 'igniteui-webcomponents';
import 'igniteui-webcomponents/themes/light/bootstrap.css';
import { ReviewOpenDialogComponent } from '../review-open-dialog/review-open-dialog.component';

defineComponents(IgcRatingComponent);
@Component({
  selector: 'app-company-reviews',
  standalone: true,
  imports: [
    MatCardModule,
    MatIcon,
    CommonModule,
    MatButtonModule,
    NgFor
  ],
  templateUrl: './company-reviews.component.html',
  styleUrl: './company-reviews.component.scss',
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CompanyReviewsComponent {
  reviews: ReviewDto[] = [];
  id: any;
  error: any;
  emptyReview: ReviewDto = {
    position: '',
    description: '',
    rating: 0,
    type: 'JOB',
    company: {
      id: '',
      address: '',
      name: '',
      website: ''
    }
  };

  constructor(public dialog: MatDialog, private reviewService: ReviewService, private route: ActivatedRoute, private searchService: SearchService) { };

  ngOnInit(): void {
    console.log('Company page initialized');
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    const param = {
      endpoint: 'reviews',
      criteria: {
        "company.id": this.id
      }
    };
    this.searchService.search(param).subscribe({
      next: (reviews: ReviewDto[]) => {
        this.reviews = reviews;
        console.log('Reviews:', reviews);
      },
      error: (error: any) => {
        this.error = error;
        console.error('Error fetching review:', error);
      },
      complete: () => {
        console.log('Completed fetching review');
      }
    });
  }

  openDialog(review: ReviewDto): void {
    const dialogRef = this.dialog.open(ReviewOpenDialogComponent, {
      width: '50%',
      data: { companyId: this.id , review: review }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Result', result);
      if (result.review) {
        const index = this.reviews?.findIndex(x => x.id === result.review.id);
        if (index !== -1 && index !== undefined && index !== null && this.reviews !== undefined) {
          this.reviews[index] = result.review;
        }
        else {
          this.reviews?.push(result.review);
        }
        this.reviews = [...this.reviews ?? []];
      }
      console.log('Dialog was closed');
    });
  }

  deleteReview(review: ReviewDto): void {
    console.log('Deleting review', review.id);
    if(review.id !== undefined) {
      const params = { id: review.id };
      this.reviewService.deleteReview(params).subscribe({
        next: () => {
          console.log('Review deleted successfully');
          let index = this.reviews?.indexOf(review);
          if (index !== undefined && index !== -1) {
            this.reviews?.splice(index, 1);
          }
        },
        error: (error) => {
          console.error('Error deleting review', error);
        },
      });
    }
  }
}
