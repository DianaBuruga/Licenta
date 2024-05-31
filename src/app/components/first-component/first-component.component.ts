import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-first-component',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './first-component.component.html',
  styleUrl: './first-component.component.scss'
})
export class FirstComponentComponent {
  name: string = '';
  email: string = '';
  message: string = '';
}
