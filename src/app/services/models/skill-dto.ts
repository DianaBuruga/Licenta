/* tslint:disable */
/* eslint-disable */
import { BibliographyDto } from '../models/bibliography-dto';
import { CourseDto } from '../models/course-dto';
import { UserSkillsDto } from '../models/user-skills-dto';

/**
 * Skill that will be deleted
 */
export interface SkillDto {
  bibliographies: Array<BibliographyDto>;
  courses: Array<CourseDto>;
  id?: string;
  name: string;
  userSkills: Array<UserSkillsDto>;
}
