/* tslint:disable */
/* eslint-disable */
import { SkillDto } from '../models/skill-dto';
import { SpecializationDto } from '../models/specialization-dto';

/**
 * Course that will be deleted
 */
export interface CourseDto {
  id?: string;
  name: string;
  semester: number;
  skills: Array<SkillDto>;
  specialization: SpecializationDto;
  year: number;
}
