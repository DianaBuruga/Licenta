import { Component, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { SkillChartComponent } from '../skill-chart/skill-chart.component';
import { UserDto } from '../../services/models/user-dto';
import { OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from '../../services/services';
import { ProfileOpenDialogComponent } from '../profile-open-dialog/profile-open-dialog.component';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-profile-card',
  standalone: true,
  templateUrl: './profile-card.component.html',
  styleUrls: ['./profile-card.component.scss'],
  imports: [
    MatCardModule,
    MatButtonModule,
    SkillChartComponent,
    MatIconModule
  ],
})
export class ProfileCardComponent implements OnInit {
  @Input() profileCardUser: UserDto = {} as UserDto;

  ngOnInit(): void {
    console.log('ProfileCardUser2', this.profileCardUser);
  }

  constructor(public dialog: MatDialog, private userService: UserService) {
  }

  openDialog(user: UserDto): void {
    const dialogRef = this.dialog.open(ProfileOpenDialogComponent, {
      width: '50%',
      data: { user: this.profileCardUser }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Result', result);
      if (result.user !== undefined) {
        this.profileCardUser = result.user;
        console.log('User', this.profileCardUser);
      }
      console.log('Dialog was closed');
    });
  }
}
