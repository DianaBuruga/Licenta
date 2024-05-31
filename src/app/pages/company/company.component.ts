import { Component, OnInit } from '@angular/core';
import { CompanyProfileComponent } from "../../components/company/company-profile/company-profile.component";
import { CompanyRecruitersComponent } from "../../components/company/company-recruiters/company-recruiters.component";
import { CompanyReviewsComponent } from "../../components/company/company-reviews/company-reviews.component";
import { CompanyDto } from '../../services/models';
import { ActivatedRoute } from '@angular/router';
import { CompanyService } from '../../services/services';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-company',
  standalone: true,
  templateUrl: './company.component.html',
  styleUrl: './company.component.scss',
  imports: [CompanyProfileComponent, CompanyRecruitersComponent, CompanyReviewsComponent, MatCardModule]
})
export class CompanyComponent implements OnInit {
  id: any;
  error: any;
  company: CompanyDto = {} as CompanyDto;

  constructor(private route: ActivatedRoute, private companyService: CompanyService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    const param = { id: this.id };
    this.companyService.findCompanyById(param).subscribe({
      next: (company: CompanyDto) => {
        this.company = company;
        console.log('Company:', company);
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
}
