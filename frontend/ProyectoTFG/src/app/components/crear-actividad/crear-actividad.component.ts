import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OfertantesService } from '../../services/ofertantes.service';
import { ActividadesRequestDTO } from '../../models/DTO/ActividadesRequestDTO';
import { FormsModule } from '@angular/forms';
import { TipoActividadesService } from '../../services/tipo-actividades.service';
import { TipoActividad } from '../../models/TipoActividad';

@Component({
  selector: 'app-crear-actividad',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './crear-actividad.component.html',
  styleUrl: './crear-actividad.component.css'
})
export class CrearActividadComponent {
  ofertanteId!: number;
  actividadForm: ActividadesRequestDTO = {
    nombre: '',
    descripcion: '',
    fec_inicio: new Date(),
    fec_final: new Date(),
    precio: '',
    materiales: '',
    tipoActividadId: 0
  };
  tiposActividades: TipoActividad[] = []; // Lista de tipos de actividades

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private ofertantesService: OfertantesService,
    private tipoService: TipoActividadesService
  ) { }

  ngOnInit(): void {
    this.ofertanteId = +this.route.snapshot.paramMap.get('idOfertante')!;
    console.log("ID Ofertante:", this.ofertanteId);

    // Cargar tipos de actividades al iniciar el componente
    this.tipoService.listarTipoActividades().subscribe(
      (tipos) => {
        this.tiposActividades = tipos;
      },
      (error) => {
        console.error('Error al cargar los tipos de actividades:', error);
      }
    );
  }

  onSubmit(): void {
    this.ofertantesService.crearActividadParaOfertante(this.ofertanteId, this.actividadForm)
      .subscribe({
        next: (actividad) => {
          console.log('Actividad creada:', actividad);
          setTimeout(() => {
            this.irAactividades();
          }, 2000);
        },
        error: (error) => {
          console.error('Error al crear la actividad:', error);
        }
      });
  }

  irAactividades(): void {
    this.router.navigate(["ofertantes/lista-actividades-ofertante"]);
  }
}
