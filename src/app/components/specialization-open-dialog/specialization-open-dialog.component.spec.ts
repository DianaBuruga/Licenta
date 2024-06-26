import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SpecializationOpenDialogComponent} from './specialization-open-dialog.component';

describe('SpecializationOpenDialogComponent', () => {
  let component: SpecializationOpenDialogComponent;
  let fixture: ComponentFixture<SpecializationOpenDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpecializationOpenDialogComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(SpecializationOpenDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
