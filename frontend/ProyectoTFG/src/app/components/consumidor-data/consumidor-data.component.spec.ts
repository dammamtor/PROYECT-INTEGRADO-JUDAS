import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsumidorDataComponent } from './consumidor-data.component';

describe('ConsumidorDataComponent', () => {
  let component: ConsumidorDataComponent;
  let fixture: ComponentFixture<ConsumidorDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConsumidorDataComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConsumidorDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
