import { Component, Input } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { LanguageDto, UserDto } from '../../../services/models';
import { MatIcon } from '@angular/material/icon';
import { MatDialog } from '@angular/material/dialog';
import { LanguageService } from '../../../services/services';
import { LanguageOpenDialogComponent } from '../language-open-dialog/language-open-dialog.component';

@Component({
  selector: 'app-language-table',
  standalone: true,
  imports: [MatTableModule, MatIcon],
  templateUrl: './language-table.component.html',
  styleUrl: './language-table.component.scss'
})
export class LanguageTableComponent {
  @Input() user: UserDto | undefined;
  @Input() languages: LanguageDto[] = [];

  emptyLanguage: LanguageDto = {} as LanguageDto;

  get languageData() {
    return this.languages;
  }

  deleteLanguage(language: LanguageDto) {
    console.log('Deleting language', language.id);
    if (language.id !== undefined) {
      const params = { id: language.id };
      this.languageService.deleteLanguage(params).subscribe({
        next: () => {
          let index = this.languages.indexOf(language);
          if (index !== undefined && index !== -1) {
            this.languages.splice(index, 1);
          }
          if (this.languages === undefined) {
            this.languages = [];
          }
          this.languages = [...this.languages];
          console.log('Language deleted successfully');
        },
        error: (error) => {
          console.error('Error deleting language', error);
        },
      });
    }
  }
  constructor(public dialog: MatDialog, private languageService: LanguageService) { }
  openDialog(language: LanguageDto) {
    const dialogRef = this.dialog.open(LanguageOpenDialogComponent, {
      width: '50%',
      data: { user: this.user, language: language }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Result', result);
      if (result.languageEntity && this.user) {
        console.log('am ajuns aici');
        let index = this.languages.indexOf(language);
        if (index !== -1 && index !== undefined && index !== null) {
          this.languages[index] = result.languageEntity;
        } else {
          this.languages?.push(result.languageEntity);
        }
        this.languages = [...this.languages];
        console.log('Languages', this.languages);
      }
      console.log('Dialog was closed');
    });
  }

  color: string = '#E74535';
  textColor: string = '#FFFFFF';
  fontSize: string = '16px';
  displayedColumns: string[] = ['language', 'conversation', 'listening', 'reading', 'speaking', 'writing', 'actions'];

}
