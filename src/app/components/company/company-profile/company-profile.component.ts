import { CUSTOM_ELEMENTS_SCHEMA, Component, Input, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { AverageRating, CompanyDto } from '../../../services/models';
import { defineComponents, IgcRatingComponent } from 'igniteui-webcomponents';
import 'igniteui-webcomponents/themes/light/bootstrap.css';
import { MatDialog } from '@angular/material/dialog';
import { CompanyProfileOpenDialogComponent } from '../../company-profile-open-dialog/company-profile-open-dialog.component';
import { ReviewService } from '../../../services/services';
import { ActivatedRoute } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';

defineComponents(IgcRatingComponent);

@Component({
  selector: 'app-company-profile',
  standalone: true,
  imports: [
    MatCardModule, MatButtonModule
  ],
  templateUrl: './company-profile.component.html',
  styleUrl: './company-profile.component.scss',
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CompanyProfileComponent implements OnInit {
  rating: AverageRating = {
    averageRating: 0,
    totalRatings: 0
  } as AverageRating;
  error: any;
  id: any;

  constructor(public dialog: MatDialog, private reviewService: ReviewService, private route: ActivatedRoute) { };
  @Input() company: CompanyDto | undefined;

  ngOnInit(): void {
    console.log('Company page initialized');
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    const param = { companyId: this.id };
    this.reviewService.getAverageReviewRating(param).subscribe({
      next: (averageRating: AverageRating) => {
        this.rating = averageRating;
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

  openDialog(company: CompanyDto | undefined): void {
    if (company) {
      const dialogRef = this.dialog.open(CompanyProfileOpenDialogComponent, {
        width: '50%',
        data: { company: this.company }
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log('Result', result);
        console.log('Dialog was closed');
      });
    }
  }
}
