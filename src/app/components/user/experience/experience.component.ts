import {Component, Input} from '@angular/core';
import {MatCard, MatCardTitle, MatCardContent} from '@angular/material/card';
import {CommonModule, NgFor} from '@angular/common';
import {MatIcon} from '@angular/material/icon';
import {AddFormDialogComponent} from '../add-form-dialog/add-form-dialog.component';
import {MatDialog, MatDialogModule} from '@angular/material/dialog';
import {JobHistoryDto, UserDto} from '../../../services/models';
import {JobHistoryService} from '../../../services/services';
import Swal from 'sweetalert2';

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

  get sortedExperiences(): JobHistoryDto[] {
    return this.experiences?.sort((a, b) => this.parseDate(b.startDate).getTime() - this.parseDate(a.startDate).getTime()) ?? [];
  }

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
      data: {user: this.userExperiences, job: jobHistory}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Result', result);
      if (result.jobHistory !== undefined && this.userExperiences) {
        Swal.fire({
          icon: "success",
          title: "Information was successfully saved",
          showConfirmButton: false,
          timer: 1500
        });
        let index = this.experiences?.findIndex(x => x.id === jobHistory.id);
        if (index !== -1 && index !== undefined && index !== null && this.experiences !== undefined) {
          this.experiences[index] = result.jobHistory;
        } else {
          this.experiences?.push(result.jobHistory);
        }
        this.experiences = [...this.experiences?.sort((a, b) => this.parseDate(b.startDate).getTime() - this.parseDate(a.startDate).getTime()) ?? []];
        console.log('JobHistory', this.experiences);
      }
      console.log('Dialog was closed');
    });
  }

  deleteJobExperience(jobHistory: JobHistoryDto): void {
    console.log('Deleting Job History', jobHistory.id);
    Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3f51b5",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!"
    }).then((result) => {
      if (result.isConfirmed) {
        if (jobHistory.id !== undefined) {
          const params = {id: jobHistory.id};
          this.jobHistoryService.deleteJobHistory(params).subscribe({
            next: () => {
              console.log('Job history deleted successfully');
              let index = this.experiences?.indexOf(jobHistory);
              if (index !== undefined && index !== -1) {
                this.experiences?.splice(index, 1);
                Swal.fire({
                  title: "Deleted!",
                  text: "Your file has been deleted.",
                  icon: "success"
                });
              }
            },
            error: (error) => {
              console.error('Error deleting job history', error);
              Swal.fire({
                title: "Cancelled!",
                text: "Something went wrong, please try again later.",
                icon: "error"
              });
            },
          });
        }
      }
    });

  }

  private parseDate(dateStr: string): Date {
    const [day, month, year] = dateStr.split('.').map(Number);
    return new Date(year, month - 1, day + 1);
  };

}
