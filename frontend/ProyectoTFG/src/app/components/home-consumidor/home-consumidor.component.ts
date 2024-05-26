import { Component } from '@angular/core';
import { ConsumidoresService } from '../../services/consumidores.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Consumidores } from '../../models/Consumidores';
import { UsuarioService } from '../../services/usuario.service';
import { NavbarComponent } from '../navbar/navbar.component';
import { AuthService } from '../../services/auth.service';
import { SolicitudesActividades } from '../../models/SolicitudesActividades';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home-consumidor',
  standalone: true,
  imports: [NavbarComponent, CommonModule],
  templateUrl: './home-consumidor.component.html',
  styleUrl: './home-consumidor.component.css'
})
export class HomeConsumidorComponent {
  public consumidor: Consumidores | null = null;
  public usuario: string | null = null;
  public idConsumidor: number | null = null; 
  public idActividad: number | null = null; 
  public solicitudesActividades: SolicitudesActividades[] = [];

  constructor(
    private usuarioService: UsuarioService,
    private consumidoresService: ConsumidoresService,
    private authService: AuthService,
    private ruta: Router,
    private rutaActiva: ActivatedRoute
  ) { }

  ngOnInit(): void {
    const userId = this.rutaActiva.snapshot.params["user"];
    if (userId) {
      console.log("ID RUTA: ", userId);
      this.usuarioService.usuario = userId;
      this.usuario = userId; 
      this.obtenerConsumidorPorUsername(userId);
      this.guardarIdConsumidorLocalStorage(userId); 
      this.obtenerIdConsumidorLocalStorage(); 

      if (this.idConsumidor) {
        this.obtenerSolicitudesActividadesPorConsumidor(this.idConsumidor);
      }

    } else {
      console.error("No se proporcionó un ID de usuario en la ruta.");
    }
  }

  obtenerConsumidorPorUsername(user: string): void {
    this.consumidoresService.obtenerConsumidorPorUsername(user)
      .subscribe({
        next: (consumidor: Consumidores) => {
          console.log('Consumidor:', consumidor);
          this.consumidor = consumidor;
        },
        error: (error) => {
          console.error('Error al obtener el consumidor:', error);
        }
      });
  }

  guardarIdConsumidorLocalStorage(user: string): void {
    this.authService.guardarIdConsumidorEnLocalStorage(user);
  }

  obtenerIdConsumidorLocalStorage(): void {
    this.idConsumidor = this.authService.obtenerIdConsumidorDeLocalStorage();
    console.log("DESDE EL HOME, ID CONSUMIDOR: ", this.idConsumidor);
  }

  obtenerSolicitudesActividadesPorConsumidor(idConsumidor: number): void {
    this.consumidoresService.obtenerSolicitudesActividadesPorConsumidor(idConsumidor)
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
  escribirReview(idConsumidor: number, idActividad: number): void {
    this.ruta.navigate(['/consumidores/home', this.usuario, 'escribir-review', idConsumidor, idActividad]);
  }

  actualizarUsuario(): void{
    this.ruta.navigate(['/consumidores/home', this.usuario, 'actualizar-usuario']);
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
