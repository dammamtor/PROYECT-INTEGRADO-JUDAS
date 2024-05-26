import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { CommonModule } from '@angular/common';
import { ConsumidoresService } from '../../services/consumidores.service';
import { AuthService } from '../../services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Consumidores } from '../../models/Consumidores';
import { Reseñas } from '../../models/Reseñas';

@Component({
  selector: 'app-lista-reviews',
  standalone: true,
  imports: [NavbarComponent, CommonModule],
  templateUrl: './lista-reviews.component.html',
  styleUrl: './lista-reviews.component.css'
})
export class ListaReviewsComponent {
  public idConsumidor: number | null = null;
  public consumidor: Consumidores | null = null;
  public userId: string = ""
  public resenas: Reseñas[] = []

  constructor(
    private consumidoresService: ConsumidoresService,
    private authService: AuthService,
    private ruta: Router,
    private rutaActiva: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.userId = this.rutaActiva.snapshot.params["user"];
    if (this.userId) {
      console.log("ID RUTA: ", this.userId);
      this.obtenerIdConsumidorLocalStorage();
      this.obtenerResenasPorIdConsumidor();
    } else {
      console.error("No se proporcionó un ID de usuario en la ruta.");
    }
  }

  obtenerIdConsumidorLocalStorage(): void {
    this.idConsumidor = this.authService.obtenerIdConsumidorDeLocalStorage();
    console.log("DESDE EL HOME, ID CONSUMIDOR: ", this.idConsumidor);
  }

  obtenerResenasPorIdConsumidor(): void {
    if (this.idConsumidor) {
      this.consumidoresService.obtenerReseñaPorIDconsumidor(this.idConsumidor).subscribe(
        (resenas: Reseñas[]) => {
          console.log("Reseñas obtenidas:", resenas);
          this.resenas = resenas;
        },
        error => {
          console.error("Error al obtener reseñas:", error);
        }
      );
    } else {
      console.error("No se ha obtenido el ID del consumidor.");
    }
  }

  borrarReview(idReseña: number): void {
    this.consumidoresService.borrarReseña(idReseña).subscribe(
      (respuesta: { eliminar: boolean }) => {
        if (respuesta.eliminar) {
          console.log("Reseña eliminada exitosamente.");
          // Aquí puedes realizar alguna acción adicional, como volver a cargar las reseñas actualizadas
          this.obtenerResenasPorIdConsumidor();
        } else {
          console.error("La reseña no pudo ser eliminada.");
        }
      },
      error => {
        console.error("Error al intentar eliminar la reseña:", error);
      }
    );
  }
}
