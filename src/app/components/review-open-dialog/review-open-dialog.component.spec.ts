import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewOpenDialogComponent } from './review-open-dialog.component';

describe('ReviewOpenDialogComponent', () => {
  let component: ReviewOpenDialogComponent;
  let fixture: ComponentFixture<ReviewOpenDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReviewOpenDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReviewOpenDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
