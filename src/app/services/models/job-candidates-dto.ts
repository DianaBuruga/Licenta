/* tslint:disable */
/* eslint-disable */
import { PostedJobDto } from '../models/posted-job-dto';
import { UserDto } from '../models/user-dto';
export interface JobCandidatesDto {
  applicationDate?: string;
  candidateId?: string;
  jobId?: string;
  postedJob: PostedJobDto;
  user: UserDto;
}
