import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';
import { Observable } from 'rxjs';
import { Consumidores } from '../../models/Consumidores';
import { ConsumidoresService } from '../../services/consumidores.service';
import { CommonModule } from '@angular/common';

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

  constructor(private route: ActivatedRoute, private consumidoresService: ConsumidoresService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.consumidorId = +params['id'];
      console.log('ID del consumidor:', this.consumidorId);
      this.obtenerConsumidorPorId(this.consumidorId);
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


}
