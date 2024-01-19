import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckpointDeliveriesComponent } from './checkpoint-deliveries.component';

describe('CheckpointDeliveriesComponent', () => {
  let component: CheckpointDeliveriesComponent;
  let fixture: ComponentFixture<CheckpointDeliveriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CheckpointDeliveriesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CheckpointDeliveriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
