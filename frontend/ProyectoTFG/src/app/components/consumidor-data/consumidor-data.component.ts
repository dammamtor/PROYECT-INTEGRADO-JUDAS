import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';
import { Observable } from 'rxjs';
import { Consumidores } from '../../models/Consumidores';
import { ConsumidoresService } from '../../services/consumidores.service';
import { CommonModule } from '@angular/common';
import { Reseñas } from '../../models/Reseñas';

@Component({
  selector: 'app-consumidor-data',
  standalone: true,
  imports: [NavbarComponent, CommonModule],
  templateUrl: './consumidor-data.component.html',
  styleUrl: './consumidor-data.component.css'
})
export class ConsumidorDataComponent {
  consumidorId: number | undefined;
  consumidor$: Consumidores | null = null;
  reviews$: Reseñas[] | null = null; // Variable para almacenar las reseñas

  constructor(private route: ActivatedRoute, private consumidoresService: ConsumidoresService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.consumidorId = +params['id'];
      console.log('ID del consumidor:', this.consumidorId);
      this.obtenerConsumidorPorId(this.consumidorId);
      this.obtenerReseñasPorIdConsumidor(this.consumidorId); // Llamada al método para obtener reseñas
    });
  }

  obtenerConsumidorPorId(id: number): void {
    this.consumidoresService.obtenerConsumidorPorId(id)
      .subscribe(
        (consumidor: Consumidores) => {
          console.log('Datos del consumidor:', consumidor);
          this.consumidor$ = consumidor;
        },
        (error) => {
          console.error('Error al obtener el consumidor:', error);
          // Aquí puedes manejar el error
        }
      );
  }

  // Método para obtener las reseñas del consumidor por su ID
  obtenerReseñasPorIdConsumidor(id: number): void {
    this.consumidoresService.obtenerReseñaPorIDconsumidor(id)
      .subscribe(
        (reseñas: Reseñas[]) => {
          console.log('Reseñas del consumidor:', reseñas);
          this.reviews$ = reseñas;
        },
        (error) => {
          console.error('Error al obtener las reseñas del consumidor:', error);
          // Aquí puedes manejar el error
        }
      );
  }
}
