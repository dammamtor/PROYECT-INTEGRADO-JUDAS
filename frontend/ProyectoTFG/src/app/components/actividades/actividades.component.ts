import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { Actividades } from '../../models/Actividades';
import { Router } from '@angular/router';
import { ActividadesService } from '../../services/actividades.service';
import { CommonModule } from '@angular/common';
import { TipoActividadesService } from '../../services/tipo-actividades.service';
import { TipoActividad } from '../../models/TipoActividad';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-actividades',
  standalone: true,
  imports: [NavbarComponent, CommonModule],
  templateUrl: './actividades.component.html',
  styleUrl: './actividades.component.css'
})
export class ActividadesComponent {
  actividades: Actividades[] = [];
  tiposActividades: TipoActividad[] = [];
  filtroTipo: string | null = null;
  idConsumidor: number | null = null;

  constructor(
    private ruta: Router, 
    private actividadesService: ActividadesService,
    private tiposService: TipoActividadesService,
    private authService: AuthService 

  ) { }

  ngOnInit(): void {
    this.listarActividades();
    this.listarTipoActividades();
    this.obtenerIdConsumidor(); 
  }

  obtenerIdConsumidor(): void {
    this.idConsumidor = this.authService.obtenerIdConsumidorDeLocalStorage();
    console.log("DESDE ACTIVIDADES COMPONENT, ID CONSUMIDOR: ", this.idConsumidor);
  }

  listarActividades(): void {
    this.actividadesService.listarActividades().subscribe({
      next: (actividades) => {
        this.actividades = actividades;
        console.log("Listado actividades: ", actividades);
      },
      error: (error: any) => {
        console.log('Error al obtener las actividades:', error);
      }
    });
  }

  listarTipoActividades(): void {
    this.tiposService.listarTipoActividades().subscribe({
      next: (tipos) => {
        this.tiposActividades = tipos;
        console.log("Listado tipos de actividades: ", tipos);
      },
      error: (error: any) => {
        console.log('Error al obtener los tipos de actividades:', error);
      }
    });
  }

  filtrarPorTipo(event: any): void {
    const tipoNombre = event?.target?.value;
    this.filtroTipo = tipoNombre ? tipoNombre : null;
  
    if (this.filtroTipo !== null) {
      this.actividadesService.listarActividadesPorTipo(this.filtroTipo).subscribe({
        next: (actividades) => {
          this.actividades = actividades;
          console.log("Listado de actividades filtradas por tipo: ", actividades);
        },
        error: (error: any) => {
          console.log('Error al obtener las actividades filtradas por tipo:', error);
        }
      });
    } else {
      this.listarActividades(); // Si no se selecciona ningún tipo, listar todas las actividades nuevamente
    }
  }
  irASolicitudActividad(idConsumidor: number, idActividad: number): void {
    this.ruta.navigate(['solicitud-actividad', idConsumidor, idActividad]);
  }
}
