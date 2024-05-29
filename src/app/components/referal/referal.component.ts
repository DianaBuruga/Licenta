import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';
import { SwiperModule } from 'swiper/angular';
import SwiperCore, { Navigation, Pagination, EffectCoverflow } from 'swiper';
import { MatCardModule } from '@angular/material/card';
import {CommonModule, NgFor} from '@angular/common';
import { MatIcon } from '@angular/material/icon';
import { ReferralDto, UserDto } from '../../services/models';
import { MatDialog } from '@angular/material/dialog';
import { ReferalOpenDialogComponent } from '../referal-open-dialog/referal-open-dialog.component';
import { ReferralService } from '../../services/services';

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
export class ReferalComponent{
  @Input() user: UserDto | undefined;
  get referrals(): ReferralDto[]{
    return this.user?.receivedReferrals??[];
  };
 get emptyReferral(): ReferralDto{
  return {
    id: '',
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
  } as ReferralDto;
}


constructor(public dialog: MatDialog, private referalService: ReferralService) {}

openDialog(referral: ReferralDto): void {
  console.log('Opening dialog', this.user);
  const dialogRef = this.dialog.open(ReferalOpenDialogComponent, {
    width: '50%',
    data: {user: this.user, referral: referral}
  });

  dialogRef.afterClosed().subscribe(result => {
    console.log('Result',result);
    if(result && this.user)
      {
        this.user.receivedReferrals?.push(result.referral);
        console.log('Referral', this.referrals);
      }
    console.log('Dialog was closed');
  });
}

deleteReferral(referral: ReferralDto) {
console.log('Deleting Referral', referral.id); 
if (referral.id !== undefined) {
  const params = {id: referral.id };
  this.referalService.deleteReferral(params).subscribe({
    next: () => {
      let index = this.user?.receivedReferrals?.indexOf(referral);
      if (index !== undefined && index !== -1) {
        this.user?.receivedReferrals?.splice(index, 1);
      }
      console.log('Referral deleted successfully');
    },
    error: (error) => {
      console.error('Error deleting referral', error);
    },
  })  ;
}
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
