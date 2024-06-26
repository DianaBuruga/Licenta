import {Component, OnInit} from '@angular/core';
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
  selector: 'app-users',
  standalone: true,
  templateUrl: './users.component.html',
  styleUrl: './users.component.scss',
  imports: [FlexLayoutServerModule, FlexLayoutModule, MatCardModule, CommonModule, NgFor, MatButtonModule, SearchComponent]
})
export class UsersComponent implements OnInit {
  processUsers(filteredUsers: UserDto[]) {
    console.log('Am primit userii:');
    this.users = filteredUsers;
  }

  users: UserDto[] = [];
  error: any;
  endpoint: string = 'users';

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit(): void {
    console.log('Users page initialized');
    this.userService.findAllUsers().subscribe({
      next: (users: UserDto[]) => {
        this.users = users;
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

  navigateWithParam(id: string | undefined) {
    if (id) {
      this.router.navigate(['/user', id]);
    }
  }
}
