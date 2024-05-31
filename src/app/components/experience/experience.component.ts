import { Component, Input } from '@angular/core';
import { MatCard, MatCardTitle, MatCardContent } from '@angular/material/card';
import { CommonModule, NgFor } from '@angular/common';
import { MatIcon } from '@angular/material/icon';
import { AddFormDialogComponent } from '../add-form-dialog/add-form-dialog.component';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { JobHistoryDto, UserDto } from '../../services/models';
import { JobHistoryService } from '../../services/services';

@Component({
  selector: 'app-experience',
  standalone: true,
  imports: [
    MatCard,
    MatCardContent,
    MatCardTitle,
    CommonModule,
    NgFor,
    MatIcon,
    MatDialogModule,
  ],
  templateUrl: './experience.component.html',
  styleUrl: './experience.component.scss'
})
export class ExperienceComponent {
  @Input() userExperiences: UserDto | undefined;
  @Input() experiences: JobHistoryDto[] | undefined;
  get emptyJobHistory(): JobHistoryDto {
    return {
      id: '',
      position: '',
      description: '',
      startDate: '',
      endDate: '',
      needQualification: false,
      company: {
        name: '',
        website: '',
        address: '',
        jobHistories: [],
        postedJobs: [],
        reviews: []
      },
      user: this.userExperiences
    } as JobHistoryDto;
  }

  constructor(public dialog: MatDialog, private jobHistoryService: JobHistoryService) {
  }

  openDialog(jobHistory: JobHistoryDto): void {
    const dialogRef = this.dialog.open(AddFormDialogComponent, {
      width: '50%',
      data: { user: this.userExperiences, job: jobHistory }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Result', result);
      if (result.jobHistory !== undefined && this.userExperiences) {
        let index = this.experiences?.findIndex(x => x.id === jobHistory.id);
        if (index !== -1 && index !== undefined && index !== null && this.experiences !== undefined) {
          this.experiences[index] = result.jobHistory;
        } else {
          this.experiences?.push(result.jobHistory);
        }
        this.experiences = [...this.experiences ?? []];
        console.log('JobHistory', this.experiences);
      }
      console.log('Dialog was closed');
    });
  }
  deleteJobExperience(jobHistory: JobHistoryDto): void {
    console.log('Deleting Job History', jobHistory.id);

    if (jobHistory.id !== undefined) {
      const params = { id: jobHistory.id };
      this.jobHistoryService.deleteJobHistory(params).subscribe({
        next: () => {
          console.log('Job history deleted successfully');
          let index = this.experiences?.indexOf(jobHistory);
          if (index !== undefined && index !== -1) {
            this.experiences?.splice(index, 1);
          }
        },
        error: (error) => {
          console.error('Error deleting job history', error);
        },
      });
    }
  }

}
