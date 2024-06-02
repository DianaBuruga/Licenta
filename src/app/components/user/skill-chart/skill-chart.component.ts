import { Component } from '@angular/core';
import { MatProgressBarModule } from '@angular/material/progress-bar';

@Component({
  selector: 'app-skill-chart',
  standalone: true,
  templateUrl: './skill-chart.component.html',
  styleUrls: ['./skill-chart.component.scss'],
  imports: [MatProgressBarModule],
})
export class SkillChartComponent {
}
