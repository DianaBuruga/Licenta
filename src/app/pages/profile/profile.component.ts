import {Component, OnInit} from '@angular/core';
import {FirstComponentComponent} from '../../components/first-component/first-component.component';
import {ProfileCardComponent} from '../../components/user/profile-card/profile-card.component';
import {ExperienceComponent} from '../../components/user/experience/experience.component';
import {ProjectsCarouselComponent} from '../../components/user/projects-carousel/projects-carousel.component';
import {
  SkillCircularProgressbarComponent
} from '../../components/user/skill-circular-progressbar/skill-circular-progressbar.component';
import {LanguageTableComponent} from '../../components/user/language-table/language-table.component';
import {AcreditareCardComponent} from '../../components/user/acreditare-card/acreditare-card.component';
import {ReferalComponent} from '../../components/user/referal/referal.component';
import {
  ExperienceDto,
  JobHistoryDto,
  LanguageDto,
  ReferralDto,
  SpecializationDto,
  UserDto,
  UserSkillsDto
} from '../../services/models';
import {UserService} from '../../services/services/user.service';
import {NgIf} from '@angular/common';
import {SearchService} from '../../services/services';
import {EducationComponent} from "../../components/education/education.component";
import { Router, ActivatedRoute} from '@angular/router';
import { TokenService } from '../../services/auth/token.service';
import { MessageService } from '../../services/services/message.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'] // Corrected from 'styleUrl' to 'styleUrls'
  ,
  imports: [
    FirstComponentComponent,
    ProfileCardComponent,
    ExperienceComponent,
    ProjectsCarouselComponent,
    SkillCircularProgressbarComponent,
    LanguageTableComponent,
    AcreditareCardComponent,
    ReferalComponent,
    NgIf,
    EducationComponent
  ]
})
export class ProfileComponent implements OnInit {
  user: UserDto = {} as UserDto;
  error: any = null;
  jobHistories: JobHistoryDto[] = [];
  experiences: ExperienceDto[] = [];
  skills: UserSkillsDto[] = [];
  languages: LanguageDto[] = [];
  referrals: ReferralDto[] = [];
  specializations: SpecializationDto[] = [];
  isVisible=false;



  constructor(private userService: UserService, private searchService: SearchService, private router: Router, private route: ActivatedRoute, private tokenService: TokenService,  private messageService: MessageService) {
    const token = this.tokenService.token;
    this.route.queryParams.subscribe(params => {
      const code = params['code'];
      
      if (!code) {
        if(this.tokenService.token === undefined || this.tokenService.token ===''){
          console.log('You are not authorized! Redirecting to login page.');
          console.log(this.tokenService.token !== undefined || this.tokenService.token!='')
          this.router.navigate(['login']);
        }
      }
    });
  }

  ngOnInit(): void {
    console.log('ProfileComponent initialized');
    this.getCurrentUser();
    this.getJobHistory();

  }

  private getCurrentUser(): void {
    this.userService.getAuthenticatedUser().subscribe({
      next: (user: UserDto) => {
        this.isVisible=true;
        this.user = user;
        this.getJobHistory();
        this.getExperiences();
        this.getSkill();
        this.getLanguages();
        this.getReferrals();
        this.getSpecializations();
        console.log('User:', user);
      },
      error: (error: any) => {
        this.error = error;
        this.router.navigate(['login']);
        console.error('Error fetching user:', error);
      },
      complete: () => {
        console.log('Completed fetching user');
      }
    });
  }

  private getJobHistory(): void {
    if (this.user.id) {
      console.log('Userul este:', this.user);
      const param = {
        endpoint: "jobHistories",
        criteria: {
          "user.id": this.user.id
        }
      };
      this.searchService.search(param).subscribe({
        next: (jobHistory: JobHistoryDto[]) => {
          this.jobHistories = jobHistory;
          console.log('JobHistories:', this.jobHistories);
        },
        error: (error: any) => {
          this.error = error;
          console.error('Error fetching user:', error);
        },
        complete: () => {
          console.log('Completed fetching user');
        }
      });
    }
  }

  private getSpecializations(): void {
    if (this.user.id) {
      console.log('Userul este:', this.user);
      const param = {
        endpoint: "specializations",
        criteria: {
          "user.id": this.user.id
        }
      };
      this.searchService.search(param).subscribe({
        next: (specializations: SpecializationDto[]) => {
          this.specializations = specializations;
          console.log('Specializations:', this.specializations);
        },
        error: (error: any) => {
          this.error = error;
          console.error('Error fetching specializations:', error);
        },
        complete: () => {
          console.log('Completed fetching specializations');
        }
      });
    }
  }

  private getExperiences(): void {
    if (this.user.id) {
      console.log('Userul este:', this.user);
      const param = {
        endpoint: "experiences",
        criteria: {
          "user.id": this.user.id
        }
      };
      this.searchService.search(param).subscribe({
        next: (experience: ExperienceDto[]) => {
          this.experiences = experience;
          console.log('Experiences:', this.experiences);
        },
        error: (error: any) => {
          this.error = error;
          console.error('Error fetching user:', error);
        },
        complete: () => {
          console.log('Completed fetching user');
        }
      });
    }
  }

  private getSkill(): void {
    if (this.user.id) {
      console.log('Userul este:', this.user);
      const param = {
        endpoint: "userSkills",
        criteria: {
          "user.id": this.user.id
        }
      };
      this.searchService.search(param).subscribe({
        next: (skills: UserSkillsDto[]) => {
          this.skills = skills;
          console.log('Skills:', this.skills);
        },
        error: (error: any) => {
          this.error = error;
          console.error('Error fetching user:', error);
        },
        complete: () => {
          console.log('Completed fetching user');
        }
      });
    }
  }

  private getLanguages(): void {
    if (this.user.id) {
      console.log('Userul este:', this.user);
      const param = {
        endpoint: "languages",
        criteria: {
          "user.id": this.user.id
        }
      };
      this.searchService.search(param).subscribe({
        next: (languages: LanguageDto[]) => {
          this.languages = languages;
          console.log('Languages:', this.languages);
        },
        error: (error: any) => {
          this.error = error;
          console.error('Error fetching user:', error);
        },
        complete: () => {
          console.log('Completed fetching user');
        }
      });
    }
  }

  private getReferrals(): void {
    if (!this.user.id) {
      return;
    }
  
    if (this.user.role === 'TEACHER' || this.user.role === 'STUDENT') {
      let criteria: { [key: string]: any } = {};
      
      if (this.user.role === 'TEACHER') {
        criteria = { "teacher.id": this.user.id };
      } else if (this.user.role === 'STUDENT') {
        criteria = { "student.id": this.user.id };
      }
  
      const param = {
        endpoint: "referrals",
        criteria: criteria
      };
  
      console.log('Userul este:', this.user);
  
      this.searchService.search(param).subscribe({
        next: (referrals: ReferralDto[]) => {
          this.referrals = referrals;
          console.log('Referrals:', this.referrals);
        },
        error: (error: any) => {
          this.error = error;
          console.error('Error fetching referrals:', error);
        },
        complete: () => {
          console.log('Completed fetching referrals');
        }
      });
    }
  }
}
