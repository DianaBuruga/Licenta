import { Component, Input, OnInit } from '@angular/core';
import {MatTableModule } from '@angular/material/table';
import { LanguageDto } from '../../services/models';

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
  imports: [MatTableModule],
  templateUrl: './language-table.component.html',
  styleUrl: './language-table.component.scss'
})
export class LanguageTableComponent implements OnInit {
  languageData: LanguageProficiency[]=[];
  ngOnInit(): void {
   this.languageData = this.languages?.map(dto => ({
    language: dto.name,
    conversational: dto.conversation,
    listening: dto.listening,
    reading: dto.reading,
    speaking: dto.speaking,
    writing: dto.writing
  })) || [];
  }
  @Input() languages: LanguageDto[] | undefined;
  color : string = '#E74535';
  textColor : string = '#FFFFFF';
  fontSize : string = '16px';
  displayedColumns: string[] = ['language', 'conversation', 'listening', 'reading', 'speaking', 'writing'];
  
  // [
  //   { language: 'English', conversational: 'C1', listening: 'C1', reading: 'C1', speaking: 'C1', writing: 'C1' },
  //   { language: 'Spanish', conversational: 'B1', listening: 'B1', reading: 'B1', speaking: 'B1', writing: 'B1' },
  //   { language: 'French', conversational: 'A2', listening: 'A2', reading: 'A2', speaking: 'A2', writing: 'A2' },
  // ];

}
