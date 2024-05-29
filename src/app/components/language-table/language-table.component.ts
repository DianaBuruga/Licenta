import {Component, Input, OnInit} from '@angular/core';
import {MatTableModule} from '@angular/material/table';
import {LanguageDto, UserDto} from '../../services/models';
import {MatIcon} from '@angular/material/icon';
import { LanguageOpenDialogComponent } from '../language-open-dialog/language-open-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { LanguageService } from '../../services/services';

@Component({
  selector: 'app-language-table',
  standalone: true,
  imports: [MatTableModule, MatIcon],
  templateUrl: './language-table.component.html',
  styleUrl: './language-table.component.scss'
})
export class LanguageTableComponent {
  @Input() user: UserDto | undefined;
  get languageData():LanguageDto[]{
    return this.user?.languages??[];
  }
  set languageData(value: LanguageDto[]){
    this.user!.languages = value;
  }
  
  emptyLanguage: LanguageDto = {} as LanguageDto;

  deleteLanguage(language: LanguageDto) {
    console.log('Deleting language', language.id); 
  if (language.id !== undefined && language.id !== undefined) {
    const params = { id: language.id};
    this.languageService.deleteLanguage(params).subscribe({
      next: () => {
        let index = this.user?.languages?.indexOf(language);
        if (index !== undefined && index !== -1) {
          this.user?.languages?.splice(index, 1);
        }
        if(this.user){
        if(this.user?.languages === undefined){
            this.user.languages = [];
          }
        this.languageData = [...this.user.languages];
        }
        console.log('Language deleted successfully');
      },
      error: (error) => {
        console.error('Error deleting language', error);
      },
    })  ;
  }
  }
  constructor(public dialog: MatDialog, private languageService: LanguageService) {}
  openDialog(language: LanguageDto) {
    const dialogRef = this.dialog.open(LanguageOpenDialogComponent, {
      width: '50%',
      data: {user: this.user, language: language}
    });
  
    dialogRef.afterClosed().subscribe(result => {
      console.log('Result', result);
      if(result && this.user)
        {
          console.log('am ajuns aici');
          if(this.user.languages === undefined){
            this.user.languages = [];
          }
          result.languageEntity.user= this.user;
          this.user.languages.push(result.languageEntity);
          this.languageData = [...this.user.languages];
          console.log('Languages', this.languageData);
        }
      console.log('Dialog was closed');
    });
  }

  color: string = '#E74535';
  textColor: string = '#FFFFFF';
  fontSize: string = '16px';
  displayedColumns: string[] = ['language', 'conversation', 'listening', 'reading', 'speaking', 'writing', 'actions'];

}
