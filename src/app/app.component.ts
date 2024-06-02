import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { FirstComponentComponent } from "./components/first-component/first-component.component";
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProfileCardComponent } from './components/user/profile-card/profile-card.component';
import { ExperienceComponent } from './components/experience/experience.component';
import { ProjectsCarouselComponent } from './components/user/projects-carousel/projects-carousel.component';
import { SkillCircularProgressbarComponent } from './components/user/skill-circular-progressbar/skill-circular-progressbar.component';
import { LanguageTableComponent } from './components/user/language-table/language-table.component';
import { AcreditareCardComponent } from './components/user/acreditare-card/acreditare-card.component';
import { ReferalComponent } from './components/user/referal/referal.component';
import { JsonPipe } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { MyHttpService } from './my-http.service';
import { TokenService } from './services/auth/token.service';

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
  constructor(private route: ActivatedRoute, private http: MyHttpService, private tokenService: TokenService) {
  }

  ngOnInit(): void {
    this.route.queryParams
      .subscribe(params => {
        if (params["code"] !== undefined) {
          this.http.getToken(params["code"]).subscribe(result => {
            if (result && result !== "") {
              this.tokenService.token = result;
            }
          });
        }
      }
    );
  }

  title = 'Diana application';
}
