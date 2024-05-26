import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcreditationFormDialogComponent } from './acreditation-form-dialog.component';

describe('AcreditationFormDialogComponent', () => {
  let component: AcreditationFormDialogComponent;
  let fixture: ComponentFixture<AcreditationFormDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AcreditationFormDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AcreditationFormDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
