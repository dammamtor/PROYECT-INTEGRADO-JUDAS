import { Injectable } from '@angular/core';
import { ConsumidoresService } from './consumidores.service';
import { Consumidores } from '../models/Consumidores';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private consumidorService: ConsumidoresService) { }

  guardarNombreUsuarioEnSesion(nombreUsuario: string) {
    sessionStorage.setItem('nombreUsuario', nombreUsuario);
  }

  obtenerNombreUsuarioEnSesion(): string | null {
    return sessionStorage.getItem('nombreUsuario');
  }

  limpiarSesion() {
    sessionStorage.clear();
  }

  guardarIdConsumidorEnLocalStorage(user: string) {
    this.consumidorService.obtenerConsumidorPorUsername(user)
      .subscribe({
        next: (consumidor: Consumidores) => {
          localStorage.setItem('idConsumidor', consumidor.id.toString());
        },
        error: (error) => {
          console.error('Error al obtener el consumidor:', error);
        }
      });
  }

  obtenerIdConsumidorDeLocalStorage(): number | null {
    const idString = localStorage.getItem('idConsumidor');
    console.log("idConsumidor: ", idString);
    return idString ? parseInt(idString, 10) : null;
  }
}
