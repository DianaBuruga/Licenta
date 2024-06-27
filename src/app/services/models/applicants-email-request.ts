/* tslint:disable */
/* eslint-disable */
import { FileDto } from '../models/file-dto';
export interface ApplicantsEmailRequest {
  name?: string;
  position?: string;
  subject?: string;
  toEmail?: string;
  zip?: FileDto;
}
