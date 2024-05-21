import { TestBed } from '@angular/core/testing';

import { OfertantesService } from './ofertantes.service';

describe('OfertantesService', () => {
  let service: OfertantesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OfertantesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
