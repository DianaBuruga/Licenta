import { CUSTOM_ELEMENTS_SCHEMA, Component, Inject, Input, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { CompanyDto } from '../../../services/models';
import { defineComponents, IgcRatingComponent } from 'igniteui-webcomponents';
import 'igniteui-webcomponents/themes/light/bootstrap.css';

defineComponents(IgcRatingComponent);

@Component({
  selector: 'app-company-profile',
  standalone: true,
  imports: [
    MatCardModule,
  ],
  templateUrl: './company-profile.component.html',
  styleUrl: './company-profile.component.scss',
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CompanyProfileComponent implements OnInit {
  ngOnInit(): void {
    // if(this.company === undefined) {
    //     throw new Error('Company is required');
    // }
    // if(this.company?.reviews === undefined) {
    //   this.company.reviews = [];
    // }
  }

  @Input() company: CompanyDto | undefined;
  get averageScore(): number {
    const totalScore = this.company?.reviews.reduce((sum, review) => sum + review.rating, 0) || 0;
    //if(!this.company?.reviews.length) return 0;
    //return totalScore / this.company?.reviews.length;
    return 3.2;
  }
  get totalReviews(): number {
    return this.company?.reviews.length || 0;
  }
}
