import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JobCandidatesService, PostedJobService } from '../../services/services';
import { JobCandidatesDto, PostedJobDto } from '../../services/models';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { MatChipsModule } from '@angular/material/chips';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { PostedJobOpenDialogComponent } from '../../components/company/posted-job-open-dialog/posted-job-open-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-job',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatChipsModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './job.component.html',
  styleUrl: './job.component.scss'
})
export class JobComponent {
  id: any;
  error: any;
  job: PostedJobDto = {} as PostedJobDto;
  constructor(private jobService: PostedJobService, private route: ActivatedRoute, private router: Router, private dialog: MatDialog, private jobCandidatesService: JobCandidatesService) { }

  ngOnInit(): void {
    console.log('ProfileComponent initialized');
    this.getCurrentJob();

  }
  private getCurrentJob(): void {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    const param = { id: this.id };
    this.jobService.findPostedJobById(param).subscribe({
      next: (job: PostedJobDto) => {
        this.job = job;
        console.log('Posted Job:', job);
      },
      error: (error: any) => {
        this.error = error;
        console.error('Error fetching job:', error);
      },
      complete: () => {
        console.log('Completed fetching job');
      }
    });
  }

  navigateWithParam(id: string | undefined) {
    if (id) {
      this.router.navigate(['/user', id]);
    }
  }
  
  openDialog(job: PostedJobDto): void {
    const dialogRef = this.dialog.open(PostedJobOpenDialogComponent, {
      width: '50%',
      data: { companyId: this.id, job: job }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Result', result);
      this.job = result.job;
      console.log('Dialog was closed');
    });
  }

  applyJob(job: PostedJobDto): void {
    const param = {
      body: job
    }
    this.jobCandidatesService.saveJobCandidates(param).subscribe({
      next: (job: JobCandidatesDto) => {
        console.log('JobCandidates:', job);
      },
      error: (error: any) => {
        console.error('Error fetching job:', error);
      },
      complete: () => {
        console.log('Completed fetching job');
      }
    });
  }
}
