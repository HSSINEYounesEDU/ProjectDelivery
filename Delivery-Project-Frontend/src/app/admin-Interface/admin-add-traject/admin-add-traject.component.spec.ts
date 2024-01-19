import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddTrajectComponent } from './admin-add-traject.component';

describe('AdminAddTrajectComponent', () => {
  let component: AdminAddTrajectComponent;
  let fixture: ComponentFixture<AdminAddTrajectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminAddTrajectComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminAddTrajectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
