import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConsumidoresService } from '../../services/consumidores.service';
import { SolicitudesActividades } from '../../models/SolicitudesActividades';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-solicitudes',
  standalone: true,
  imports: [NavbarComponent],
  templateUrl: './solicitudes.component.html',
  styleUrl: './solicitudes.component.css'
})
export class SolicitudesComponent {
  idConsumidor!: number;
  idActividad!: number;
  mensaje: string | null = null;
  confirmado: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private consumidoresService: ConsumidoresService
  ) {}

  ngOnInit(): void {
    this.idConsumidor = +this.route.snapshot.paramMap.get('idConsumidor')!;
    this.idActividad = +this.route.snapshot.paramMap.get('idActividad')!;
    console.log("ID Consumidor:", this.idConsumidor);
    console.log("ID Actividad:", this.idActividad);
  }

  confirmarSolicitud(): void {
    this.confirmado = true;
    this.enviarSolicitud();
  }

  cancelar(): void {
    this.router.navigate(['actividades']);
  }

  enviarSolicitud(): void {
    this.consumidoresService.enviarSolicitudActividad(this.idConsumidor, this.idActividad).subscribe({
      next: (respuesta: SolicitudesActividades) => {
        this.mensaje = 'Solicitud enviada con éxito. Redirigiendo a actividades...';
        console.log('Respuesta de la solicitud:', respuesta);
        // Redirigir de vuelta a ActividadesComponent después de 2 segundos
        setTimeout(() => {
          this.router.navigate(['actividades']);
        }, 2000);
      },
      error: (error: any) => {
        this.mensaje = 'Error al enviar la solicitud. Por favor, inténtalo de nuevo.';
        console.error('Error al enviar la solicitud:', error);
      }
    });
  }
}
