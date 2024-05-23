import {Component, Input, OnInit} from '@angular/core';
import {MatTableModule} from '@angular/material/table';
import {LanguageDto} from '../../services/models';
import {MatIcon} from '@angular/material/icon';

interface LanguageProficiency {
  language: string;
  conversational: string;
  listening: string;
  reading: string;
  speaking: string;
  writing: string;
}

@Component({
  selector: 'app-language-table',
  standalone: true,
  imports: [MatTableModule, MatIcon],
  templateUrl: './language-table.component.html',
  styleUrl: './language-table.component.scss'
})
export class LanguageTableComponent implements OnInit {
  deleteLanguage(arg0: any) {
    throw new Error('Method not implemented.');
  }

  emptyLanguage: any;

  openDialog(arg0: any) {
    throw new Error('Method not implemented.');
  }

  languageData: LanguageProficiency[] = [];

  ngOnInit(): void {
    this.languageData = this.languages?.map(dto => ({
      language: dto.name,
      conversational: dto.conversation,
      listening: dto.listening,
      reading: dto.reading,
      speaking: dto.speaking,
      writing: dto.writing
    })) ?? [];
  }

  @Input() languages: LanguageDto[] | undefined;
  color: string = '#E74535';
  textColor: string = '#FFFFFF';
  fontSize: string = '16px';
  displayedColumns: string[] = ['language', 'conversation', 'listening', 'reading', 'speaking', 'writing', 'actions'];

}
