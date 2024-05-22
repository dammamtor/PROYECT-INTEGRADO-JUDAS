import { Component } from '@angular/core';
import { Ofertantes } from '../../models/Ofertantes';
import { ActivatedRoute } from '@angular/router';
import { OfertantesService } from '../../services/ofertantes.service';
import { NavbarComponent } from '../navbar/navbar.component';
import { CommonModule } from '@angular/common';
import { Reseñas } from '../../models/Reseñas';

@Component({
  selector: 'app-ofertante-data',
  standalone: true,
  imports: [NavbarComponent, CommonModule],
  templateUrl: './ofertante-data.component.html',
  styleUrl: './ofertante-data.component.css'
})
export class OfertanteDataComponent {
  ofertanteId: number | undefined;
  ofertante$: Ofertantes | null = null;
  review$: Reseñas[] = [];


  constructor(private route: ActivatedRoute, private ofertantesService: OfertantesService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.ofertanteId = +params['id'];
      console.log('ID del ofertante:', this.ofertanteId);
      this.obtenerOfertantePorId(this.ofertanteId);
    });
  }

  obtenerOfertantePorId(id: number): void {
    this.ofertantesService.obtenerOfertantePorId(id).subscribe({
      next: (ofertante: Ofertantes) => {
        console.log('Datos del ofertante:', ofertante);
        this.ofertante$ = ofertante;
        this.obtenerReseñasPorOfertanteId(id); // Obtener reseñas después de obtener los datos del ofertante
      },
      error: (error) => {
        console.error('Error al obtener el ofertante:', error);
      },
      complete: () => {
        console.log('Solicitud completada.');
      }
    });
  }

  obtenerReseñasPorOfertanteId(id: number): void {
    this.ofertantesService.obtenerReseñasPorOfertanteId(id).subscribe({
      next: (reseñas: Reseñas[]) => {
        console.log('Reseñas obtenidas:', reseñas);
        this.review$ = reseñas;
      },
      error: (error) => {
        console.error('Error al obtener las reseñas:', error);
      },
      complete: () => {
        console.log('Solicitud de reseñas completada.');
      }
    });
  }

}
