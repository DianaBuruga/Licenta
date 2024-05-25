/* tslint:disable */
/* eslint-disable */
import { SkillDto } from '../models/skill-dto';
import { UserDto } from '../models/user-dto';
export interface UserSkillsDto {
  proficiency: number;
  skill: SkillDto;
  skillId?: string;
  userDTO: UserDto;
  userId?: string;
}
