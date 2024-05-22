import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { Ofertantes } from '../../models/Ofertantes';
import { Router } from '@angular/router';
import { OfertantesService } from '../../services/ofertantes.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ofertantes',
  standalone: true,
  imports: [NavbarComponent, CommonModule],
  templateUrl: './ofertantes.component.html',
  styleUrl: './ofertantes.component.css'
})
export class OfertantesComponent {
  ofertantes: Ofertantes[] = [];
  constructor(private ruta: Router, private ofertantesService: OfertantesService) { }

  ngOnInit(): void {
    this.listarOfertantes();
  }

  listarOfertantes(): void {
    this.ofertantesService.listarOfertantes()
      .subscribe(ofertantes => {
        this.ofertantes = ofertantes;
        console.log(ofertantes);
      });
  }

  verDetalles(id: number): void {
    this.ruta.navigate(['/ofertantes/data', id]);
  }
}
