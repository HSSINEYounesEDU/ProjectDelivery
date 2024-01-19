import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckpointDeliveredComponent } from './checkpoint-delivered.component';

describe('CheckpointDeliveredComponent', () => {
  let component: CheckpointDeliveredComponent;
  let fixture: ComponentFixture<CheckpointDeliveredComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CheckpointDeliveredComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CheckpointDeliveredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
