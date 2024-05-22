import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OfertantesService } from '../../services/ofertantes.service';
import { AuthService } from '../../services/auth.service';
import { Ofertantes } from '../../models/Ofertantes';
import { NavbarComponent } from '../navbar/navbar.component';
import { CommonModule } from '@angular/common';

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

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private ofertantesService: OfertantesService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.usuario = this.authService.obtenerNombreUsuarioEnSesion() || '';
    console.log("Usuario ofertante: ", this.usuario);
    this.obtenerOfertantePorUsername( this.usuario);
  }

  obtenerOfertantePorUsername(user: string): void {
    this.ofertantesService.obtenerOfertantePorUsername(user)
      .subscribe({
        next: (ofertante: Ofertantes) => {
          console.log('Ofertante:', ofertante);
          this.ofertante = ofertante;
        },
        error: (error) => {
          console.error('Error al obtener el ofertante:', error);
        }
      });
  }
}
