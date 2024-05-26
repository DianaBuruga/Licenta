import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';
import { SwiperModule } from 'swiper/angular';
import SwiperCore, { Navigation, Pagination, EffectCoverflow } from 'swiper';
import { MatCardModule } from '@angular/material/card';
import {CommonModule, NgFor} from '@angular/common';
import { MatIcon } from '@angular/material/icon';
import { ReferralDto, UserDto } from '../../services/models';

SwiperCore.use([Navigation, Pagination, EffectCoverflow]);
interface User {
  name: string;
  email: string;
  role: string;
  image: string;
}

interface Referal {
id: any;
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
    MatIcon
  ],
  templateUrl: './referal.component.html',
  styleUrl: './referal.component.scss',
  encapsulation: ViewEncapsulation.None
})
export class ReferalComponent implements OnInit{
  @Input() user: UserDto | undefined;
  referals: ReferralDto[] | undefined;
  emptyReferal: ReferralDto = {} as ReferralDto;
  ngOnInit(): void {
    this.referals = this.user?.receivedReferrals;
    this.emptyReferal = {
      teacher: {
        name: '',
        email: '',
        role: 'TEACHER',
        description: '',
        phone: '',
        website: ''
      },
      student: {
        name: '',
        email: '',
        role: 'STUDENT',
        description: '',
        phone: '',
        website: ''
      },
      description: ''
    };
  }
openDialog(arg0: any) {
throw new Error('Method not implemented.');
}
deleteAcreditare(arg0: any) {
throw new Error('Method not implemented.');
}
  // referals: ReferralDto[] = [
  //   {
  //     id: '1',
  //     teacher: {
  //       name: 'David Dell',
  //       email: 'david@example.com',
  //       role: 'TEACHER',
  //       description: '',
  //       phone: '',
  //       website: ''
  //     },
  //     student: {
  //       description: '',
  //       email: '',
  //       name: '',
  //       phone: '',
  //       role: 'STUDENT',
  //       website: ''
  //     },
  //     description: 'David is a key developer in the tech department.'
  //   },
  //   {
  //     id: '2',
  //     teacher: {
  //       name: 'Samantha Peak',
  //       email: 'samantha@example.com',
  //       role: 'TEACHER',
  //       description: '',
  //       phone: '',
  //       website: ''
  //     },
  //     student: {
  //       description: '',
  //       email: '',
  //       name: '',
  //       phone: '',
  //       role: 'STUDENT',
  //       website: ''
  //     },
  //     description: 'Samantha is a creative designer with 5 years of experience.'
  //   },
  //   {
  //     id: '3',
  //     teacher: {
  //       name: 'Michael Doe',
  //       email: 'michael@example.com',
  //       role: 'TEACHER',
  //       description: '',
  //       phone: '',
  //       website: ''
  //     },
  //     student: {
  //       description: '',
  //       email: '',
  //       name: '',
  //       phone: '',
  //       role: 'STUDENT',
  //       website: ''
  //     },
  //     description: 'Michael manages the operations team efficiently.'
  //   }
  // ];
}
