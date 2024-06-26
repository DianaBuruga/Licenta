import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseOpenDialogComponent } from './course-open-dialog.component';

describe('CourseOpenDialogComponent', () => {
  let component: CourseOpenDialogComponent;
  let fixture: ComponentFixture<CourseOpenDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseOpenDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CourseOpenDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
