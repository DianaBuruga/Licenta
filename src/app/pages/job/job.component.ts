import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService, JobCandidatesService, PostedJobService, SearchService, UserService } from '../../services/services';
import { IsOwner, JobCandidatesDto, PostedJobDto, Role, UserDto } from '../../services/models';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { MatChipsModule } from '@angular/material/chips';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { PostedJobOpenDialogComponent } from '../../components/company/posted-job-open-dialog/posted-job-open-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { BehaviorSubject } from 'rxjs';
import { IsOwner$Params } from '../../services/fn/authentication/is-owner';
import { ApplicantsComponent } from "../../components/applicants/applicants.component";

@Component({
    selector: 'app-job',
    standalone: true,
    templateUrl: './job.component.html',
    styleUrl: './job.component.scss',
    imports: [
        CommonModule,
        MatCardModule,
        MatChipsModule,
        MatButtonModule,
        MatIconModule,
        ApplicantsComponent
    ]
})
export class JobComponent {
  id: any;
  error: any;
  job: PostedJobDto = {} as PostedJobDto;
  currentUser : UserDto = {} as UserDto;
  users: UserDto[] = [];
  wasNotPressed = true;
  private isOwnerSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private isSudentSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  constructor(private jobService: PostedJobService, private route: ActivatedRoute, private router: Router, private dialog: MatDialog, private jobCandidatesService: JobCandidatesService, private authService: AuthenticationService, private searchService: SearchService, private userService: UserService) { }

  ngOnInit(): void {
    console.log('ProfileComponent initialized');
    this.getCurrentJob();
    this.checkIfStudent();
    this.getCurrentUser();
  }
  private getCurrentJob(): void {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id');
    });
    const param = { id: this.id };
    this.jobService.findPostedJobById(param).subscribe({
      next: (job: PostedJobDto) => {
        this.job = job;
        console.log('Posted Job:', job);
        if(job.id){
        this.checkIfOwner(job);
        this.getApplicants(job.id);
        }
      },
      error: (error: any) => {
        this.error = error;
        console.error('Error fetching job:', error);
      },
      complete: () => {
        console.log('Completed fetching job');
      }
    });
    
  }

  getCurrentUser(): void {
    this.userService.getAuthenticatedUser().subscribe({
      next: (user: UserDto) => {
        this.currentUser = user;
        this.hasAlreadyApplied();
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

  getApplicants(id:string): void{
    const param = {
      endpoint: 'users',
      criteria: {
        "jobCandidates.postedJob.id": id
      }
    };
    this.searchService.search(param).subscribe({
      next: (users: UserDto[]) => {
        this.users = users;
        console.log('Users:', users);
      },
      error: (error: any) => {
        this.error = error;
        console.error('Error fetching users:', error);
      },
      complete: () => {
        console.log('Completed fetching users');
      }
    });
  }

  navigateWithParam(id: string | undefined) {
    if (id) {
      this.router.navigate(['/user', id]);
    }
  }
  
  openDialog(job: PostedJobDto): void {
    const dialogRef = this.dialog.open(PostedJobOpenDialogComponent, {
      width: '50%',
      data: { companyId: this.id, job: job }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Result', result);
      this.job = result.job;
      console.log('Dialog was closed');
    });
  }

  applyJob(job: PostedJobDto): void {
    this.wasNotPressed = false;
    const param = {
      body: job
    }
    this.jobCandidatesService.saveJobCandidates(param).subscribe({
      next: (job: JobCandidatesDto) => {
        console.log('JobCandidates:', job);
      },
      error: (error: any) => {
        console.error('Error fetching job:', error);
      },
      complete: () => {
        console.log('Completed fetching job');
      }
    });
  }
  
  get isOwner$() {
    return this.isOwnerSubject.asObservable();
  }

  get pressedButton(): boolean {
    return this.wasNotPressed;
  }

  hasAlreadyApplied(): void {
    if(this.currentUser?.id){
      console.log('wasNotPressed:', this.wasNotPressed);
      this.wasNotPressed = this.users.map(user => user.id).includes(this.currentUser.id);
      console.log('wasNotPressed:', this.wasNotPressed);
    }
  }

  checkIfOwner(job: PostedJobDto): void {
    if(job.user?.id){
    const param = {
      id: job.user.id,
      endpoint: "users"
    } as IsOwner$Params;

    this.authService.isOwner(param).subscribe({
      next: (result: IsOwner) => {
        console.log('Result received:', result);
        this.isOwnerSubject.next(result.isOwner? true : false);
      },
      error: (error: any) => {
        console.error('Error:', error);
      }
    });
  }
  }

  get isStudent$() {
    return this.isSudentSubject.asObservable();
  }

  checkIfStudent(): void {
    this.authService.getUserRole().subscribe({
      next: (result: Role) => {
        console.log('Result received:', result.role);
        this.isSudentSubject.next(result.role==='STUDENT');
      },
      error: (error: any) => {
        console.error('Error:', error);
      }
    });
  }
}
