import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyRecruitersComponent } from './company-recruiters.component';

describe('CompanyRecruitersComponent', () => {
  let component: CompanyRecruitersComponent;
  let fixture: ComponentFixture<CompanyRecruitersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CompanyRecruitersComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(CompanyRecruitersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
