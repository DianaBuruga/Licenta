// profile-card.component.ts
import { Component, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { SkillChartComponent } from '../skill-chart/skill-chart.component';
import { BackgroundAnimationComponent } from '../background-animation/background-animation.component';
import { UserDto } from '../../services/models/user-dto';
import { OnInit } from '@angular/core';

@Component({
  selector: 'app-profile-card',
  standalone: true,
  templateUrl: './profile-card.component.html',
  styleUrls: ['./profile-card.component.scss'],
  imports: [
    MatCardModule, 
    MatButtonModule,
    SkillChartComponent,
    BackgroundAnimationComponent
  ],
})
export class ProfileCardComponent implements OnInit{
  @Input() profileCardUser: UserDto = {} as UserDto;
  
  ngOnInit(): void {
    //this.profileCardUser2 = this.profileCardUser;
    console.log('ProfileCardUser2', this.profileCardUser);
  }
}
