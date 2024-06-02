import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostedJobOpenDialogComponent } from './posted-job-open-dialog.component';

describe('PostedJobOpenDialogComponent', () => {
  let component: PostedJobOpenDialogComponent;
  let fixture: ComponentFixture<PostedJobOpenDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostedJobOpenDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PostedJobOpenDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
