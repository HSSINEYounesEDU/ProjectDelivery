import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckpointInProgressComponent } from './checkpoint-in-progress.component';

describe('CheckpointInProgressComponent', () => {
  let component: CheckpointInProgressComponent;
  let fixture: ComponentFixture<CheckpointInProgressComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CheckpointInProgressComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CheckpointInProgressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
