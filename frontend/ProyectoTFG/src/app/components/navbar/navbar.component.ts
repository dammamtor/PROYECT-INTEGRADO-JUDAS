import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  nombreUsuario: string | null;
  rolUsuario: string | null;

  constructor(private router: Router, private authService: AuthService) {
    this.nombreUsuario = this.authService.obtenerNombreUsuarioEnSesion();
    this.rolUsuario = this.authService.obtenerRolUsuarioEnSesion();
  }

  ngOnInit(): void {}

  irAConsumidores(): void {
    if (this.rolUsuario === 'CONSUMER') {
      this.router.navigate(['/consumidores']);
    } else if (this.rolUsuario === 'PROVIDER') {
      this.router.navigate(['/ofertantes']);
    } else {
      console.error('Acceso denegado: el usuario no tiene un rol válido');
    }
  }

  irAActividades(): void {
    this.router.navigate(['/actividades']);
  }
  irAHome(): void {
    if (this.rolUsuario === 'CONSUMER') {
      this.router.navigate(['/consumidores/home', this.nombreUsuario]);
    } else if (this.rolUsuario === 'PROVIDER') {
      this.router.navigate(['/ofertantes/home', this.nombreUsuario]);
    } else {
      console.error('Rol de usuario no reconocido');
    }
  }

  cerrarSesion(): void {
    this.authService.limpiarSesion();
    this.router.navigate(['/login']);
  }
}
