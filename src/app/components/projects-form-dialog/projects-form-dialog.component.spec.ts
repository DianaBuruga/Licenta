import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectsFormDialogComponent } from './projects-form-dialog.component';

describe('ProjectsFormDialogComponent', () => {
  let component: ProjectsFormDialogComponent;
  let fixture: ComponentFixture<ProjectsFormDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProjectsFormDialogComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ProjectsFormDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
