import { Component, OnInit } from '@angular/core';
import { FirstComponentComponent } from '../../components/first-component/first-component.component';
import { ProfileCardComponent } from '../../components/profile-card/profile-card.component';
import { ExperienceComponent } from '../../components/experience/experience.component';
import { ProjectsCarouselComponent } from '../../components/projects-carousel/projects-carousel.component';
import { SkillCircularProgressbarComponent } from '../../components/skill-circular-progressbar/skill-circular-progressbar.component';
import { LanguageTableComponent } from '../../components/language-table/language-table.component';
import { AcreditareCardComponent } from '../../components/acreditare-card/acreditare-card.component';
import { ReferalComponent } from '../../components/referal/referal.component';
import { ExperienceDto, JobHistoryDto, LanguageDto, ReferralDto, SearchCriteria, UserDto, UserSkillsDto } from '../../services/models';
import { UserService } from '../../services/services/user.service';
import { NgIf } from '@angular/common';
import { SearchService } from '../../services/services';

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
  styleUrls: ['./profile.component.scss'] // Corrected from 'styleUrl' to 'styleUrls'
})
export class ProfileComponent implements OnInit {
  user: UserDto = {} as UserDto;
  error: any = null;

  constructor(private userService: UserService, private searchService: SearchService) {}

  ngOnInit(): void {
    console.log('ProfileComponent initialized');
    this.getCurrentUser();
    this.getJobHistory();
    
  }

  private getCurrentUser(): void {
    this.userService.getAuthenticatedUser().subscribe({
      next: (user: UserDto) => {
        this.user = user;
        this.getJobHistory();
        this.getExperiences();
        this.getSkill();
        this.getLanguages();
        this.getReferrals();
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
    if(this.user.id){
      console.log('Userul este:', this.user);
    const param ={
      endpoint: "jobHistories",
      criteria: {
        "user.id": this.user.id
      }
    };
    this.searchService.search(param).subscribe({
      next: (jobHistory: JobHistoryDto[]) => {
        this.user.postedJobs = jobHistory;
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

private getExperiences(): void {
  if(this.user.id){
    console.log('Userul este:', this.user);
  const param ={
    endpoint: "experiences",
    criteria: {
      "user.id": this.user.id
    }
  };
  this.searchService.search(param).subscribe({
    next: (experience: ExperienceDto[]) => {
      this.user.experiences = experience;
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

private getSkill(): void {
  if(this.user.id){
    console.log('Userul este:', this.user);
  const param ={
    endpoint: "userSkills",
    criteria: {
      "user.id": this.user.id
    }
  };
  this.searchService.search(param).subscribe({
    next: (skills: UserSkillsDto[]) => {
      this.user.skills = skills;
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

private getLanguages(): void {
  if(this.user.id){
    console.log('Userul este:', this.user);
  const param ={
    endpoint: "languages",
    criteria: {
      "user.id": this.user.id
    }
  };
  this.searchService.search(param).subscribe({
    next: (languages: LanguageDto[]) => {
      this.user.languages = languages;
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
private getReferrals(): void {
  if(this.user.id){
    console.log('Userul este:', this.user);
  const param ={
    endpoint: "referrals",
    criteria: {
      "student.id": this.user.id
    }
  };
  this.searchService.search(param).subscribe({
    next: (referrals: ReferralDto[]) => {
      this.user.receivedReferrals = referrals;
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
}