import { Injectable } from '@angular/core';
import { ConsumidoresService } from './consumidores.service';
import { Consumidores } from '../models/Consumidores';
import { OfertantesService } from './ofertantes.service';
import { Ofertantes } from '../models/Ofertantes';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private consumidorService: ConsumidoresService,
    private ofertantesService: OfertantesService
  ) { }

  guardarNombreUsuarioEnSesion(nombreUsuario: string): void {
    sessionStorage.setItem('nombreUsuario', nombreUsuario);
  }

  obtenerNombreUsuarioEnSesion(): string | null {
    return sessionStorage.getItem('nombreUsuario');
  }

  guardarRolUsuarioEnSesion(role: string): void {
    sessionStorage.setItem('rolUsuario', role);
    console.log('Rol de usuario guardado en la sesión:', role);
  }

  obtenerRolUsuarioEnSesion(): string | null {
    const rolUsuario = sessionStorage.getItem('rolUsuario');
    console.log('Rol de usuario obtenido en la sesión:', rolUsuario);
    return rolUsuario;
  }


  limpiarSesion(): void {
    sessionStorage.clear();
  }

  guardarIdConsumidorEnLocalStorage(user: string): void {
    this.consumidorService.obtenerConsumidorPorUsername(user).subscribe({
      next: (consumidor: Consumidores) => {
        localStorage.setItem('idConsumidor', consumidor.id.toString());
        if (consumidor.usuario && consumidor.usuario.role) {
          this.guardarRolUsuarioEnSesion(consumidor.usuario.role);
        } else {
          console.error('El rol del usuario no está disponible');
        }
      },
      error: (error) => {
        console.error('Error al obtener el consumidor:', error);
      },
    });
  }

  guardarIdOfertanteEnLocalStorage(user: string): void {
    this.ofertantesService.obtenerOfertantePorUsername(user).subscribe({
      next: (ofertante: Ofertantes) => {
        localStorage.setItem('idOfertante', ofertante.id.toString());
        if (ofertante.usuario && ofertante.usuario.role) {
          this.guardarRolUsuarioEnSesion(ofertante.usuario.role);
        } else {
          console.error('El rol del usuario no está disponible');
        }
      },
      error: (error) => {
        console.error('Error al obtener el ofertante:', error);
      },
    });
  }

  obtenerIdConsumidorDeLocalStorage(): number | null {
    const idString = localStorage.getItem('idConsumidor');
    console.log('idConsumidor:', idString);
    return idString ? parseInt(idString, 10) : null;
  }

  obtenerIdOfertanteDeLocalStorage(): number | null {
    const idString = localStorage.getItem('idOfertante');
    console.log('idOfertante:', idString);
    return idString ? parseInt(idString, 10) : null;
  }
}
