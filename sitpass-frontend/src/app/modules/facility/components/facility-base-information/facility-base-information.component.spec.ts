import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacilityBaseInformationComponent } from './facility-base-information.component';

describe('FacilityBaseInformationComponent', () => {
  let component: FacilityBaseInformationComponent;
  let fixture: ComponentFixture<FacilityBaseInformationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FacilityBaseInformationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacilityBaseInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
