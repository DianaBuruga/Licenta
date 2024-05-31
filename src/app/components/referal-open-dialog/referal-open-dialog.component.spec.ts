import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReferalOpenDialogComponent } from './referal-open-dialog.component';

describe('ReferalOpenDialogComponent', () => {
  let component: ReferalOpenDialogComponent;
  let fixture: ComponentFixture<ReferalOpenDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReferalOpenDialogComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ReferalOpenDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
