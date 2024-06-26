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
import {ExperienceDto, JobHistoryDto, LanguageDto, ReferralDto, SpecializationDto, UserDto, UserSkillsDto} from '../../services/models';
import {UserService} from '../../services/services/user.service';
import {NgIf} from '@angular/common';
import {SearchService} from '../../services/services';
import {ActivatedRoute} from '@angular/router';
import {EducationComponent} from "../../components/education/education.component";


@Component({
  selector: 'app-user',
  standalone: true,
  templateUrl: './user.component.html',
  styleUrl: './user.component.scss',
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
export class UserComponent implements OnInit {
  user: UserDto = {} as UserDto;
  error: any = null;
  jobHistories: JobHistoryDto[] = [];
  experiences: ExperienceDto[] = [];
  skills: UserSkillsDto[] = [];
  languages: LanguageDto[] = [];
  referrals: ReferralDto[] = [];
  id: any;
  specializations: SpecializationDto[] =[];
  
  constructor(private userService: UserService, private searchService: SearchService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    console.log('ProfileComponent initialized');
    this.getCurrentUser();
  }

  private getCurrentUser(): void {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    const param = {id: this.id};
    this.userService.findUserById(param).subscribe({
      next: (user: UserDto) => {
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
          this.jobHistories = jobHistory.sort((a, b) => this.parseDate(b.startDate).getTime() - this.parseDate(a.startDate).getTime());
          ;
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
    if (this.user.id) {
      console.log('Userul este:', this.user);
      const param = {
        endpoint: "referrals",
        criteria: {
          "student.id": this.user.id
        }
      };
      this.searchService.search(param).subscribe({
        next: (referrals: ReferralDto[]) => {
          this.referrals = referrals;
          console.log('User:', this.user);
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

  private parseDate(dateStr: string): Date {
    const [day, month, year] = dateStr.split('.').map(Number);
    return new Date(year, month - 1, day);
  };
}
