import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgFor } from '@angular/common';
interface Skill {
  name: string;
  percentage: number;
  color: string;
}

@Component({
  selector: 'app-skill-circular-progressbar',
  standalone: true,
  imports: [CommonModule, NgFor],
  templateUrl: './skill-circular-progressbar.component.html',
  styleUrl: './skill-circular-progressbar.component.scss'
})
export class SkillCircularProgressbarComponent {
  skills: Skill[] = [
    { name: 'HTML', percentage: 90 },
    { name: 'CSS', percentage: 85 },
    { name: 'JavaScript', percentage: 75 },
  ].map(skill => ({
    ...skill,
    color: this.getRandomColor()
  }));

  getRandomColor() {
    return `hsl(${Math.random() * 360}, 100%, 50%)`; // Generează o culoare HSL aleatorie
  }
}
