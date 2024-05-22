import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaActividadesOfertanteComponent } from './lista-actividades-ofertante.component';

describe('ListaActividadesOfertanteComponent', () => {
  let component: ListaActividadesOfertanteComponent;
  let fixture: ComponentFixture<ListaActividadesOfertanteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaActividadesOfertanteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListaActividadesOfertanteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
