/* tslint:disable */
/* eslint-disable */
import { CompanyDto } from '../models/company-dto';
import { UserDto } from '../models/user-dto';
export interface JobHistoryDto {
  company: CompanyDto;
  description: string;
  endDate: string;
  id?: string;
  needQualification: boolean;
  position: string;
  startDate: string;
  user: UserDto;
}
