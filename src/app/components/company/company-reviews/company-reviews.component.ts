import { Component } from '@angular/core';
import { ReviewDto } from '../../../services/models';

@Component({
  selector: 'app-company-reviews',
  standalone: true,
  imports: [],
  templateUrl: './company-reviews.component.html',
  styleUrl: './company-reviews.component.scss'
})
export class CompanyReviewsComponent {
  reviews: ReviewDto[] = [];
}
