/* tslint:disable */
/* eslint-disable */
import { SkillDto } from '../models/skill-dto';
import { UserDto } from '../models/user-dto';

/**
 * Bibliography that will be deleted
 */
export interface BibliographyDto {
  date: string;
  id?: string;
  skill: SkillDto;
  text: string;
  title: string;
  writer: UserDto;
}
