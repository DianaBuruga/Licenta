import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LanguageOpenDialogComponent } from './language-open-dialog.component';

describe('LanguageOpenDialogComponent', () => {
  let component: LanguageOpenDialogComponent;
  let fixture: ComponentFixture<LanguageOpenDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LanguageOpenDialogComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(LanguageOpenDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
