import { Component, OnInit } from '@angular/core';
import { FlexLayoutServerModule } from '@angular/flex-layout/server';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatCardModule } from '@angular/material/card';
import { CommonModule, NgFor } from '@angular/common';
import { UserDto } from '../../services/models';
import { UserService } from '../../services/services';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [FlexLayoutServerModule, FlexLayoutModule, MatCardModule, CommonModule, NgFor],
  templateUrl: './users.component.html',
  styleUrl: './users.component.scss'
})
export class UsersComponent implements OnInit {
  users: UserDto[] = [];
  error: any;

  constructor(private userService: UserService) { }
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
}
