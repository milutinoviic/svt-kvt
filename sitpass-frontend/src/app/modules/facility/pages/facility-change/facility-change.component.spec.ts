import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacilityChangeComponent } from './facility-change.component';

describe('FacilityChangeComponent', () => {
  let component: FacilityChangeComponent;
  let fixture: ComponentFixture<FacilityChangeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FacilityChangeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacilityChangeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
