import {CUSTOM_ELEMENTS_SCHEMA, Component, EventEmitter, Input, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {provideNativeDateAdapter} from '@angular/material/core';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatSelectModule} from '@angular/material/select';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import {
  AverageRating,
  BibliographyDto,
  CompanyDto,
  CourseDto,
  FacultyDto,
  PostedJobDto,
  SkillDto,
  SpecializationDto,
  UserDto
} from '../../services/models';
import {CompanyService, ReviewService, SearchService, SkillService} from '../../services/services';
import {MatDialogModule} from '@angular/material/dialog';
import {CommonModule} from '@angular/common';
import {defineComponents, IgcRatingComponent} from 'igniteui-webcomponents';
import 'igniteui-webcomponents/themes/light/bootstrap.css';

defineComponents(IgcRatingComponent);


@Component({
  selector: 'app-search',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatAutocompleteModule,
    MatDialogModule,
    CommonModule
  ],
  templateUrl: './search.component.html',
  providers: [provideNativeDateAdapter()],
  styleUrl: './search.component.scss',
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SearchComponent {
  filterForm: FormGroup;
  companyMap: Map<string, CompanyDto> = new Map();
  options: CompanyDto[] = [];
  @Output() users = new EventEmitter<UserDto[]>();
  @Output() companies = new EventEmitter<CompanyDto[]>();
  @Output() postedJobs = new EventEmitter<PostedJobDto[]>();
  @Output() courses = new EventEmitter<CourseDto[]>();
  @Output() bibliographies = new EventEmitter<BibliographyDto[]>();

  @Input() endpoint: string = {} as string;
  selectedCompany: CompanyDto | null | undefined;
  filteredOptions: Observable<CompanyDto[]> = new Observable();
  skillMap: Map<string, SkillDto> = new Map();
  skillFilteredOptions: Observable<SkillDto[]> = new Observable();
  skillOptions: SkillDto[] = [];
  languages: string[] = ['Afrikaans', 'Akan', 'Albaneză', 'Amharică', 'Arabă', 'Armeană', 'Asameză', 'Aymara', 'Azeră', 'Bambara', 'Bască', 'Belarusă', 'Bengaleză', 'Bhojpuri', 'Birmană', 'Bosniacă', 'Bulgară', 'Catalană', 'Cebuană', 'Cehă', 'Chineză (Simplificată)', 'Chineză (Tradițională)', 'Coreeană', 'Corsicană', 'Croată', 'Daneză', 'Divehi', 'Dogri', 'Ebraică', 'Engleză', 'Esperanto', 'Estonă', 'Ewe', 'Filipineză', 'Finlandeză', 'Franceză', 'Frizonă occidentală', 'Gaelică scoțiană', 'Galeză', 'Galiciană', 'Ganda', 'Georgiană', 'Germană', 'Goan Konkani', 'Greacă', 'Guarani', 'Gujarati', 'Haitiană', 'Hausa', 'Hawaiiană', 'Hindi', 'Hmong', 'Idiș', 'Igbo', 'Iloko', 'Indoneziană', 'Irlandeză', 'Islandeză', 'Italiană', 'Japoneză', 'Javaneză', 'Kannada', 'Kazahă', 'Kârgâză', 'Khmeră', 'Kinyarwanda', 'Krio', 'Kurdă', 'Kurdă centrală', 'Laoțiană', 'Latină', 'Letonă', 'Lingala', 'Lituaniană', 'Luxemburgheză', 'Macedoneană', 'Maghiară', 'Maithili', 'Malaeză', 'Malayalam', 'Malgașă', 'Malteză', 'Manipuri (meitei mayek)', 'Maori', 'Marathi', 'Mizo', 'Mongolă', 'Neerlandeză', 'Nepaleză', 'Norvegiană', 'Nyanja', 'Odia', 'Oromo', 'Paștună', 'Persană', 'Poloneză', 'Portugheză', 'Punjabi', 'Quechua', 'Română', 'Rusă', 'Samoană', 'Sanscrită', 'Sârbă', 'Sesotho', 'Shona', 'Sindhi', 'Singhaleză', 'Slovacă', 'Slovenă', 'Somaleză', 'Sotho de nord', 'Spaniolă', 'Suedeză', 'Sundaneză', 'Swahili', 'Tadjică', 'Tamilă', 'Tătară', 'Telugu', 'Thailandeză', 'Tigrină', 'Tsonga', 'Turcă', 'Turkmenă', 'Ucraineană', 'Uigură', 'Urdu', 'Uzbecă', 'Vietnameză', 'Xhosa', 'Yoruba', 'Zulu'];
  filteredLanguageOptions: Observable<string[]> = new Observable();
  specializations: string[] = [];
  facultyOptions: string[] = [];
  error: any = null;
  filteredFacultyOptions: Observable<string[]> = new Observable();
  specializationFilterOptions: Observable<string[]> = new Observable();
  filteredCompanies: CompanyDto[] = [];
  filteredJobs: PostedJobDto[] = [];

  constructor(private fb: FormBuilder, private companyService: CompanyService, private skillService: SkillService, private searchService: SearchService, private reviewService: ReviewService) {
    this.filterForm = this.fb.group({
      name: [''],
      faculty: [''],
      specialization: [''],
      year: [''],
      semester: [''],
      company: [''],
      skill: [''],
      percentageMin: ['', [this.percentageValidator]],
      percentageMax: ['', [this.percentageValidator]],
      language: [''],
      listeningLevel: [''],
      speakingLevel: [''],
      writingLevel: [''],
      readingLevel: [''],
      conversationLevel: [''],
      userRole: [''],
      status: [''],
      jobLocation: [''],
      jobType: [''],
      recruiterName: [''],
      companyName: [''],
      companyRating: [''],
      position: [''],
      title: ['']
    });
  }

  percentageValidator(control: FormControl) {
    const value = control.value;
    if (value === null || value === '') {
      return null;
    }
    if (value < 0 || value > 100) {
      return {range: true};
    }
    return null;
  }

  get percentageMin() {
    return this.filterForm.get('percentageMin');
  }

  get percentageMax() {
    return this.filterForm.get('percentageMax');
  }

  get year() {
    return this.filterForm.get('year');
  }

  get semester() {
    return this.filterForm.get('semester');
  }

  ngOnInit() {
    this.getCompanyList().subscribe(companies => {
      this.companyMap = new Map(companies.map(company => [company.name, company]));
      this.options = companies;
      this.filteredOptions = this.filterForm.get('company')!.valueChanges.pipe(startWith(''), map(value => this._filter(value)));
      console.log('Companies list', this.options);
    });
    this.filterForm.get('company')!.valueChanges.subscribe(value => {
      this.selectedCompany = this.options.find(option => option.name === value) ?? null;
    });
    this.getSkillList().subscribe(skills => {
      this.skillMap = new Map(skills.map(skill => [skill.name, skill]));
      this.skillOptions = skills;
      this.skillFilteredOptions = this.filterForm.get('skill')!.valueChanges.pipe(
        startWith(''),
        map(value => this._skillFilter(value))
      );
      console.log('Skill list', this.options);
    });
    this.filteredLanguageOptions = this.filterForm.get('language')!.valueChanges.pipe(
      startWith(''),
      map(value => this._languageFilter(value, this.languages))
    );

    this.getFacultyList().subscribe(faculties => {
      this.facultyOptions = faculties.map(faculty => faculty.name);
      this.filteredFacultyOptions = this.filterForm.get('faculty')!.valueChanges.pipe(startWith(''), map(value => this.facultyFilter(value)));
      console.log('Faculties list', this.facultyOptions);
    });

    this.getSpecilizations().subscribe(specializations => {
      this.specializations = specializations.map(specialization => specialization.name);
      this.specializationFilterOptions = this.filterForm.get('specialization')!.valueChanges.pipe(startWith(''), map(value => this.specializationFilter(value)));
      console.log('Specializations list', this.specializations);
    });
  }

  private getSpecilizations(): Observable<SpecializationDto[]> {
    const param = {
      endpoint: "specializations",
      criteria: {
        "faculty.name": this.filterForm.get('faculty')?.value
      }
    };
    return this.searchService.search(param).pipe(map(specializations => {
      console.log('Specializations', specializations);
      return specializations;
    }));
  }

  private specializationFilter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.specializations
      .filter(option => option.toLowerCase().includes(filterValue))
      .sort((a, b) => a.localeCompare(b));
  }

  private getFacultyList(): Observable<FacultyDto[]> {
    const param = {
      endpoint: "faculties",
      criteria: {}
    };
    return this.searchService.search(param).pipe(map(faculties => {
      console.log('Faculties', faculties);
      return faculties;
    }));
  }

  private facultyFilter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.facultyOptions
      .filter(option => option.toLowerCase().includes(filterValue))
      .sort((a, b) => a.localeCompare(b));
  }

  onSubmit(): void {
    console.log(this.filterForm.value);
  }

  resetForm(): void {
    this.filterForm.reset();
    switch (this.endpoint) {
      case 'users':
        this.resetUsers();
        break;
      case 'companies':
        this.resetCompanies();
        break;
      case 'postedJobs':
        this.resetPostedJobs();
        break;
      case 'courses':
        this.resetCourses();
        break;
      case 'bibliographies':
        this.resetBibliographies();
        break;
    }

  }

  private _filter(value: string): CompanyDto[] {
    const filterValue = value.toLowerCase();
    return this.options
      .filter(option => option.name.toLowerCase().includes(filterValue))
      .sort((a, b) => a.name.localeCompare(b.name));
  }

  private getCompanyList(): Observable<CompanyDto[]> {
    return this.companyService.findAllCompanies().pipe(map(companies => {
      console.log('Companies', companies);
      return companies;
    }));
  }

  private getSkillList(): Observable<SkillDto[]> {
    return this.skillService.findAllSkills().pipe(
      map(skills => {
        console.log('Skills', skills);
        return skills;
      })
    );
  }

  private _skillFilter(value: string): SkillDto[] {
    const filterValue = value.toLowerCase();
    return this.skillOptions
      .filter(option => option.name.toLowerCase().includes(filterValue))
      .sort((a, b) => a.name.localeCompare(b.name));
  }

  private _languageFilter(value: string, list: string[]): string[] {
    const filterValue = value.toLowerCase();
    return list
      .filter(option => option.toLowerCase().includes(filterValue))
      .sort((a, b) => a.localeCompare(b));
  }

  updateRating(event: any): void {
    const newRating = event.target ? event.target.value : event;
    console.log('New Rating', newRating);
    this.filterForm.controls['companyRating'].setValue(newRating);
  }

  sendData() {
    switch (this.endpoint) {
      case 'users':
        this.sendFilteredUsers();
        break;
      case 'companies':
        this.sendFilteredCompanies();
        break;
      case 'postedJobs':
        this.sendFilteredPostedJobs();
        break;
      case 'courses':
        this.sendFilteredCourses();
        break;
      case 'bibliographies':
        this.sendFilteredBibliographies();
        break;
    }
  }

  sendFilteredUsers(): void {
    let criteria: { [key: string]: string } = {};
    if (this.filterForm.get("name")?.value && this.filterForm.get("name")?.value != '') {
      criteria["name"] = this.filterForm.get("name")?.value;
    }
    if (this.filterForm.get("userRole")?.value && this.filterForm.get("userRole")?.value != '') {
      criteria["role"] = this.filterForm.get("userRole")?.value;
    }
    if (this.filterForm.get("status")?.value && this.filterForm.get("status")?.value != '') {
      criteria["status"] = this.filterForm.get("status")?.value;
    }
    if (this.filterForm.get("faculty")?.value && this.filterForm.get("faculty")?.value != '') {
      criteria["specializations.faculty.name"] = this.filterForm.get("faculty")?.value;
    }
    if (this.filterForm.get("specialization")?.value && this.filterForm.get("specialization")?.value != '') {
      criteria["specializations.name"] = this.filterForm.get("specialization")?.value;
    }
    if (this.filterForm.get("company")?.value && this.filterForm.get("company")?.value != '') {
      criteria["jobHistories.company.name"] = this.filterForm.get("company")?.value;
    }
    if (this.filterForm.get("skill")?.value && this.filterForm.get("skill")?.value != '') {
      criteria["skills.skill.name"] = this.filterForm.get("skill")?.value;
    }
    if (this.filterForm.get("percentageMin")?.value && this.filterForm.get("percentageMin")?.value != '') {
      criteria["skills.proficiency>"] = this.filterForm.get("percentageMin")?.value;
    }
    if (this.filterForm.get("percentageMax")?.value && this.filterForm.get("percentageMax")?.value != '') {
      criteria["skills.proficiency<"] = this.filterForm.get("percentageMax")?.value;
    }
    if (this.filterForm.get("language")?.value && this.filterForm.get("language")?.value != '') {
      criteria["languages.name"] = this.filterForm.get("language")?.value;
    }
    if (this.filterForm.get("listeningLevel")?.value && this.filterForm.get("listeningLevel")?.value != '') {
      criteria["languages.listening"] = this.filterForm.get("listeningLevel")?.value;
    }
    if (this.filterForm.get("speakingLevel")?.value && this.filterForm.get("speakingLevel")?.value != '') {
      criteria["languages.speaking"] = this.filterForm.get("speakingLevel")?.value;
    }
    if (this.filterForm.get("writingLevel")?.value && this.filterForm.get("writingLevel")?.value != '') {
      criteria["languages.writing"] = this.filterForm.get("writingLevel")?.value;
    }
    if (this.filterForm.get("readingLevel")?.value && this.filterForm.get("readingLevel")?.value != '') {
      criteria["languages.reading"] = this.filterForm.get("readingLevel")?.value;
    }
    if (this.filterForm.get("conversationLevel")?.value && this.filterForm.get("conversationLevel")?.value != '') {
      criteria["languages.conversation"] = this.filterForm.get("conversationLevel")?.value;
    }
    const param = {
      "endpoint": this.endpoint,
      "criteria": criteria
    };
    this.searchService.search(param).subscribe({
      next: (users: UserDto[]) => {
        this.users.emit(users);
        console.log('Users:', users);
      },
      error: (error: any) => {
        console.error('Error fetching review:', error);
      },
      complete: () => {
        console.log('Completed fetching review');
      }
    });
  }

  resetUsers(): void {
    const param = {
      endpoint: this.endpoint,
      criteria: {}
    };
    this.searchService.search(param).subscribe({
      next: (users: UserDto[]) => {
        this.users.emit(users);
        console.log('Users:', users);
      },
      error: (error: any) => {
        console.error('Error fetching review:', error);
      },
      complete: () => {
        console.log('Completed fetching review');
      }
    });
  }

  sendFilteredCompanies(): void {
    let criteria: { [key: string]: string } = {};
    if (this.filterForm.get("companyName")?.value && this.filterForm.get("companyName")?.value != '') {
      criteria["name"] = this.filterForm.get("companyName")?.value;
    }
    const param = {
      "endpoint": this.endpoint,
      "criteria": criteria
    };
    this.searchService.search(param).subscribe({
      next: async (companies: CompanyDto[]) => {
        const ratingThreshold = this.filterForm.get("companyRating")?.value;
        if (!ratingThreshold) {
          this.filteredCompanies = companies;
        } else {
          console.log('Companies before filtering:', companies);

          const ratingsPromises = companies.map(async (company) => {
            const rating = await this.averageRating(company);
            return {company, rating};
          });

          const companiesWithRatings = await Promise.all(ratingsPromises);

          this.filteredCompanies = companiesWithRatings
            .filter(item => item.rating > ratingThreshold)
            .map(item => item.company);

          console.log('Filtered Companies:', this.filteredCompanies);
        }
        this.companies.emit(this.filteredCompanies);
        console.log('Companies:', this.filteredCompanies);
      },
      error: (error: any) => {
        console.error('Error fetching review:', error);
      },
      complete: () => {
        console.log('Completed fetching review');
      }
    });
  }

  resetCompanies(): void {
    const param = {
      endpoint: this.endpoint,
      criteria: {}
    };
    this.searchService.search(param).subscribe({
      next: (companies: CompanyDto[]) => {
        this.companies.emit(companies);
        console.log('Companies:', companies);
      },
      error: (error: any) => {
        console.error('Error fetching review:', error);
      },
      complete: () => {
        console.log('Completed fetching review');
      }
    });
  }

  sendFilteredPostedJobs(): void {
    let criteria: { [key: string]: string } = {};
    if (this.filterForm.get("skill")?.value && this.filterForm.get("skill")?.value != '') {
      criteria["skills.name"] = this.filterForm.get("skill")?.value;
    }
    if (this.filterForm.get("status")?.value && this.filterForm.get("status")?.value != '') {
      criteria["status"] = this.filterForm.get("status")?.value;
    }
    if (this.filterForm.get("position")?.value && this.filterForm.get("position")?.value != '') {
      criteria["position"] = this.filterForm.get("position")?.value;
    }
    if (this.filterForm.get("recruiterName")?.value && this.filterForm.get("recruiterName")?.value != '') {
      criteria["user.name"] = this.filterForm.get("recruiterName")?.value;
    }
    if (this.filterForm.get("company")?.value && this.filterForm.get("company")?.value != '') {
      criteria["company.name"] = this.filterForm.get("company")?.value;
    }
    if (this.filterForm.get("jobLocation")?.value && this.filterForm.get("jobLocation")?.value != '') {
      criteria["location"] = this.filterForm.get("jobLocation")?.value;
    }
    if (this.filterForm.get("jobType")?.value && this.filterForm.get("jobType")?.value != '') {
      criteria["type"] = this.filterForm.get("jobType")?.value;
    }
    const param = {
      "endpoint": this.endpoint,
      "criteria": criteria
    };
    this.searchService.search(param).subscribe({
      next: async (jobs: PostedJobDto[]) => {
        const ratingThreshold = this.filterForm.get("companyRating")?.value;
        if (!ratingThreshold) {
          this.filteredJobs = jobs;
        } else {
          console.log('Jobs before filtering:', jobs);

          const ratingsPromises = jobs.map(async (jobs) => {
            const rating = await this.averageRating(jobs.company);
            return {jobs, rating};
          });

          const jobsWithCompaniesRatings = await Promise.all(ratingsPromises);

          this.filteredJobs = jobsWithCompaniesRatings
            .filter(item => item.rating > ratingThreshold)
            .map(item => item.jobs);

          console.log('Filtered Jobs:', this.filteredJobs);
        }
        this.postedJobs.emit(this.filteredJobs);
        console.log('Jobs:', this.filteredJobs);
      },
      error: (error: any) => {
        console.error('Error fetching review:', error);
      },
      complete: () => {
        console.log('Completed fetching review');
      }
    });
  }

  resetPostedJobs(): void {
    const param = {
      endpoint: this.endpoint,
      criteria: {
        "status": "ACTIVE"
      }
    };
    this.searchService.search(param).subscribe({
      next: (jobs: PostedJobDto[]) => {
        this.postedJobs.emit(jobs);
        console.log('Posted jobs:', jobs);
      },
      error: (error: any) => {
        console.error('Error fetching review:', error);
      },
      complete: () => {
        console.log('Completed fetching review');
      }
    });
  }

  sendFilteredCourses(): void {
    let criteria: { [key: string]: string } = {};
    if (this.filterForm.get("skill")?.value && this.filterForm.get("skill")?.value != '') {
      criteria["skills.name"] = this.filterForm.get("skill")?.value;
    }
    if (this.filterForm.get("name")?.value && this.filterForm.get("name")?.value != '') {
      criteria["name"] = this.filterForm.get("name")?.value;
    }
    if (this.filterForm.get("semester")?.value && this.filterForm.get("semester")?.value != '') {
      criteria["semester"] = this.filterForm.get("semester")?.value;
    }
    if (this.filterForm.get("year")?.value && this.filterForm.get("year")?.value != '') {
      criteria["year"] = this.filterForm.get("year")?.value;
    }
    if (this.filterForm.get("specialization")?.value && this.filterForm.get("specialization")?.value != '') {
      criteria["specialization.name"] = this.filterForm.get("specialization")?.value;
    }
    if (this.filterForm.get("faculty")?.value && this.filterForm.get("faculty")?.value != '') {
      criteria["specialization.faculty.name"] = this.filterForm.get("faculty")?.value;
    }
    const param = {
      "endpoint": this.endpoint,
      "criteria": criteria
    };
    this.searchService.search(param).subscribe({
      next: (courses: CourseDto[]) => {
        this.courses.emit(courses);
        console.log('Courses:', courses);
      },
      error: (error: any) => {
        console.error('Error fetching review:', error);
      },
      complete: () => {
        console.log('Completed fetching review');
      }
    });
  }

  resetCourses(): void {
    const param = {
      endpoint: this.endpoint,
      criteria: {}
    };
    this.searchService.search(param).subscribe({
      next: (courses: CourseDto[]) => {
        this.courses.emit(courses);
        console.log('Courses:', courses);
      },
      error: (error: any) => {
        console.error('Error fetching course:', error);
      },
      complete: () => {
        console.log('Completed fetching course');
      }
    });
  }

  sendFilteredBibliographies(): void {
    let criteria: { [key: string]: string } = {};
    if (this.filterForm.get("skill")?.value && this.filterForm.get("skill")?.value != '') {
      criteria["skill.name"] = this.filterForm.get("skill")?.value;
    }
    if (this.filterForm.get("name")?.value && this.filterForm.get("name")?.value != '') {
      criteria["writer.name"] = this.filterForm.get("name")?.value;
    }
    if (this.filterForm.get("title")?.value && this.filterForm.get("title")?.value != '') {
      criteria["title"] = this.filterForm.get("title")?.value;
    }
    const param = {
      "endpoint": this.endpoint,
      "criteria": criteria
    };
    this.searchService.search(param).subscribe({
      next: (bibliographies: BibliographyDto[]) => {
        this.bibliographies.emit(bibliographies);
        console.log('Bibliographies:', bibliographies);
      },
      error: (error: any) => {
        console.error('Error fetching bibliographies:', error);
      },
      complete: () => {
        console.log('Completed fetching bibliographies');
      }
    });
  }

  resetBibliographies(): void {
    const param = {
      endpoint: this.endpoint,
      criteria: {}
    };
    this.searchService.search(param).subscribe({
      next: (bibliographies: BibliographyDto[]) => {
        this.bibliographies.emit(bibliographies);
        console.log('Bibliographies:', bibliographies);
      },
      error: (error: any) => {
        console.error('Error fetching bibliographies:', error);
      },
      complete: () => {
        console.log('Completed fetching bibliographies');
      }
    });
  }

  averageRating(company: CompanyDto | undefined): Promise<number> {
    if (!company?.id) {
      return Promise.reject(new Error('Error fetching review: Company ID is missing.'));
    }

    const param = {companyId: company.id};

    return new Promise((resolve, reject) => {
      this.reviewService.getAverageReviewRating(param).subscribe({
        next: (averageRating: AverageRating) => {
          console.log('Company name', company.name);
          console.log('Average Rating:', averageRating);
          resolve(averageRating.averageRating ?? 0);
        },
        error: (error: any) => {
          reject(new Error('Error fetching review: ' + error));
        }
      });
    });
  }

}
