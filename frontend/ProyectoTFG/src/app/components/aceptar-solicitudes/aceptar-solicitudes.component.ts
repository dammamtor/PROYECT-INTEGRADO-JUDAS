import { Component } from '@angular/core';
import { OfertantesService } from '../../services/ofertantes.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-aceptar-solicitudes',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './aceptar-solicitudes.component.html',
  styleUrl: './aceptar-solicitudes.component.css'
})
export class AceptarSolicitudesComponent {
  ofertanteId!: number;
  idSolicitud!: number;
  usuario: string = "";
  mensaje: string | null = null;
  confirmado: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private ofertantesService: OfertantesService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.ofertanteId = +this.route.snapshot.paramMap.get('ofertanteId')!;
    this.idSolicitud = +this.route.snapshot.paramMap.get('idSolicitud')!;
    // Recuperar el nombre de usuario del almacenamiento de sesión
    this.usuario = this.authService.obtenerNombreUsuarioEnSesion() || '';
    console.log("ID Ofertante:", this.ofertanteId);
    console.log("ID Solicitud:", this.idSolicitud);
    console.log("Usuario ofertante: ", this.usuario);
  }

  aceptarSolicitud(ofertanteId: number, idSolicitud: number): void {
    this.ofertantesService.aceptarSolicitudPorId(ofertanteId, idSolicitud).subscribe({
      next: (response) => {
        console.log('Solicitud aceptada:', response);
        setTimeout(() => {
          this.volveraHome();
        }, 2000);
      },
      error: (error) => {
        console.error('Error al aceptar la solicitud:', error);
      }
    });
  }

  volveraHome(): void {
    this.router.navigate(["ofertantes/home", this.usuario]);
  }
}
