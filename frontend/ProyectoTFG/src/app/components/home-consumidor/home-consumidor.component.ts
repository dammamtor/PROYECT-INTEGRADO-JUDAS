import { Component } from '@angular/core';
import { ConsumidoresService } from '../../services/consumidores.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Consumidores } from '../../models/Consumidores';
import { UsuarioService } from '../../services/usuario.service';
import { NavbarComponent } from '../navbar/navbar.component';
import { AuthService } from '../../services/auth.service';

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
  public idConsumidor: number | null = null; // Agrega una nueva variable para almacenar el id del consumidor

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
      this.usuario = userId; // Guarda el ID del usuario en la variable usuario
      this.obtenerConsumidorPorUsername(userId);
      this.guardarIdConsumidorLocalStorage(userId); // Llama al método para guardar el ID del consumidor en el localStorage
      this.obtenerIdConsumidorLocalStorage(); // Llama al método para obtener el ID del consumidor del localStorage
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
          // Aquí puedes manejar la respuesta del servicio
        },
        error: (error) => {
          console.error('Error al obtener el consumidor:', error);
          // Aquí puedes manejar el error
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
}
