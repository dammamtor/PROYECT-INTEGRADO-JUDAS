import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OfertanteDataComponent } from './ofertante-data.component';

describe('OfertanteDataComponent', () => {
  let component: OfertanteDataComponent;
  let fixture: ComponentFixture<OfertanteDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OfertanteDataComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OfertanteDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
