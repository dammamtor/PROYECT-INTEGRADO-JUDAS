import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateUsarioComponent } from './update-usario.component';

describe('UpdateUsarioComponent', () => {
  let component: UpdateUsarioComponent;
  let fixture: ComponentFixture<UpdateUsarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateUsarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateUsarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
