import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteUsarioComponent } from './delete-usario.component';

describe('DeleteUsarioComponent', () => {
  let component: DeleteUsarioComponent;
  let fixture: ComponentFixture<DeleteUsarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeleteUsarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DeleteUsarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
