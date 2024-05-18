/* tslint:disable */
/* eslint-disable */
import { FileDto } from '../models/file-dto';
import { UserDto } from '../models/user-dto';
export interface ExperienceDto {
  date: string;
  description: string;
  fileDTO?: FileDto;
  id?: string;
  title: string;
  type: 'COMPETITION' | 'PROJECT' | 'ACCREDITATION' | 'VOLUNTEERING' | 'INTERNSHIP';
  url?: string;
  userDTO?: UserDto;
}
