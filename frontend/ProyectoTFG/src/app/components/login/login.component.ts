import { Component } from '@angular/core';
import { UsuarioService } from '../../services/usuario.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  public user: string = '';
  public password: string = '';
  public tipoSesion: string = '1'; 

  constructor(private usuarioService: UsuarioService, private ruta: Router, private rutaActiva: ActivatedRoute) { 
    console.log("ESTAS EN INICIAR SESION");
  }

  iniciarSesion() {
    this.usuarioService.iniciarSesion(this.user, this.password, this.tipoSesion).subscribe({
      next: response => {
        if (this.tipoSesion === '1') {
          this.ruta.navigate(["consumidores/home", this.user]);
        } else if (this.tipoSesion === '2') {
          this.ruta.navigate(["ofertantes"]);
        } 
      },
      error: error => {
        console.error(error);
      }
    });
  }
  
}
