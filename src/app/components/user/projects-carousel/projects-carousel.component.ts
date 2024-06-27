import {Component, Input, SimpleChanges} from '@angular/core';
import {CommonModule, NgFor} from '@angular/common';
import {MatCardModule} from '@angular/material/card';
import {MatIcon} from '@angular/material/icon';
import {ExperienceDto, IsOwner, UserDto} from '../../../services/models';
import {MatDialog, MatDialogModule} from '@angular/material/dialog';
import {AuthenticationService, ExperienceService} from '../../../services/services';
import {ProjectsFormDialogComponent} from '../projects-form-dialog/projects-form-dialog.component';
import {MatChipsModule} from '@angular/material/chips';
import Swal from 'sweetalert2';
import { BehaviorSubject } from 'rxjs';
import { IsOwner$Params } from '../../../services/fn/authentication/is-owner';

@Component({
  selector: 'app-projects-carousel',
  standalone: true,
  imports: [
    MatCardModule,
    CommonModule,
    NgFor,
    MatIcon,
    MatDialogModule,
    MatChipsModule
  ],
  templateUrl: './projects-carousel.component.html',
  styleUrl: './projects-carousel.component.scss'
})
export class ProjectsCarouselComponent {
  @Input() userExperiences: UserDto | undefined;
  @Input() experiencesArray: ExperienceDto[] | undefined;
  currentIndex = 0;
  private isOwnerSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  
  ngOnChanges(changes: SimpleChanges) {
    if(this.userExperiences !== undefined) {
    if (this.userExperiences.id) {
      this.checkIfOwner(this.userExperiences.id, 'users');
    }
  }
  }

  types = ['All', 'PROJECT', 'COMPETITION'];
  selectedType = 'All';

  selectType(type: string) {
    this.selectedType = type;
    this.currentIndex = 0;
  }

  get experiences(): ExperienceDto[] {
    switch (this.selectedType) {
      case 'All':
        return this.experiencesArray?.filter((experience) => experience.type != 'ACCREDITATION').sort((a, b) => this.parseDate(b.date).getTime() - this.parseDate(a.date).getTime()) ?? [];
      case 'PROJECT':
        return this.experiencesArray?.filter((experience) => experience.type === 'PROJECT').sort((a, b) => this.parseDate(b.date).getTime() - this.parseDate(a.date).getTime()) ?? [];
      case 'COMPETITION':
        return this.experiencesArray?.filter((experience) => experience.type === 'COMPETITION').sort((a, b) => this.parseDate(b.date).getTime() - this.parseDate(a.date).getTime()) ?? [];
      default:
        return this.experiencesArray?.filter((experience) => experience.type != 'ACCREDITATION').sort((a, b) => this.parseDate(b.date).getTime() - this.parseDate(a.date).getTime()) ?? [];
    }
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

  constructor(public dialog: MatDialog, private experienceService: ExperienceService, private authService: AuthenticationService) {
  }


  showNext(): void {
    if (this.experiences === undefined) return;
    if (this.currentIndex < this.experiences.length - 1) {
      this.currentIndex++;
    } else {
      this.currentIndex = 0;
    }
  }

  showPrev(): void {
    if (this.experiences === undefined) return;
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
      console.log('Result', result);
      if (result.experience && this.userExperiences) {
        Swal.fire({
          icon: "success",
          title: "Information was successfully saved",
          showConfirmButton: false,
          timer: 1500
        });
        const index = this.experiencesArray?.findIndex(x => x.id === result.experience.id);
        if (index !== -1 && index !== undefined && index !== null && this.experiencesArray !== undefined) {
          this.experiencesArray[index] = result.experience;
          experience = result.experience;
          this.showExperience(index);
        } else {
          this.experiencesArray?.push(result.experience);
        }
        this.experiencesArray = [...this.experiencesArray ?? []];
        this.showExperience(this.currentIndex);
        console.log('Projects and Competitions', this.experiences);
      }
      this.currentIndex = this.experiences?.indexOf(result) ?? 0;
      console.log('Dialog was closed');
    });
  }

  deleteExperience(experience: ExperienceDto): void {
    console.log('Deleting Experience', experience.id);
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
        if (experience.id !== undefined) {
          const params = {id: experience.id};
          this.experienceService.deleteExperience(params).subscribe({
            next: () => {
              console.log('Experience deleted successfully');
              let index = this.experiencesArray?.indexOf(experience);
              if (index !== undefined && index !== -1) {
                this.experiencesArray?.splice(index, 1);
                this.showPrev();
              }
            },
            error: (error) => {
              console.error('Error deleting experience', error);
            },
          });
        }
      }
    });
  }

  onImageError(event: any, type: string) {
    if (type === 'PROJECT') {
      event.target.src = 'assets/project.jpg';
    } else {
      event.target.src = 'assets/competition.jpg';
    }
  }

  private parseDate(dateStr: string): Date {
    const [day, month, year] = dateStr.split('.').map(Number);
    return new Date(year, month - 1, day);
  };

  get isOwner$() {
    return this.isOwnerSubject.asObservable();
  }

  checkIfOwner(id: string, endpoint: string): void {
    const param = {
      id: id,
      endpoint: endpoint
    } as IsOwner$Params;

    this.authService.isOwner(param).subscribe({
      next: (result: IsOwner) => {
        console.log('Result received:', result);
        this.isOwnerSubject.next(result.isOwner? true : false);
      },
      error: (error: any) => {
        console.error('Error:', error);
      }
    });
  }
}
