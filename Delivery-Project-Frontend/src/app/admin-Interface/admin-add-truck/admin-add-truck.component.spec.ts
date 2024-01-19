import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddTruckComponent } from './admin-add-truck.component';

describe('AdminAddTruckComponent', () => {
  let component: AdminAddTruckComponent;
  let fixture: ComponentFixture<AdminAddTruckComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminAddTruckComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminAddTruckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
