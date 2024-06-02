/* tslint:disable */
/* eslint-disable */
import { CourseDto } from '../models/course-dto';
import { FacultyDto } from '../models/faculty-dto';
import { UserDto } from '../models/user-dto';
export interface SpecializationDto {
  courses: Array<CourseDto>;
  degree: 'BACHELOR' | 'MASTER' | 'PHD' | 'POSTDOC' | 'NONE';
  faculty: FacultyDto;
  finishedDate?: string;
  id?: string;
  name: string;
  startedDate: string;
  user: UserDto;
}
