import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileOpenDialogComponent } from './profile-open-dialog.component';

describe('ProfileOpenDialogComponent', () => {
  let component: ProfileOpenDialogComponent;
  let fixture: ComponentFixture<ProfileOpenDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfileOpenDialogComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ProfileOpenDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
