/* tslint:disable */
/* eslint-disable */
import { FileDto } from '../models/file-dto';
import { UserDto } from '../models/user-dto';
export interface EventDto {
  creator: UserDto;
  date: string;
  description: string;
  file?: FileDto;
  id?: string;
  location: string;
  subscribers: Array<UserDto>;
}
