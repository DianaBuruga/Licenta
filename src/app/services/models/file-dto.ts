/* tslint:disable */

/* eslint-disable */
export interface FileDto {
  content: Array<string>;
  name: string;
  tableId: string;
  tableName: string;
  type: 'CV' | 'CERTIFICATE' | 'POST_PHOTO' | 'PROFILE_PHOTO' | 'ASSETS';
}
