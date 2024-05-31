import { Component } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { SearchBarComponent } from '../search-bar/search-bar.component';
import { MatMenuModule } from '@angular/material/menu';
import { RouterModule } from '@angular/router'; // Necessary for using routerLink

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
  standalone: true,
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    SearchBarComponent,
    MatMenuModule,
    RouterModule
  ]
})
export class NavbarComponent {
  onFocus(): void {
  }

  performSearch(value: string): void {
    console.log('Searching for:', value);
  }
}
