import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeConsumidorComponent } from './home-consumidor.component';

describe('HomeConsumidorComponent', () => {
  let component: HomeConsumidorComponent;
  let fixture: ComponentFixture<HomeConsumidorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomeConsumidorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HomeConsumidorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
