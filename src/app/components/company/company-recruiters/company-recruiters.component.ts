import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { CommonModule, NgFor } from '@angular/common';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatIcon } from '@angular/material/icon';
import { FlexLayoutServerModule } from '@angular/flex-layout/server';
import { UserDto } from '../../../services/models';
import { MatDialog } from '@angular/material/dialog';
import { SearchService } from '../../../services/services';
import { ActivatedRoute } from '@angular/router';

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
  recruiters: UserDto[] | undefined;
  error: any;
  id: any;
  constructor(public dialog: MatDialog, private searchService: SearchService, private route: ActivatedRoute) { };

  ngOnInit(): void {
    console.log('Company page initialized');
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    const param = {
      endpoint: 'users',
      criteria: {
        "role": "COMPANY_REPRESENTATIVE",
        "jobHistories.company.id": this.id
      }
    };
    this.searchService.search(param).subscribe({
      next: (users: UserDto[]) => {
        this.recruiters = users;
        console.log('Recruiters:', users);
      },
      error: (error: any) => {
        this.error = error;
        console.error('Error fetching review:', error);
      },
      complete: () => {
        console.log('Completed fetching review');
      }
    });
  }
}
