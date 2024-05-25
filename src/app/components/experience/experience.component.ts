import { Component, Input, OnInit } from '@angular/core';
import {MatCard, MatCardTitle, MatCardContent} from '@angular/material/card';
import {CommonModule, NgFor} from '@angular/common';
import { MatIcon } from '@angular/material/icon';
import { AddFormDialogComponent } from '../add-form-dialog/add-form-dialog.component';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { JobHistoryDto, UserDto} from '../../services/models';
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
export class ExperienceComponent implements OnInit{
  @Input() userExperiences: UserDto | undefined;
  experiences: JobHistoryDto[] | undefined;
  emptyJobHistory: JobHistoryDto = {} as JobHistoryDto;
  ngOnInit(): void {
    this.experiences = this.userExperiences?.postedJobs;
    console.log('Experiences', this.experiences);
    this.emptyJobHistory = {
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
      user: this.userExperiences as UserDto
    };
  }

  constructor(public dialog: MatDialog, private jobHistoryService: JobHistoryService) {}
  openDialog(jobHistory: JobHistoryDto): void {
    const dialogRef = this.dialog.open(AddFormDialogComponent, {
      width: '50%',
      data: {user: this.userExperiences, job: jobHistory}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog was closed');
    });
  }
    deleteJobExperience(id: string | undefined): void {
      console.log('Deleting Job History', id);

      if(id !== undefined) {
        const params = { id: id };
        this.jobHistoryService.deleteJobHistory(params).subscribe({
          next: () => {
            console.log('Job history deleted successfully');
          },
          error: (error) => {
            console.error('Error deleting job history', error);
          },
        });
      }
    }

}
