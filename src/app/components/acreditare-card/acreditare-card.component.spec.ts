import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcreditareCardComponent } from './acreditare-card.component';

describe('AcreditareCardComponent', () => {
  let component: AcreditareCardComponent;
  let fixture: ComponentFixture<AcreditareCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AcreditareCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AcreditareCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
