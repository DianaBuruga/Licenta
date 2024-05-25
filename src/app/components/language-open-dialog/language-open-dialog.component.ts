import {Component, Inject, inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {CommonModule} from '@angular/common';
import { LanguageDto } from '../../services/models';
import { LanguageService } from '../../services/services';

@Component({
  selector: 'app-language-open-dialog',
  standalone: true,
  imports: [MatDialogModule, MatButtonModule, MatInputModule, MatFormFieldModule, ReactiveFormsModule, MatDialogModule, MatAutocompleteModule, CommonModule],
  templateUrl: './language-open-dialog.component.html',
  styleUrl: './language-open-dialog.component.scss'
})
export class LanguageOpenDialogComponent implements OnInit{
  languages: string[] = ['Afrikaans', 'Akan', 'Albaneză', 'Amharică', 'Arabă', 'Armeană', 'Asameză', 'Aymara', 'Azeră', 'Bambara', 'Bască', 'Belarusă', 'Bengaleză', 'Bhojpuri', 'Birmană', 'Bosniacă', 'Bulgară', 'Catalană', 'Cebuană', 'Cehă', 'Chineză (Simplificată)', 'Chineză (Tradițională)', 'Coreeană', 'Corsicană', 'Croată', 'Daneză', 'Divehi', 'Dogri', 'Ebraică', 'Engleză', 'Esperanto', 'Estonă', 'Ewe', 'Filipineză', 'Finlandeză', 'Franceză', 'Frizonă occidentală', 'Gaelică scoțiană', 'Galeză', 'Galiciană', 'Ganda', 'Georgiană', 'Germană', 'Goan Konkani', 'Greacă', 'Guarani', 'Gujarati', 'Haitiană', 'Hausa', 'Hawaiiană', 'Hindi', 'Hmong', 'Idiș', 'Igbo', 'Iloko', 'Indoneziană', 'Irlandeză', 'Islandeză', 'Italiană', 'Japoneză', 'Javaneză', 'Kannada', 'Kazahă', 'Kârgâză', 'Khmeră', 'Kinyarwanda', 'Krio', 'Kurdă', 'Kurdă centrală', 'Laoțiană', 'Latină', 'Letonă', 'Lingala', 'Lituaniană', 'Luxemburgheză', 'Macedoneană', 'Maghiară', 'Maithili', 'Malaeză', 'Malayalam', 'Malgașă', 'Malteză', 'Manipuri (meitei mayek)', 'Maori', 'Marathi', 'Mizo', 'Mongolă', 'Neerlandeză', 'Nepaleză', 'Norvegiană', 'Nyanja', 'Odia', 'Oromo', 'Paștună', 'Persană', 'Poloneză', 'Portugheză', 'Punjabi', 'Quechua', 'Română', 'Rusă', 'Samoană', 'Sanscrită', 'Sârbă', 'Sesotho', 'Shona', 'Sindhi', 'Singhaleză', 'Slovacă', 'Slovenă', 'Somaleză', 'Sotho de nord', 'Spaniolă', 'Suedeză', 'Sundaneză', 'Swahili', 'Tadjică', 'Tamilă', 'Tătară', 'Telugu', 'Thailandeză', 'Tigrină', 'Tsonga', 'Turcă', 'Turkmenă', 'Ucraineană', 'Uigură', 'Urdu', 'Uzbecă', 'Vietnameză', 'Xhosa', 'Yoruba', 'Zulu'];
  levels: string[] = ['A1', 'A2', 'B1', 'B2', 'C1', 'C2', 'TONGUE'];
  dialogRef = inject(MatDialogRef);
  error: any = null;
  filteredLanguageOptions: Observable<String[]> = new Observable();
  filteredConvesationOptions: Observable<String[]> = new Observable();
  filteredListeningOptions: Observable<String[]> = new Observable();
  filteredReadingOptions: Observable<String[]> = new Observable();
  filteredSpeakingOptions: Observable<String[]> = new Observable();
  filteredWritingOptions: Observable<String[]> = new Observable();
  form: FormGroup;
  language: LanguageDto = {} as LanguageDto;


  ngOnInit() {
    this.filteredLanguageOptions = this.form.get('language')!.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value, this.languages))
    );
    this.filteredConvesationOptions = this.form.get('conversation')!.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value, this.levels))
    );
    this.filteredListeningOptions = this.form.get('listening')!.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value, this.levels))
    );
    this.filteredReadingOptions = this.form.get('reading')!.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value, this.levels))
    );
    this.filteredSpeakingOptions = this.form.get('speaking')!.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value, this.levels))
    );
    this.filteredWritingOptions = this.form.get('writing')!.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value, this.levels))
    );
  }
 
  constructor(private languageService: LanguageService, @Inject(MAT_DIALOG_DATA) public data: any) {
    console.log('Data', data);
    const fb = new FormBuilder();
    this.form = fb.group({
      language: [data.language.name, [Validators.required]],
      conversation: [data.language.conversation, [Validators.required]],
      listening: [data.language.listening, [Validators.required]],
      reading: [data.language.reading, [Validators.required]],
      speaking: [data.language.speaking, [Validators.required]],
      writing: [data.language.writing, [Validators.required]],
      languageEntity: []
    });
  }

  private _filter(value: string, list: string[]): string[] {
    const filterValue = value.toLowerCase();
    return list
      .filter(option => option.toLowerCase().includes(filterValue))
      .sort((a, b) => a.localeCompare(b));
  }

  closeDialog() {
    this.dialogRef.close();
  }

  initializeUserSkill(): void {
    this.language.id = this.data.language.id;
    this.language.user = this.data.user;
    this.language.name = this.form.get('language')?.value;
    this.language.conversation = this.form.get('conversation')?.value;
    this.language.listening = this.form.get('listening')?.value;
    this.language.reading = this.form.get('reading')?.value;
    this.language.speaking = this.form.get('speaking')?.value;
    this.language.writing = this.form.get('writing')?.value;
  }

  onSubmit(): void {
    if (this.form.valid) {
      this.initializeUserSkill();
      const params = { body: this.language };
      console.log('params', params);
      if (this.language.id === '' || this.language.id === undefined) {
        this.languageService.saveLanguage(params).subscribe({
          next: (response: any) => {
            console.log('Language added successfully', response);
            this.form.get('languageEntity')?.setValue(response);
            this.dialogRef.close(this.form.value);
          },
          error: (error: any) => {
            console.error('Error adding language', error);
          }
        });
      } else {
        this.languageService.updateLanguage(params).subscribe({
          next: (response: any) => {
            console.log('Language updated successfully', response);
            this.form.get('languageEntity')?.setValue(response);
            this.dialogRef.close(this.form.value);
          },
          error: (error: any) => {
            console.error('Error updating language', error);
          }
        });
      }
    }
  }
}