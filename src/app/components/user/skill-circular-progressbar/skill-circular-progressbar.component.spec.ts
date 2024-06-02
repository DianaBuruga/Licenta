import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillCircularProgressbarComponent } from './skill-circular-progressbar.component';

describe('SkillCircularProgressbarComponent', () => {
  let component: SkillCircularProgressbarComponent;
  let fixture: ComponentFixture<SkillCircularProgressbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SkillCircularProgressbarComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(SkillCircularProgressbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
