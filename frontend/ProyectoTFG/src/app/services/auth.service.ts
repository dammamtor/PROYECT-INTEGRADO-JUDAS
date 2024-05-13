import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  guardarNombreUsuarioEnSesion(nombreUsuario: string) {
    sessionStorage.setItem('nombreUsuario', nombreUsuario);
  }

  obtenerNombreUsuarioEnSesion(): string | null {
    return sessionStorage.getItem('nombreUsuario');
  }

  limpiarSesion() {
    sessionStorage.clear();
  }}
