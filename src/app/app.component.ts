import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { FirstComponentComponent } from "./components/first-component/first-component.component";
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProfileCardComponent } from './components/profile-card/profile-card.component';
import { ExperienceComponent } from './components/experience/experience.component';
import { ProjectsCarouselComponent } from './components/projects-carousel/projects-carousel.component';
import { SkillCircularProgressbarComponent } from './components/skill-circular-progressbar/skill-circular-progressbar.component';
import { LanguageTableComponent } from './components/language-table/language-table.component';
import { AcreditareCardComponent } from './components/acreditare-card/acreditare-card.component';
import { ReferalComponent } from './components/referal/referal.component';
import { JsonPipe } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { MyHttpService } from './my-http.service';

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
    ]
})
export class AppComponent {
  constructor(private route: ActivatedRoute, private http: MyHttpService) {
  }

  ngOnInit(): void {
    this.route.queryParams
      .subscribe(params => {
        if (params["code"] !== undefined) {
          this.http.getToken(params["code"]).subscribe(result => {
            if (result) {
              localStorage.clear();
              localStorage.setItem('token', result);
            }
          });
        }
      }
    );
  }

  title = 'Diana application';
}
