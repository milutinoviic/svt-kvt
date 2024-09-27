import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkDayDeleteComponent } from './work-day-delete.component';

describe('WorkDayDeleteComponent', () => {
  let component: WorkDayDeleteComponent;
  let fixture: ComponentFixture<WorkDayDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WorkDayDeleteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WorkDayDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
