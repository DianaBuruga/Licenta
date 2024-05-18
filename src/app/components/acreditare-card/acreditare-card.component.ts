import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { NgFor } from '@angular/common';
import { FlexLayoutModule } from '@angular/flex-layout';

export interface Acreditare {
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
    FlexLayoutModule
  ],
  templateUrl: './acreditare-card.component.html',
  styleUrl: './acreditare-card.component.scss'
})
export class AcreditareCardComponent {
  acreditari: Acreditare[] = [{
    title: 'Acreditare 1',
    description: 'Description of acreditare 1',
    date: '18-04-2024',
    url: 'https://github.com/',
    type: 'ACREDITARE',
    image: 'path/to/image1.jpg'
  },
  {
    title: 'Acreditare 2',
    description: 'Description of acreditare 2',
    date: '18-04-2024',
    url: 'https://github.com/',
    type: 'ACREDITARE',
    image: 'path/to/image1.jpg'
  }
  ]
}
