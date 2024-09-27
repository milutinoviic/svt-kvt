import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkDayAddComponent } from './work-day-add.component';

describe('WorkDayAddComponent', () => {
  let component: WorkDayAddComponent;
  let fixture: ComponentFixture<WorkDayAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WorkDayAddComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WorkDayAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
