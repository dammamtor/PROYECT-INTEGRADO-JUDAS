import { Component } from '@angular/core';
import { ConsumidoresService } from '../../services/consumidores.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Consumidores } from '../../models/Consumidores';
import { UsuarioService } from '../../services/usuario.service';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-home-consumidor',
  standalone: true,
  imports: [NavbarComponent],
  templateUrl: './home-consumidor.component.html',
  styleUrl: './home-consumidor.component.css'
})
export class HomeConsumidorComponent {
  public consumidor: Consumidores | null = null;
  public usuario: string | null = null;


  constructor(private usuarioService: UsuarioService, private consumidoresService: ConsumidoresService, private ruta: Router, private rutaActiva: ActivatedRoute) { }

  ngOnInit(): void {
    const userId = this.rutaActiva.snapshot.params["user"];
    if (userId) {
      console.log("ID RUTA: ", userId);
      this.usuarioService.usuario = userId;
      this.obtenerConsumidorPorUsername(userId);
    } else {
      console.error("No se proporcionó un ID de usuario en la ruta.");
    }
  }

  obtenerConsumidorPorUsername(user: string): void {
    this.consumidoresService.obtenerConsumidorPorUsername(user)
      .subscribe(
        (consumidor: Consumidores) => {
          console.log('Consumidor:', consumidor);
          this.consumidor = consumidor;
          // Aquí puedes manejar la respuesta del servicio
        },
        (error) => {
          console.error('Error al obtener el consumidor:', error);
          // Aquí puedes manejar el error
        }
      );
  }
}
