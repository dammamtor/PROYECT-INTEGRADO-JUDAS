import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OfertantesService } from '../../services/ofertantes.service';
import { AuthService } from '../../services/auth.service';
import { Ofertantes } from '../../models/Ofertantes';
import { NavbarComponent } from '../navbar/navbar.component';
import { CommonModule } from '@angular/common';
import { Reseñas } from '../../models/Reseñas';

@Component({
  selector: 'app-lista-actividades-ofertante',
  standalone: true,
  imports: [NavbarComponent, CommonModule],
  templateUrl: './lista-actividades-ofertante.component.html',
  styleUrl: './lista-actividades-ofertante.component.css'
})
export class ListaActividadesOfertanteComponent {
  usuario: string = "";
  public ofertante: Ofertantes | null = null;
  review$: Reseñas[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private ofertantesService: OfertantesService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.usuario = this.authService.obtenerNombreUsuarioEnSesion() || '';
    console.log("Usuario ofertante: ", this.usuario);
    this.obtenerOfertantePorUsername(this.usuario);
  }

  obtenerOfertantePorUsername(user: string): void {
    this.ofertantesService.obtenerOfertantePorUsername(user)
      .subscribe({
        next: (ofertante: Ofertantes) => {
          console.log('Ofertante:', ofertante);
          this.ofertante = ofertante;
          this.obtenerReseñasPorOfertanteId(this.ofertante.id); // Obtener reseñas después de obtener los datos del ofertante
        },
        error: (error) => {
          console.error('Error al obtener el ofertante:', error);
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
