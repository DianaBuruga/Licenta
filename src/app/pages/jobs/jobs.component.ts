import {Component} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatDialog} from '@angular/material/dialog';
import {MatIcon} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import {NgFor} from '@angular/common';
import {PostedJobDto} from '../../services/models';
import {PostedJobService, SearchService} from '../../services/services';
import {ActivatedRoute, Router} from '@angular/router';
import {
  PostedJobOpenDialogComponent
} from '../../components/company/posted-job-open-dialog/posted-job-open-dialog.component';
import {SearchComponent} from "../../components/search/search.component";

@Component({
  selector: 'app-company-jobs',
  standalone: true,
  templateUrl: './jobs.component.html',
  styleUrl: './jobs.component.scss',
  imports: [
    MatButtonModule,
    MatIcon,
    MatCardModule,
    NgFor,
    SearchComponent
  ]
})
export class JobsComponent {
  id: any;
  error: any;
  jobs: PostedJobDto[] = [];
  endpoint: string = 'postedJobs';

  emptyJob: PostedJobDto = {} as PostedJobDto;

  constructor(private jobService: PostedJobService, private dialog: MatDialog, private route: ActivatedRoute, private searchService: SearchService, private router: Router) {
  };

  processPostedJobs(filterdJobs: PostedJobDto[]) {
    console.log('Am primit job-urile:');
    this.jobs = filterdJobs;
  }

  ngOnInit(): void {
    this.emptyJob.id = '';
    console.log('Company page initialized');
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    const param = {
      endpoint: 'postedJobs',
      criteria: {
        "status": "ACTIVE"
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
      data: {companyId: this.id, job: job}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Result', result);
      if (result.job && result.job.status === 'ACTIVE') {
        console.log('Job', result.job);
        const index = this.jobs?.findIndex(x => x.id === result.job.id);
        if (index !== -1 && index !== undefined && index !== null && this.jobs !== undefined) {
          this.jobs[index] = result.job;
        } else {
          this.jobs?.push(result.job);
        }
        this.jobs = [...this.jobs ?? []];
      }
      console.log('Dialog was closed');
    });
  }

  navigateWithParam(id: string | undefined) {
    if (id) {
      this.router.navigate(['/job', id]);
    }
  }

  navigateToCompanyWithParam(id: string | undefined) {
    if (id) {
      this.router.navigate(['/company', id]);
    }
  }
}
