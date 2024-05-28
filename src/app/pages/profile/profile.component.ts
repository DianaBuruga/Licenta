import {Component, OnInit} from '@angular/core';
import {FirstComponentComponent} from '../../components/first-component/first-component.component';
import {ProfileCardComponent} from '../../components/profile-card/profile-card.component';
import {ExperienceComponent} from '../../components/experience/experience.component';
import {ProjectsCarouselComponent} from '../../components/projects-carousel/projects-carousel.component';
import {SkillCircularProgressbarComponent} from '../../components/skill-circular-progressbar/skill-circular-progressbar.component';
import {LanguageTableComponent} from '../../components/language-table/language-table.component';
import {AcreditareCardComponent} from '../../components/acreditare-card/acreditare-card.component';
import {ReferalComponent} from '../../components/referal/referal.component';
import {UserDto} from '../../services/models';
import {UserService} from '../../services/services/user.service';
import { Observer } from 'rxjs';
import { NgIf } from '@angular/common';

import { MyHttpService } from '../../my-http.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    FirstComponentComponent,
    ProfileCardComponent,
    ExperienceComponent,
    ProjectsCarouselComponent,
    SkillCircularProgressbarComponent,
    LanguageTableComponent,
    AcreditareCardComponent,
    ReferalComponent,
    NgIf
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent implements OnInit {
  constructor( private userService: UserService, private http: MyHttpService) { };
token: string = '';
  ngOnInit(): void {
    this.getCurrentUser();
  }
  user: UserDto = {} as UserDto;
  response = 'string';
  error: any = null;
  private getCurrentUser(): void {
    const observer: Observer<UserDto> = {
      next: (user: UserDto) => {
        this.user = user;
        console.log('User', user);
      },
      error: (error: any) => {
        this.error = error;
        console.error('Error fetching user:', error);
      },
      complete: () => {
        console.log('Completed fetching user');
      }
    };
    this.userService.getAuthenticatedUser().subscribe(observer);
  }


}
