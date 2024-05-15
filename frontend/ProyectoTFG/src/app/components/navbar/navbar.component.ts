import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  nombreUsuario: string | null;
  constructor(private router: Router, private usuarioService: UsuarioService,private authService: AuthService) {
    this.nombreUsuario = this.authService.obtenerNombreUsuarioEnSesion();
  }

  
  irAConsumidores(): void {
    this.router.navigate(['/consumidores']);
  }

  irAHome(): void {
    this.router.navigate(['/consumidores/home', this.nombreUsuario]);
  }

  irAActividades(): void{
    this.router.navigate(["/actividades"]);
  }

  cerrarSesion(): void {
    this.authService.limpiarSesion();
    this.router.navigate(['/login']);
  }
}
