import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientDeliveredComponent } from './client-delivered.component';

describe('ClientDeliveredComponent', () => {
  let component: ClientDeliveredComponent;
  let fixture: ComponentFixture<ClientDeliveredComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ClientDeliveredComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ClientDeliveredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
