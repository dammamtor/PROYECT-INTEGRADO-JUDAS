import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  constructor(private router: Router, private usuarioService: UsuarioService) {}

  irAHomeDelUsuario(): void {
    const usuario = this.usuarioService.usuario;
    if (usuario) {
      this.router.navigate(['/consumidores/user', usuario]);
    } else {
      console.error("No se ha proporcionado un nombre de usuario.");
    }
  }
}
