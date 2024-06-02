import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyProfileOpenDialogComponent } from './company-profile-open-dialog.component';

describe('CompanyProfileOpenDialogComponent', () => {
  let component: CompanyProfileOpenDialogComponent;
  let fixture: ComponentFixture<CompanyProfileOpenDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CompanyProfileOpenDialogComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(CompanyProfileOpenDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
