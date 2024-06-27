import {CommonModule, NgFor} from '@angular/common';
import {ChangeDetectorRef, Component, Input, SimpleChanges} from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {IsOwner, SpecializationDto, UserDto} from '../../services/models';
import Swal from 'sweetalert2';
import {AuthenticationService, SpecializationService} from '../../services/services';
import {MatDialog} from '@angular/material/dialog';
import {SpecializationOpenDialogComponent} from '../specialization-open-dialog/specialization-open-dialog.component';
import { BehaviorSubject } from 'rxjs';
import { IsOwner$Params } from '../../services/fn/authentication/is-owner';

@Component({
  selector: 'app-education',
  standalone: true,
  imports: [
    MatCardModule,
    MatIconModule,
    CommonModule,
    NgFor
  ],
  templateUrl: './education.component.html',
  styleUrl: './education.component.scss'
})
export class EducationComponent {
  @Input() user: UserDto | undefined;
  @Input() specializations: SpecializationDto[] = [];
  emptySpecialization: SpecializationDto = {} as SpecializationDto;
  private isOwnerSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  
  ngOnChanges(changes: SimpleChanges) {
    if(this.user !== undefined) {
    if (this.user.id) {
      this.checkIfOwner(this.user.id, 'users');
    }
  }
  }
  
  constructor(public dialog: MatDialog, private specializationService: SpecializationService, private cdr: ChangeDetectorRef, private authService: AuthenticationService) {
  }


  get sortedSpecializations(): SpecializationDto[] {
    return this.specializations?.sort((a, b) => this.parseDate(b.startedDate).getTime() - this.parseDate(a.startedDate).getTime()) ?? [];
  }

  openDialog(specialization: SpecializationDto): void {
    const dialogRef = this.dialog.open(SpecializationOpenDialogComponent, {
      width: '50%',
      data: {user: this.user, specialization: specialization}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Result', result);
      if (result && result.specialization !== undefined && this.user) {
        Swal.fire({
          icon: "success",
          title: "Information was successfully saved",
          showConfirmButton: false,
          timer: 1500
        });
        let index = this.specializations?.findIndex(x => x.id === specialization.id);
        if (index !== -1 && index !== undefined && index !== null && this.specializations !== undefined) {
          this.specializations[index] = result.specialization;
        } else {
          this.specializations?.push(result.specialization);
          this.cdr.detectChanges();
        }
      }
      console.log('Dialog was closed');
    });
  }

  deleteSpecialization(specialization: SpecializationDto): void {
    console.log('Deleting Specialization', specialization.id);
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
        if (specialization.id !== undefined) {
          const params = {"id": specialization.id};
          this.specializationService.deleteSpecialization(params).subscribe({
            next: () => {
              console.log('Job history deleted successfully');
              let index = this.specializations?.indexOf(specialization);
              if (index !== undefined && index !== -1) {
                this.specializations?.splice(index, 1);
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
