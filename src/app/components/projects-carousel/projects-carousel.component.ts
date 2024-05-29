import { Component, Input, OnInit } from '@angular/core';
import {CommonModule, NgFor} from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIcon } from '@angular/material/icon';
import { ExperienceDto, UserDto } from '../../services/models';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { ExperienceService } from '../../services/services';
import { ProjectsFormDialogComponent } from '../projects-form-dialog/projects-form-dialog.component';
import e from 'express';
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
export class ProjectsCarouselComponent{
  @Input() userExperiences: UserDto | undefined;
  currentIndex = 0;

  get experiences(): ExperienceDto[] {
    return this.userExperiences?.experiences?.filter((experience) => experience.type != 'ACCREDITATION') ?? [];
  };

  get emptyExperience(): ExperienceDto {
    return {
    id: '',
    title: '',
    description: '',
    date: '',
    url: '',
    type: 'PROJECT',
    userDTO: this.userExperiences
  } as ExperienceDto;
  };

  constructor(public dialog: MatDialog, private experienceService: ExperienceService) {}


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

openDialog(experience: ExperienceDto): void {
  const dialogRef = this.dialog.open(ProjectsFormDialogComponent, {
    width: '50%',
    data: {user: this.userExperiences, newExperience: experience}
  });

  dialogRef.afterClosed().subscribe(result => {
    console.log('Result',result);
      if(result && this.userExperiences)
        {
          const length = this.userExperiences.experiences?.length;
          if(this.userExperiences.experiences === undefined){
            this.userExperiences.experiences = [];
          }
          this.userExperiences.experiences.push(result);
          console.log('Projects and Competitions', this.experiences);
        }
        this.currentIndex = this.experiences?.indexOf(result) ?? 0;
      console.log('Dialog was closed');
  });
}
  deleteExperience(experience: ExperienceDto): void {
    console.log('Deleting Job History', experience.id);

    if(experience.id !== undefined) {
      const params = { id: experience.id };
      this.experienceService.deleteExperience(params).subscribe({
        next: () => {
          console.log('Experience deleted successfully');
          let index = this.userExperiences?.experiences?.indexOf(experience);
          if (index !== undefined && index !== -1) {
            this.userExperiences?.experiences?.splice(index, 1);
          }
          if(this.userExperiences){
            if(this.userExperiences?.experiences === undefined){
                this.userExperiences.experiences = [];
              }
            this.showPrev();
          }
        },
        error: (error) => {
          console.error('Error deleting experience', error);
        },
      });
    }
  }

  onImageError(event: any, type: string) {
    if(type === 'PROJECT'){
    event.target.src = 'assets/project.jpg';
    }
    else
    {
      event.target.src = 'assets/competition.jpg';
    }
  }
}
