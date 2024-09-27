import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisciplineCreateComponent } from './discipline-create.component';

describe('DisciplineCreateComponent', () => {
  let component: DisciplineCreateComponent;
  let fixture: ComponentFixture<DisciplineCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DisciplineCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisciplineCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
