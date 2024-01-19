import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckpointSendCargoComponent } from './checkpoint-send-cargo.component';

describe('CheckpointSendCargoComponent', () => {
  let component: CheckpointSendCargoComponent;
  let fixture: ComponentFixture<CheckpointSendCargoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CheckpointSendCargoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CheckpointSendCargoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
