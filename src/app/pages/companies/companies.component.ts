import { CUSTOM_ELEMENTS_SCHEMA, Component, OnInit } from '@angular/core';
import { FlexLayoutServerModule } from '@angular/flex-layout/server';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatCardModule } from '@angular/material/card';
import { CommonModule, NgFor } from '@angular/common';
import { AverageRating, CompanyDto } from '../../services/models';
import { CompanyService, ReviewService } from '../../services/services';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { defineComponents, IgcRatingComponent } from 'igniteui-webcomponents';
import 'igniteui-webcomponents/themes/light/bootstrap.css';

defineComponents(IgcRatingComponent);

@Component({
  selector: 'app-companies',
  standalone: true,
  imports: [FlexLayoutServerModule, FlexLayoutModule, MatCardModule, CommonModule, NgFor, MatButtonModule],
  templateUrl: './companies.component.html',
  styleUrl: './companies.component.scss',
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CompaniesComponent implements OnInit {
  companies: CompanyDto[] = [];
  error: any;

  constructor(private companyService: CompanyService, private router: Router, private reviewService: ReviewService) { }
  ngOnInit(): void {
    console.log('Users page initialized');
    this.companyService.findAllCompanies().subscribe({
      next: (companies: CompanyDto[]) => {
        this.companies = companies;
      },
      error: (error: any) => {
        this.error = error;
        console.error('Error fetching user:', error);
      },
      complete: () => {
        console.log('Completed fetching user');
      }
    });
  }
  rating: Map<string, AverageRating> = new Map<string, AverageRating>();

  private getCompanyReview(companyId: string) {
    const param = { companyId: companyId };
    this.reviewService.getAverageReviewRating(param).subscribe({
      next: (averageRating: AverageRating) => {
        this.rating.set(companyId, averageRating);
        console.log(averageRating);
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

  navigateWithParam(id: string | undefined) {
    if (id) {
      this.router.navigate(['/company', id]);
    }
  }
}
