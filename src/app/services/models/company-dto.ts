/* tslint:disable */
/* eslint-disable */
import { JobHistoryDto } from '../models/job-history-dto';
import { PostedJobDto } from '../models/posted-job-dto';
import { ReviewDto } from '../models/review-dto';

/**
 * Company that will be deleted
 */
export interface CompanyDto {
  address: string;
  id?: string;
  jobHistories: Array<JobHistoryDto>;
  logoUrl?: string;
  name: string;
  postedJobs: Array<PostedJobDto>;
  reviews: Array<ReviewDto>;
  website: string;
}
