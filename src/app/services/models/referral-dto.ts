/* tslint:disable */
/* eslint-disable */
import { UserDto } from '../models/user-dto';
export interface ReferralDto {
  description: string;
  id?: string;
  student: UserDto;
  teacher: UserDto;
}
