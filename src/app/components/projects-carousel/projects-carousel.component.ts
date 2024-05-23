import { Component, Input, OnInit } from '@angular/core';
import {CommonModule, NgFor} from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIcon } from '@angular/material/icon';
import { ExperienceDto, UserDto } from '../../services/models';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { ExperienceService } from '../../services/services';
import { ProjectsFormDialogComponent } from '../projects-form-dialog/projects-form-dialog.component';
interface Experience {
  title: string;
  description: string;
  date: string;
  url: string,
  type: string,
  image: string,
}

@Component({
  selector: 'app-projects-carousel',
  standalone: true,
  imports: [
    MatCardModule,
    CommonModule,
    NgFor,
    MatIcon,
    MatDialogModule
],
  templateUrl: './projects-carousel.component.html',
  styleUrl: './projects-carousel.component.scss'
})
export class ProjectsCarouselComponent implements OnInit{
  @Input() userExperiences: UserDto | undefined;
  experiences: ExperienceDto[] | undefined;
  emptyExperience: ExperienceDto = {} as ExperienceDto;
  constructor(public dialog: MatDialog, private experienceService: ExperienceService) {}
  ngOnInit(): void {
    console.log('Projects', this.experiences);
    this.experiences = this.userExperiences?.experiences;
    this.emptyExperience = {
      id: '',
      title: '',
      description: '',
      date: '',
      url: '',
      type: 'PROJECT',
      userDTO: this.userExperiences as UserDto
    };
  }
  currentIndex = 0;

  showNext(): void {
    if(this.experiences === undefined) return;
    if (this.currentIndex < this.experiences.length - 1) {
      this.currentIndex++;
    } else {
      this.currentIndex = 0;
    }
  }

  showPrev(): void {
    if(this.experiences === undefined) return;
    if (this.currentIndex > 0) {
      this.currentIndex--;
    } else {
      this.currentIndex = this.experiences.length - 1;
    }
  }
  showExperience(index: number) {
    this.currentIndex = index;
}
onImageError(event: any) {
  event.target.style.display = 'none';
}
openDialog(experience: ExperienceDto): void {
  const dialogRef = this.dialog.open(ProjectsFormDialogComponent, {
    width: '50%',
    data: {user: this.userExperiences, newExperience: experience}
  });

  dialogRef.afterClosed().subscribe(result => {
    console.log('Dialog was closed');
  });
}
  deleteJobExperience(id: string | undefined): void {
    console.log('Deleting Job History', id);

    if(id !== undefined) {
      const params = { id: id };
      this.experienceService.deleteExperience(params).subscribe({
        next: () => {
          console.log('Experience deleted successfully');
        },
        error: (error) => {
          console.error('Error deleting experience', error);
        },
      });
    }
  }
}
