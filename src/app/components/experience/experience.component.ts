import { Component, Input, OnInit } from '@angular/core';
import { MatCard } from '@angular/material/card';
import { MatCardContent } from '@angular/material/card';
import { MatCardTitle } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { NgFor } from '@angular/common';
import { MatIcon } from '@angular/material/icon';
import { AddFormDialogComponent } from '../add-form-dialog/add-form-dialog.component';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { JobHistoryDto, UserDto } from '../../services/models';

interface Experience {
  year: string;
  title: string;
  description: string;
}

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
  ngOnInit(): void {
    this.experiences = this.userExperiences?.postedJobs;
    console.log('Experiences', this.experiences);
  }
  constructor(public dialog: MatDialog) {}
  openDialog(): void {
    const dialogRef = this.dialog.open(AddFormDialogComponent, {
      width: '50%',
      data: {user: this.userExperiences}
    });
  
    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog was closed');
    });
  }
  // experiences: Experience[] = [
  //   {
  //     year: '2019',
  //     title: 'FRONTED DEVELOPMENT',
  //     description: 'Maecenas finibus nec sem ut imperdiet...',
  //   },
  //   {
  //     year: '2014',
  //     title: 'GRAPHIC DESIGN',
  //     description: 'Maecenas finibus nec sem ut imperdiet...',
  //   }
  // ];
}
