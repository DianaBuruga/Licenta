/* tslint:disable */
/* eslint-disable */
import { CompanyDto } from '../models/company-dto';

/**
 * Review that will be deleted
 */
export interface ReviewDto {
  company: CompanyDto;
  description: string;
  id?: string;
  position: string;
  rating: number;
  type: 'INTERVIEW' | 'JOB';
}
