/* tslint:disable */
/* eslint-disable */
import { CompanyDto } from '../models/company-dto';
import { JobCandidatesDto } from '../models/job-candidates-dto';
import { SkillDto } from '../models/skill-dto';
import { UserDto } from '../models/user-dto';
export interface PostedJobDto {
  company?: CompanyDto;
  description: string;
  id?: string;
  jobCandidates?: Array<JobCandidatesDto>;
  location: string;
  openUntil: string;
  position: string;
  postedDate?: string;
  skills?: Array<SkillDto>;
  status?: 'ACTIVE' | 'INACTIVE';
  type: 'REMOTE' | 'ONSITE' | 'HYBRID';
  user?: UserDto;
}
