import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConsumidoresService } from '../../services/consumidores.service';
import { Reseñas } from '../../models/Reseñas';
import { ReviewPelado } from '../../models/ReviewPelado';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-crear-review',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './crear-review.component.html',
  styleUrl: './crear-review.component.css'
})
export class CrearReviewComponent {
  public idConsumidor: number | null = null;
  public idActividad: number | null = null;
  public usuario: string = "";
  public review: ReviewPelado = {
    id: -1,
    puntos: 0,
    comentario: "",
    fecha: new Date()
  }

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private consumidoresService: ConsumidoresService,
  ) { }

  ngOnInit(): void {
    this.idConsumidor = +this.route.snapshot.paramMap.get('idConsumidor')!;
    this.idActividad = +this.route.snapshot.paramMap.get('idActividad')!;
    this.usuario = this.route.snapshot.params["user"];
    console.log("ID Consumidor: ", this.idConsumidor);
    console.log("ID Actividad: ", this.idActividad);
    console.log("Constructor review: ", this.review);
    console.log("Nombre usuario: ", this.usuario);
  }

  crearYPublicarReview() {
    this.consumidoresService.publicarReview(this.idConsumidor!, this.idActividad!, this.review)
      .subscribe({
        next: (reviewPublicada) => {
          console.log('Revisión publicada con éxito:', reviewPublicada);
          console.log("TOMA REVIEW: ", reviewPublicada);
          setTimeout(() => {
            this.irAHome();
          }, 2000);
        },
        error: (error) => {
          console.error('Error al publicar la revisión:', error);
        }
      });
  }

  onSubmit() {
    this.crearYPublicarReview();
  }
  irAHome(): void {
    this.router.navigate(['/consumidores/home', this.usuario]);
  }
}
