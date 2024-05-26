import { Component } from '@angular/core';
import { CompanyProfileComponent } from "../../components/company/company-profile/company-profile.component";
import { CompanyRecruitersComponent } from "../../components/company/company-recruiters/company-recruiters.component";
import { CompanyReviewsComponent } from "../../components/company/company-reviews/company-reviews.component";
import { ReviewDto } from '../../services/models';

@Component({
    selector: 'app-company',
    standalone: true,
    templateUrl: './company.component.html',
    styleUrl: './company.component.scss',
    imports: [CompanyProfileComponent, CompanyRecruitersComponent, CompanyReviewsComponent]
})
export class CompanyComponent {
    reviews: ReviewDto[] = [];
    get averageScore(): number {
        const totalScore = this.reviews.reduce((sum, review) => sum + review.rating, 0);
      //  return totalScore / this.reviews.length;
      return 3.5;
      }
}
