import { Component } from '@angular/core';
import { ActividadesRequestDTO } from '../../models/DTO/ActividadesRequestDTO';
import { TipoActividad } from '../../models/TipoActividad';
import { ActivatedRoute, Router } from '@angular/router';
import { OfertantesService } from '../../services/ofertantes.service';
import { TipoActividadesService } from '../../services/tipo-actividades.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-update-actividad',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './update-actividad.component.html',
  styleUrl: './update-actividad.component.css'
})
export class UpdateActividadComponent {
  ofertanteId!: number;
  idActividad!: number;
  actividadForm: ActividadesRequestDTO = {
    nombre: '',
    descripcion: '',
    fec_inicio: new Date(),
    fec_final: new Date(),
    precio: '',
    materiales: '',
    tipoActividadId: 0
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private ofertantesService: OfertantesService
  ) { }

  ngOnInit(): void {
    this.ofertanteId = +this.route.snapshot.paramMap.get('ofertanteId')!;
    this.idActividad = +this.route.snapshot.paramMap.get('idActividad')!;
    console.log("ID Ofertante:", this.ofertanteId);
    console.log("ID Actividad", this.idActividad);

    // Obtener los detalles de la actividad a actualizar
    this.ofertantesService.obtenerActividadPorId(this.ofertanteId, this.idActividad).subscribe(
      (actividad) => {
        this.actividadForm = {
          nombre: actividad.nombre,
          descripcion: actividad.descripcion,
          fec_inicio: actividad.fec_inicio,
          fec_final: actividad.fec_final,
          precio: actividad.precio,
          materiales: actividad.materiales,
          tipoActividadId: actividad.tipoActividadId
        };
        console.log("Actividad recogida: ", this.actividadForm);
      },
      (error) => {
        console.error('Error al obtener los detalles de la actividad:', error);
      }
    );
  }

  onSubmit(): void {
    this.ofertantesService.actualizarActividadOfertante(this.ofertanteId, this.idActividad, this.actividadForm)
      .subscribe({
        next: (actividad) => {
          console.log('Actividad actualizada:', actividad);
          setTimeout(() => {
            this.irAactividades();
          }, 2000);
        },
        error: (error) => {
          console.error('Error al actualizar la actividad:', error);
        }
      });
  }

  irAactividades(): void {
    this.router.navigate(["ofertantes/lista-actividades-ofertante"]);
  }

}
