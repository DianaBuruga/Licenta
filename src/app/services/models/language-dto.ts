/* tslint:disable */
/* eslint-disable */
import { FileDto } from '../models/file-dto';
import { UserDto } from '../models/user-dto';

/**
 * Id of the language that will be deleted
 */
export interface LanguageDto {
  conversation: 'A1' | 'A2' | 'B1' | 'B2' | 'C1' | 'C2' | 'TONGUE';
  file?: FileDto;
  id?: string;
  listening: 'A1' | 'A2' | 'B1' | 'B2' | 'C1' | 'C2' | 'TONGUE';
  name: string;
  reading: 'A1' | 'A2' | 'B1' | 'B2' | 'C1' | 'C2' | 'TONGUE';
  speaking: 'A1' | 'A2' | 'B1' | 'B2' | 'C1' | 'C2' | 'TONGUE';
  user: UserDto;
  writing: 'A1' | 'A2' | 'B1' | 'B2' | 'C1' | 'C2' | 'TONGUE';
}
