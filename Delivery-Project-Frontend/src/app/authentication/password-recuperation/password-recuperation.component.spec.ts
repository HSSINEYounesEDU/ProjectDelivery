import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PasswordRecuperationComponent } from './password-recuperation.component';

describe('PasswordRecuperationComponent', () => {
  let component: PasswordRecuperationComponent;
  let fixture: ComponentFixture<PasswordRecuperationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PasswordRecuperationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PasswordRecuperationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
