/* tslint:disable */
/* eslint-disable */
import { CompanyDto } from '../models/company-dto';
import { JobCandidatesDto } from '../models/job-candidates-dto';
export interface PostedJobDto {
  company: CompanyDto;
  description: string;
  id?: string;
  jobCandidates: Array<JobCandidatesDto>;
  location: string;
  openUntil: string;
  postedDate: string;
  status: 'ACTIVE' | 'INACTIVE';
  type: 'REMOTE' | 'ONSITE';
}
