import {Component, Input, OnInit} from '@angular/core';
import {FlexLayoutServerModule} from '@angular/flex-layout/server';
import {FlexLayoutModule} from '@angular/flex-layout';
import {MatCardModule} from '@angular/material/card';
import {CommonModule, NgFor} from '@angular/common';
import {UserDto} from '../../services/models';
import {UserService} from '../../services/services';
import {Router} from '@angular/router';
import {MatButtonModule} from '@angular/material/button';
import {SearchComponent} from "../../components/search/search.component";

@Component({
  selector: 'app-applicants',
  standalone: true,
  imports: [FlexLayoutServerModule, FlexLayoutModule, MatCardModule, CommonModule, NgFor, MatButtonModule, SearchComponent],
  templateUrl: './applicants.component.html',
  styleUrl: './applicants.component.scss'
})
export class ApplicantsComponent {
  @Input() users: UserDto[] = [];
  
  constructor(private router: Router) {
  }

  navigateWithParam(id: string | undefined) {
    if (id) {
      this.router.navigate(['/user', id]);
    }
  }
}
