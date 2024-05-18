/* tslint:disable */
/* eslint-disable */
import { BibliographyDto } from '../models/bibliography-dto';
import { ExperienceDto } from '../models/experience-dto';
import { FileDto } from '../models/file-dto';
import { JobHistoryDto } from '../models/job-history-dto';
import { LanguageDto } from '../models/language-dto';
import { ReferralDto } from '../models/referral-dto';
import { SpecializationDto } from '../models/specialization-dto';
import { UserSkillsDto } from '../models/user-skills-dto';

/**
 * User that will be deleted
 */
export interface UserDto {
  bibliographies?: Array<BibliographyDto>;
  description: string;
  email: string;
  experiences?: Array<ExperienceDto>;
  id?: string;
  languages?: Array<LanguageDto>;
  name: string;
  phone: string;
  postedJobs?: Array<JobHistoryDto>;
  profilePhoto?: FileDto;
  receivedReferrals?: Array<ReferralDto>;
  role: 'STUDENT' | 'TEACHER' | 'COMPANY_REPRESENTATIVE' | 'MODERATOR';
  skills?: Array<UserSkillsDto>;
  specializations?: Array<SpecializationDto>;
  status?: 'RECRUITING' | 'OPEN_TO_WORK' | 'EMPLOYED';
  website: string;
}
