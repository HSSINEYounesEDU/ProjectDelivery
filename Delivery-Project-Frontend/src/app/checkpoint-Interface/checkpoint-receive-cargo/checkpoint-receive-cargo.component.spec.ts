import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckpointReceiveCargoComponent } from './checkpoint-receive-cargo.component';

describe('CheckpointReceiveCargoComponent', () => {
  let component: CheckpointReceiveCargoComponent;
  let fixture: ComponentFixture<CheckpointReceiveCargoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CheckpointReceiveCargoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CheckpointReceiveCargoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
