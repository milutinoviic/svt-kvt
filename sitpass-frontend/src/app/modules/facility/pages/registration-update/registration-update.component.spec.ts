import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationUpdateComponent } from './registration-update.component';

describe('RegistrationUpdateComponent', () => {
  let component: RegistrationUpdateComponent;
  let fixture: ComponentFixture<RegistrationUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RegistrationUpdateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrationUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
