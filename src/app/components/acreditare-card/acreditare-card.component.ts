import {Component} from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {CommonModule, NgFor} from '@angular/common';
import {FlexLayoutModule} from '@angular/flex-layout';
import {MatIcon} from '@angular/material/icon';

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
    MatIcon
  ],
  templateUrl: './acreditare-card.component.html',
  styleUrl: './acreditare-card.component.scss'
})
export class AcreditareCardComponent {
  deleteAcreditare(arg0: any) {
    throw new Error('Method not implemented.');
  }

  emptyAcreditation: any;

  openDialog(arg0: any) {
    throw new Error('Method not implemented.');
  }

  acreditari: Acreditare[] = [{
    id: '1',
    title: 'Acreditare 1',
    description: 'Description of acreditare 1',
    date: '18-04-2024',
    url: 'https://github.com/',
    type: 'ACREDITARE',
    image: 'path/to/image1.jpg'
  },
    {
      id: '2',
      title: 'Acreditare 2',
      description: 'Description of acreditare 2',
      date: '18-04-2024',
      url: 'https://github.com/',
      type: 'ACREDITARE',
      image: 'path/to/image1.jpg'
    }
  ]
}
