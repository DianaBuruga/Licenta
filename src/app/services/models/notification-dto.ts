/* tslint:disable */
/* eslint-disable */
import { UserDto } from '../models/user-dto';
export interface NotificationDto {
  date: string;
  id?: string;
  notificationContent: string;
  user: UserDto;
}
