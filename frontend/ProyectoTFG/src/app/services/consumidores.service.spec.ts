import { TestBed } from '@angular/core/testing';

import { ConsumidoresService } from './consumidores.service';

describe('ConsumidoresService', () => {
  let service: ConsumidoresService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConsumidoresService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
