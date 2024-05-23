import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgFor } from '@angular/common';
import { MatIcon } from '@angular/material/icon';
import { SkillDto, UserDto, UserSkillsDto } from '../../services/models';
import { MatDialog } from '@angular/material/dialog';
interface Skill {
  name: string;
  percentage: number;
  color: string;
}

@Component({
  selector: 'app-skill-circular-progressbar',
  standalone: true,
  imports: [CommonModule, NgFor, MatIcon],
  templateUrl: './skill-circular-progressbar.component.html',
  styleUrl: './skill-circular-progressbar.component.scss'
})
export class SkillCircularProgressbarComponent {
  @Input() user: UserDto | undefined;
  userSkills: UserSkillsDto[] | undefined;
  emptyUserSkill: UserSkillsDto = {} as UserSkillsDto;
  ngOnInit(): void {
    this.userSkills = this.user?.skills;
    console.log('UserSkill', this.userSkills);
    this.userSkills = [] as UserSkillsDto[];
  }
  
  // constructor(public dialog: MatDialog, private userSkillService: user) {}
  // openDialog(jobHistory: JobHistoryDto): void {
  //   const dialogRef = this.dialog.open(AddFormDialogComponent, {
  //     width: '50%',
  //     data: {user: this.userExperiences, job: jobHistory}
  //   });
  
  //   dialogRef.afterClosed().subscribe(result => {
  //     console.log('Dialog was closed');
  //   });
  // }
  //   deleteJobExperience(id: string | undefined): void {
  //     console.log('Deleting Job History', id); 
      
  //     if(id !== undefined) {
  //       const params = { id: id };
  //       this.jobHistoryService.deleteJobHistory(params).subscribe({
  //         next: () => {
  //           console.log('Job history deleted successfully');
  //         },
  //         error: (error) => {
  //           console.error('Error deleting job history', error);
  //         },
  //       });
  //     }
  //   }
deleteSkill(_t9: Skill) {
throw new Error('Method not implemented.');
}
emptySkill: any;

openDialog(arg0: any) {
throw new Error('Method not implemented.');
}
  skills: Skill[] = [
    { name: 'HTML', percentage: 90 },
    { name: 'CSS', percentage: 85 },
    { name: 'JavaScript', percentage: 75 },
  ].map(skill => ({
    ...skill,
    color: this.getRandomColor()
  }));

  getRandomColor() {
    return `hsl(${Math.random() * 360}, 100%, 50%)`;
  }
}
