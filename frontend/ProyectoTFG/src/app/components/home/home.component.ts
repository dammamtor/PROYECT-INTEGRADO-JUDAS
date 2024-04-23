import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(private ruta: Router, private rutaActiva: ActivatedRoute) { }

  ngOnInit(): void {
  }

  register() {
    // Redirige a la página de registro
    this.ruta.navigate(['/sign-up']);
  }

  login() {
    // Redirige a la página de inicio de sesión
    this.ruta.navigate(['/login']);
  }
}
