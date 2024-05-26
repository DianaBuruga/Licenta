import {Component, Input} from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {CommonModule, NgFor} from '@angular/common';
import {FlexLayoutModule} from '@angular/flex-layout';
import {MatIcon} from '@angular/material/icon';
import { FlexLayoutServerModule } from '@angular/flex-layout/server';
import { UserDto } from '../../../services/models';

@Component({
  selector: 'app-company-recruiters',
  standalone: true,
  imports: [
    MatCardModule,
    CommonModule,
    NgFor,
    FlexLayoutModule,
    FlexLayoutServerModule,
    MatIcon],
  templateUrl: './company-recruiters.component.html',
  styleUrl: './company-recruiters.component.scss'
})
export class CompanyRecruitersComponent {
recruiters : UserDto[] | undefined;
}
