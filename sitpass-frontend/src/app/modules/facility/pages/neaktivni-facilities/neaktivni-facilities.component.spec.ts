import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NeaktivniFacilitiesComponent } from './neaktivni-facilities.component';

describe('NeaktivniFacilitiesComponent', () => {
  let component: NeaktivniFacilitiesComponent;
  let fixture: ComponentFixture<NeaktivniFacilitiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NeaktivniFacilitiesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NeaktivniFacilitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
