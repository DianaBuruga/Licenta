import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserSkillsFormDialogComponent } from './user-skills-form-dialog.component';

describe('UserSkillsFormDialogComponent', () => {
  let component: UserSkillsFormDialogComponent;
  let fixture: ComponentFixture<UserSkillsFormDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserSkillsFormDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UserSkillsFormDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
