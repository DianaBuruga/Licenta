import { CommonModule, NgFor } from '@angular/common';
import {CUSTOM_ELEMENTS_SCHEMA, Component } from '@angular/core';
import { MatChipsModule } from '@angular/material/chips';
import { BibliographyDto, CourseDto } from '../../services/models';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { BibliographyOpenDialogComponent } from '../../components/bibliography-open-dialog/bibliography-open-dialog.component';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { BibliographyService, CourseService, FileService } from '../../services/services';
import Swal from 'sweetalert2';
import { CourseOpenDialogComponent } from '../../components/course-open-dialog/course-open-dialog.component';
import { ViewFileById1$Params } from '../../services/fn/file/view-file-by-id-1';
import { FileData } from '../../services/models/file-data';
import { SearchComponent } from '../../components/search/search.component';

@Component({
  selector: 'app-bibliography',
  standalone: true,
  imports: [
    MatChipsModule,
    NgFor,
    CommonModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
    SearchComponent
  ],
  templateUrl: './bibliography.component.html',
  styleUrl: './bibliography.component.scss',
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BibliographyComponent {
emptyBibliography = {};
emptyCourse = {};
bibliographies:BibliographyDto[] = [];
courses: CourseDto[] = [];
error: any = null;
bibliographyFileMap: Map<string, FileData> = new Map();
courseFileMap: Map<string, FileData> = new Map();
bibliographyEndpoint: string = 'bibliographies';
courseEndpoint: string = 'courses';

processBibliographies(filteredBibliographies: BibliographyDto[]) {
  this.bibliographies = filteredBibliographies;
}

processCourses(courses: CourseDto[]) {
  this.courses = courses;
}

constructor(private fileService: FileService, public dialog: MatDialog, private bibliographyService: BibliographyService, private courseService: CourseService) {
  this.bibliographyService.findAllBibliographies().subscribe({
    next: (response: BibliographyDto[]) => {
      this.bibliographies = response;
      console.log('Bibliographies', this.bibliographies);
      this.bibliographies.forEach(bibliography => {
        try{
        if (bibliography.id) {
          const id = bibliography.id;
          const param = {
            id: bibliography.id,
            table: 'bibliography',
            type: 'POST_PHOTO'
          } as ViewFileById1$Params;
          this.fileService.viewFileById1(param).subscribe({
            next: (response: FileData) => {
              console.log('Successful response:', response);
              this.bibliographyFileMap.set(id, response);
            }
          });
        }
      }catch(e){}
      });
    },
    error: (error: any) => {
      console.error('Error getting bibliographies', error);
    },
  });

  this.courseService.findAllCourses().subscribe({
    next: (response: CourseDto[]) => {
      this.courses = response;
      console.log('Courses', this.courses);
      this.courses.forEach(course => {
        try{
        if (course.id) {
          const id = course.id;
          const param = {
            id: course.id,
            table: 'course',
            type: 'POST_PHOTO'
          } as ViewFileById1$Params;
          this.fileService.viewFileById1(param).subscribe({
            next: (response: FileData) => {
              console.log('Successful response:', response);
              this.courseFileMap.set(id, response);
            }
          });
        }
      }catch(e){}
      });
    },
    error: (error: any) => {
      console.error('Error getting courses', error);
    },
  });
}

openDialog(bibliography: any): void {
  const dialogRef = this.dialog.open(BibliographyOpenDialogComponent, {
    width: '50%',
    data: {bibliography: bibliography, file: this.bibliographyFileMap.get(bibliography.id)??undefined}
  });

  dialogRef.afterClosed().subscribe(result => {
    console.log('Result', result);
    if (result.bibliography) {
      Swal.fire({
        icon: "success",
        title: "Information was successfully saved",
        showConfirmButton: false,
        timer: 1500
      });
      const index = this.bibliographies?.findIndex(x => x.id === result.bibliography.id);
      if (index !== -1 && index !== undefined && index !== null && this.bibliographies !== undefined) {
        this.bibliographies[index] = result.bibliography;
        bibliography = result.bibliography;
      } else {
        this.bibliographies?.push(result.bibliography);
      }
      this.bibliographies = [...this.bibliographies ?? []];
      console.log('File', result.file)
      this.bibliographyFileMap.set(bibliography.id, result.file);
      this.bibliographyFileMap = new Map(this.bibliographyFileMap);
      console.log('Bibliographies', this.bibliographies);
    }
    console.log('Dialog was closed');
  });
}

deleteBibliography(bibliography: any): void {
  console.log('Deleting bibliography', bibliography.id);
  Swal.fire({
    title: "Are you sure?",
    text: "You won't be able to revert this!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3f51b5",
    cancelButtonColor: "#d33",
    confirmButtonText: "Yes, delete it!"
  }).then((result) => {
    if (result.isConfirmed) {
      if (bibliography.id !== undefined) {
        const params = {id: bibliography.id};
        this.bibliographyService.deleteBibliography(params).subscribe({
          next: () => {
            console.log('bibliography deleted successfully');
            let index = this.bibliographies?.indexOf(bibliography);
            if (index !== undefined && index !== -1) {
              this.bibliographies?.splice(index, 1);
            }
          },
          error: (error: any) => {
            console.error('Error deleting bibliography', error);
          },
        });
      }
    }
  });
}
openCourseDialog(course: any): void {
  const dialogRef = this.dialog.open(CourseOpenDialogComponent, {
    width: '50%',
    data: {course: course, file: this.courseFileMap.get(course.id)??undefined}
  });

  dialogRef.afterClosed().subscribe(result => {
    console.log('Result', result);
    if (result.course) {
      Swal.fire({
        icon: "success",
        title: "Information was successfully saved",
        showConfirmButton: false,
        timer: 1500
      });
      const index = this.courses?.findIndex(x => x.id === result.course.id);
      if (index !== -1 && index !== undefined && index !== null && this.courses !== undefined) {
        this.courses[index] = result.course;
        course = result.course;
      } else {
        this.courses?.push(result.course);
      }
      this.courses = [...this.courses ?? []];
      this.courseFileMap.set(course.id, result.file);
      this.courseFileMap = new Map(this.courseFileMap);
      console.log('Projects and Competitions', this.courses);
    }
    console.log('Dialog was closed');
  });
}

deleteCourse(course: any): void {
  console.log('Deleting course', course.id);
  Swal.fire({
    title: "Are you sure?",
    text: "You won't be able to revert this!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3f51b5",
    cancelButtonColor: "#d33",
    confirmButtonText: "Yes, delete it!"
  }).then((result) => {
    if (result.isConfirmed) {
      if (course.id !== undefined) {
        const params = {id: course.id};
        this.courseService.deleteCourse(params).subscribe({
          next: () => {
            console.log('Course deleted successfully');
            let index = this.courses?.indexOf(course);
            if (index !== undefined && index !== -1) {
              this.courses?.splice(index, 1);
            }
          },
          error: (error: any) => {
            console.error('Error deleting course', error);
          },
        });
      }
    }
  });
}
  types = ['Course', 'Bibliography'];
  selectedType = 'Bibliography';

  selectType(type: string) {
    this.selectedType = type;
  }


  openFile(id: string | undefined): void {
    if (id !== undefined) {
      const param = {
        id: id,
        table:  this.selectedType.toLowerCase(),
        type: 'POST_PHOTO'
      } as ViewFileById1$Params;
      this.fileService.viewFileById1(param).subscribe({
        next: (response: FileData) => {
          const blobUrl = URL.createObjectURL(response.blob);
          const link = document.createElement('a');
          link.download = response.name;
          link.href = blobUrl;
          link.target = '_blank';
          link.click();
        }
      });
    }
  }

  isSupportedByGoogleDocsViewer(fileType: string): boolean {
    const supportedTypes = [
      'application/pdf',
      'application/msword',
      'application/vnd.openxmlformats-officedocument.wordprocessingml.document',
      'application/vnd.ms-powerpoint',
      'application/vnd.openxmlformats-officedocument.presentationml.presentation',
      'application/vnd.ms-excel',
      'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
      'text/plain',
      'image/png',
      'image/jpeg',
      'image/gif',
      'image/bmp',
      'image/tiff'
    ];

    return supportedTypes.includes(fileType);
  }
}
