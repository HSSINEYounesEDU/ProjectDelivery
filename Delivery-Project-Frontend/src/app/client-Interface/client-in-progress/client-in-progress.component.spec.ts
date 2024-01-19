import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientInProgressComponent } from './client-in-progress.component';

describe('ClientInProgressComponent', () => {
  let component: ClientInProgressComponent;
  let fixture: ComponentFixture<ClientInProgressComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ClientInProgressComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ClientInProgressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
