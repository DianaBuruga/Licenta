import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FirstComponentComponent } from "./components/first-component/first-component.component";
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProfileCardComponent } from './components/profile-card/profile-card.component';
import { ExperienceComponent } from './components/experience/experience.component';
import { ProjectsCarouselComponent } from './components/projects-carousel/projects-carousel.component';
import { SkillCircularProgressbarComponent } from './components/skill-circular-progressbar/skill-circular-progressbar.component';
import { LanguageTableComponent } from './components/language-table/language-table.component';
import { AcreditareCardComponent } from './components/acreditare-card/acreditare-card.component';
import { ReferalComponent } from './components/referal/referal.component';
import { CommonModule, JsonPipe, NgIf } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
//import { AuthInterceptor } from './services/auth/auth.interceptor';
//import { BrowserModule } from '@angular/platform-browser';

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.scss',
    imports: [
      RouterOutlet,
      FirstComponentComponent,
      NavbarComponent,
      ProfileCardComponent,
      ExperienceComponent,
      ProjectsCarouselComponent,
      SkillCircularProgressbarComponent,
      LanguageTableComponent,
      AcreditareCardComponent,
      ReferalComponent,
      JsonPipe,
      HttpClientModule
      //BrowserModule//,
     //AppRoutingModule,
    ]
})
export class AppComponent {
  private http = inject(HttpClient);

  constructor() {
  }

  title = 'Diana application';
}