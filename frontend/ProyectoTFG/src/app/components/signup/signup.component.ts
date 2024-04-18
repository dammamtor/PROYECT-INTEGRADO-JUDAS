import { Component } from '@angular/core';
import { Usuarios } from '../../models/Usuarios';
import { UsuarioService } from '../../services/usuario.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  public usuario: Usuarios;
  public textoBoton: string;
  public tituloForm: string;

  constructor(private peticion: UsuarioService, private ruta: Router, private rutaActiva: ActivatedRoute) {
    console.log("HORA DE AÑADIR USUARIOS, CAPOEIRA");
    this.usuario = {
      id: -1,
      userName: '',
      password: '',
      email: '',
      nombre: '',
      apellidos: '',
      nif: '',
      telefono: '',
      direccion: '',
      role: '' 
    }; 
    this.textoBoton = 'Enviar'; 
    this.tituloForm = 'Formulario de Registro'; 
  }

  guardarUsuario() {
    this.peticion.addUsuario(this.usuario).subscribe({
      next: dato => {
        console.log("Usuario agregado: ", dato);
        this.volverLista();
      },
      error: error => console.log(error)
    });

  }

  volverLista() {
    this.ruta.navigate(["/"]);
  }

  onSubmit(usuario: Usuarios): void {
    console.log("ESTA ES LA PERSONA QUE HA LLEGADO POR EL FORMULARIO: ", usuario)
    if (this.usuario.id == -1) {
      this.guardarUsuario();
    }
  }
}
