import { Component, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { CommonModule, NgFor } from '@angular/common';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatIcon } from '@angular/material/icon';
import { ExperienceDto, UserDto } from '../../services/models';
import { AcreditationFormDialogComponent } from '../acreditation-form-dialog/acreditation-form-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { ExperienceService } from '../../services/services';
import { FlexLayoutServerModule } from '@angular/flex-layout/server';

export interface Acreditare {
  id: any;
  title: string;
  description: string;
  date: string;
  url: string;
  type: string;
  image: string;
}

@Component({
  selector: 'app-acreditare-card',
  standalone: true,
  imports: [
    MatCardModule,
    CommonModule,
    NgFor,
    FlexLayoutModule,
    FlexLayoutServerModule,
    MatIcon
  ],
  templateUrl: './acreditare-card.component.html',
  styleUrl: './acreditare-card.component.scss'
})
export class AcreditareCardComponent {

  onError(event: any) {
    const defaultImage = 'assets/acreditation.png';
    event.target.src = defaultImage;
  }

  @Input() userExperiences: UserDto | undefined;
  @Input() experiencesArray: ExperienceDto[] | undefined;

  get emptyExperience(): ExperienceDto {
    return {
      id: '',
      title: '',
      description: '',
      date: '',
      url: '',
      type: 'ACCREDITATION',
      userDTO: this.userExperiences as UserDto
    } as ExperienceDto;
  }

  get experiences(): ExperienceDto[] {
    return this.experiencesArray?.filter((experience) => experience.type === 'ACCREDITATION') ?? [];
  };

  constructor(public dialog: MatDialog, private experienceService: ExperienceService) { }

  openDialog(experience: ExperienceDto): void {
    const dialogRef = this.dialog.open(AcreditationFormDialogComponent, {
      width: '50%',
      data: { user: this.userExperiences, newExperience: experience }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Result', result);
      if (result.accreditation && this.userExperiences) {
        console.log('am ajuns aici')
        let index = this.experiencesArray?.indexOf(experience);
        if (index !== -1 && index !== undefined && index !== null && this.experiencesArray !== undefined) {
          this.experiencesArray[index] = result.accreditation;
        } else {
          this.experiencesArray?.push(result.accreditation);
        }
        this.experiencesArray = [...this.experiencesArray ?? []];
      }
      console.log('Acreditations', this.experiences);
      console.log('Dialog was closed');
    });
  }

  deleteExperience(experience: ExperienceDto): void {
    console.log('Deleting Job History', experience.id);

    if (experience.id !== undefined) {
      const params = { id: experience.id };
      this.experienceService.deleteExperience(params).subscribe({
        next: () => {
          console.log('Experience deleted successfully');
          let index = this.experiencesArray?.indexOf(experience);
          if (index !== undefined && index !== -1) {
            this.experiencesArray?.splice(index, 1);
          }
          this.experiencesArray = [...this.experiencesArray ?? []];
        },
        error: (error) => {
          console.error('Error deleting experience', error);
        },
      });
    }
  }
}
