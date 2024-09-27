import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReplyComponentComponent } from './reply-component.component';

describe('ReplyComponentComponent', () => {
  let component: ReplyComponentComponent;
  let fixture: ComponentFixture<ReplyComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ReplyComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReplyComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
