import { Component, Input, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgFor } from '@angular/common';
import { MatIcon } from '@angular/material/icon';
import { SkillDto, UserDto, UserSkillsDto } from '../../services/models';
import { MatDialog } from '@angular/material/dialog';
import { UserSkillService } from '../../services/services';
import { UserSkillsFormDialogComponent } from '../user-skills-form-dialog/user-skills-form-dialog.component';

@Component({
  selector: 'app-skill-circular-progressbar',
  standalone: true,
  imports: [CommonModule, NgFor, MatIcon],
  templateUrl: './skill-circular-progressbar.component.html',
  styleUrl: './skill-circular-progressbar.component.scss'
})
export class SkillCircularProgressbarComponent {
  @Input() user: UserDto | undefined;
  colors : Map<SkillDto, string> = new Map<SkillDto, string>();
  get emptyUserSkill(): UserSkillsDto{
    return  {
      proficiency: 0,
      skill: {
        bibliographies: [],
        courses: [],
        name: '',
        userSkills: []
      },
      userDTO: this.user as UserDto
  } as UserSkillsDto;
  };

  get userSkills(): UserSkillsDto[]{
    return this.user?.skills??[];
  }

  get colorMap(): Map<SkillDto, string>{
    this.userSkills?.forEach(element => {
      if(!this.colors.has(element.skill)) 
        this.colors.set(element.skill, this.getRandomColor());
      }
    );
    return this.colors;
  }

  constructor(public dialog: MatDialog, private userSkillService: UserSkillService) {}
  
  openDialog(userSkillDto: UserSkillsDto): void {
    const dialogRef = this.dialog.open(UserSkillsFormDialogComponent, {
      width: '50%',
      data: {user: this.user, userSkill: userSkillDto}
    });
  
    dialogRef.afterClosed().subscribe(result => {
      console.log('Result',result);
      if(result && this.user)
        {
          if(this.user.skills === undefined){
            this.user.skills = [];
          }
          this.user.skills.push(result.userSkills);
          console.log('User Skills', this.userSkills);
        }
      console.log('Dialog was closed');
    });
  }
  
deleteSkill(userSkill: UserSkillsDto) {
  console.log('Deleting User Skill', userSkill.skill.id); 
  if (userSkill.skill.id !== undefined && this.user?.id !== undefined) {
    const params = { userId: this.user.id, skillId: userSkill.skill.id };
    this.userSkillService.deleteUserSkill(params).subscribe({
      next: () => {
        let index = this.user?.skills?.indexOf(userSkill);
        if (index !== undefined && index !== -1) {
          this.user?.skills?.splice(index, 1);
        }
        (index = this.userSkills?.indexOf(userSkill)) !== undefined && this.userSkills?.splice(index, 1);
        this.colors.delete(userSkill.skill);
        console.log('User skill deleted successfully');
      },
      error: (error) => {
        console.error('Error deleting user skill', error);
      },
    })  ;
  }
}

  getRandomColor() {
    return `hsl(${Math.random() * 360}, 100%, 50%)`;
  }
}
