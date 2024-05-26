import { Component } from '@angular/core';
import { Ofertantes } from '../../models/Ofertantes';
import { UsuarioService } from '../../services/usuario.service';
import { OfertantesService } from '../../services/ofertantes.service';
import { AuthService } from '../../services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { SolicitudesActividades } from '../../models/SolicitudesActividades';
import { NavbarComponent } from '../navbar/navbar.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home-ofertante',
  standalone: true,
  imports: [NavbarComponent, CommonModule],
  templateUrl: './home-ofertante.component.html',
  styleUrl: './home-ofertante.component.css'
})
export class HomeOfertanteComponent {
  public ofertante: Ofertantes | null = null;
  public usuario: string | null = null;
  public idOfertante: number | null = null;
  public solicitudesActividades: SolicitudesActividades[] = [];


  constructor(
    private usuarioService: UsuarioService,
    private ofertantesService: OfertantesService,
    private authService: AuthService,
    private ruta: Router,
    private rutaActiva: ActivatedRoute
  ) { }

  ngOnInit(): void {
    const userId = this.rutaActiva.snapshot.params["user"];
    if (userId) {
      console.log("ID RUTA: ", userId);
      this.usuarioService.usuario = userId;
      this.usuario = userId; // Guarda el ID del usuario en la variable usuario
      this.obtenerOfertantePorUsername(userId);
      this.guardarIdOfertanteLocalStorage(userId); // Llama al método para guardar el ID del consumidor en el localStorage
      this.obtenerIdOfertanteLocalStorage(); // Llama al método para obtener el ID del consumidor del localStorage

      // Llama al método para obtener las solicitudes de actividades por el ID del consumidor
      if (this.idOfertante) {
        this.obtenerSolicitudesActividadesPorOfertante(this.idOfertante);
      }

    } else {
      console.error("No se proporcionó un ID de usuario en la ruta.");
    }
  }

  obtenerOfertantePorUsername(user: string): void {
    this.ofertantesService.obtenerOfertantePorUsername(user)
      .subscribe({
        next: (ofertante: Ofertantes) => {
          console.log('Ofertante:', ofertante);
          this.ofertante = ofertante;
          // Aquí puedes manejar la respuesta del servicio
        },
        error: (error) => {
          console.error('Error al obtener el ofertante:', error);
          // Aquí puedes manejar el error
        }
      });
  }

  guardarIdOfertanteLocalStorage(user: string): void {
    this.authService.guardarIdOfertanteEnLocalStorage(user);
  }

  obtenerIdOfertanteLocalStorage(): void {
    this.idOfertante = this.authService.obtenerIdOfertanteDeLocalStorage();
    console.log("DESDE EL HOME, ID OFERTANTE: ", this.idOfertante);
  }

  obtenerSolicitudesActividadesPorOfertante(idConsumidor: number): void {
    this.ofertantesService.obtenerSolicitudesActividadesPorOfertante(idConsumidor)
      .subscribe({
        next: (solicitudes: SolicitudesActividades[]) => {
          console.log('Solicitudes de actividades:', solicitudes);
          this.solicitudesActividades = solicitudes;
        },
        error: (error) => {
          console.error('Error al obtener las solicitudes de actividades:', error);
        }
      });
  }
  redirigirAceptarSolicitud(ofertanteId: number, idSolicitud: number): void {
    this.ruta.navigate([`/aceptar-solicitud/${ofertanteId}/${idSolicitud}`]);
  }

  actualizarUsuario(): void{
    this.ruta.navigate(['/ofertantes/home', this.usuario, 'actualizar-usuario']);
  }

  eliminarUsuarioPorId(id: number): void {
    this.usuarioService.eliminarUsuario(id).subscribe({
      next: () => {
        console.log("Usuario eliminado exitosamente");
        this.ruta.navigate(['/']);
      },
      error: (error) => {
        console.error("Error al intentar eliminar el usuario:", error);
      }
    });
  }
}
