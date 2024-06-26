import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BibliographyOpenDialogComponent } from './bibliography-open-dialog.component';

describe('BibliographyOpenDialogComponent', () => {
  let component: BibliographyOpenDialogComponent;
  let fixture: ComponentFixture<BibliographyOpenDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BibliographyOpenDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BibliographyOpenDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
