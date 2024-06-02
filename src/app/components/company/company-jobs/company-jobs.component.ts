import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { MatIcon } from '@angular/material/icon';
import { PostedJobService, SearchService } from '../../../services/services';
import { ActivatedRoute, Router } from '@angular/router';
import { PostedJobDto } from '../../../services/models';
import { PostedJobOpenDialogComponent } from '../posted-job-open-dialog/posted-job-open-dialog.component';
import { MatCardModule } from '@angular/material/card';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-company-jobs',
  standalone: true,
  imports: [
    MatButtonModule,
    MatIcon,
    MatCardModule,
    NgFor
  ],
  templateUrl: './company-jobs.component.html',
  styleUrl: './company-jobs.component.scss'
})
export class CompanyJobsComponent {
  id: any;
  error: any;
  jobs: PostedJobDto[] = [];

  emptyJob: PostedJobDto = {} as PostedJobDto;

  constructor(private jobService: PostedJobService, private dialog: MatDialog, private route: ActivatedRoute, private searchService: SearchService, private router: Router) { };

  ngOnInit(): void {
    this.emptyJob.id = '';
    console.log('Company page initialized');
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    const param = {
      endpoint: 'postedJobs',
      criteria: {
        "status": "ACTIVE",
        "company.id": this.id
      }
    };
    this.searchService.search(param).subscribe({
      next: (jobs: PostedJobDto[]) => {
        this.jobs = jobs;
        console.log('Posted jobs:', jobs);
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

  openDialog(job: PostedJobDto): void {
    const dialogRef = this.dialog.open(PostedJobOpenDialogComponent, {
      width: '50%',
      data: { companyId: this.id, job: job }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Result', result);
      if (result.job && result.job.status === 'ACTIVE') {
        console.log('Job', result.job);
        const index = this.jobs?.findIndex(x => x.id === result.job.id);
        if (index !== -1 && index !== undefined && index !== null && this.jobs !== undefined) {
          this.jobs[index] = result.job;
        }
        else {
          this.jobs?.push(result.job);
        }
        this.jobs = [...this.jobs ?? []];
      }
      console.log('Dialog was closed');
    });
  }

  navigateWithParam(id: string | undefined) {
    if (id) {
      this.router.navigate(['/company', id]);
    }
  }

  // deleteReview(review: ReviewDto): void {
  //   console.log('Deleting review', review.id);
  //   if(review.id !== undefined) {
  //     const params = { id: review.id };
  //     this.reviewService.deleteReview(params).subscribe({
  //       next: () => {
  //         console.log('Review deleted successfully');
  //         let index = this.reviews?.indexOf(review);
  //         if (index !== undefined && index !== -1) {
  //           this.reviews?.splice(index, 1);
  //         }
  //       },
  //       error: (error) => {
  //         console.error('Error deleting review', error);
  //       },
  //     });
  //   }
  // }
}




