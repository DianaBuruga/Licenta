import { Component, ViewEncapsulation } from '@angular/core';
import { SwiperModule } from 'swiper/angular';
import SwiperCore, { Navigation, Pagination, EffectCoverflow } from 'swiper';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { NgFor } from '@angular/common';

SwiperCore.use([Navigation, Pagination, EffectCoverflow]);
interface User {
  name: string;
  email: string;
  role: string;
  image: string;
}

interface Referal {
  user: User;
  description: string;
}

@Component({
  selector: 'app-referal',
  standalone: true,
  imports: [
    SwiperModule,
    MatCardModule,
    CommonModule,
    NgFor,
  ],
  templateUrl: './referal.component.html',
  styleUrl: './referal.component.scss',
  encapsulation: ViewEncapsulation.None
})
export class ReferalComponent {
  referals: Referal[] = [
    {
      user: {
        name: 'David Dell',
        email: 'david@example.com',
        role: 'Developer',
        image: 'assets/images/david.jpg'
      },
      description: 'David is a key developer in the tech department.'
    },
    {
      user: {
        name: 'Samantha Peak',
        email: 'samantha@example.com',
        role: 'Designer',
        image: 'assets/images/samantha.jpg'
      },
      description: 'Samantha is a creative designer with 5 years of experience.'
    },
    {
      user: {
        name: 'Michael Doe',
        email: 'michael@example.com',
        role: 'Manager',
        image: 'assets/images/michael.jpg'
      },
      description: 'Michael manages the operations team efficiently.'
    }
  ];
}
