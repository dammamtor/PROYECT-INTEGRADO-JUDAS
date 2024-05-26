import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';
import { UsuarioService } from '../../services/usuario.service';
import { Usuarios } from '../../models/Usuarios';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-update-usario',
  standalone: true,
  imports: [NavbarComponent, FormsModule],
  templateUrl: './update-usario.component.html',
  styleUrl: './update-usario.component.css'
})
export class UpdateUsarioComponent {
  public usuario: string = "";
  rolUsuario: string | null;
  public usuarioUpdate: Usuarios = {
    id: 0,
    userName: "",
    password: "",
    email: "",
    nombre: "",
    apellidos: "",
    nif: "",
    telefono: "",
    direccion: ""
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private usuarioService: UsuarioService
  ) {
    this.rolUsuario = this.authService.obtenerRolUsuarioEnSesion();
  }

  ngOnInit(): void {
    this.usuario = this.route.snapshot.params["user"];
    console.log("Nombre usuario: ", this.usuario);

    // Llamar al método para obtener el usuario por su nombre de usuario
    this.usuarioService.obtenerUsuarioPorUsername(this.usuario).subscribe(
      (data: Usuarios) => {
        console.log("Usuario obtenido:", data);
        // Rellenar usuarioUpdate con los datos obtenidos
        this.usuarioUpdate = {
          id: data.id,
          userName: data.userName,
          password: data.password,
          email: data.email,
          nombre: data.nombre,
          apellidos: data.apellidos,
          nif: data.nif,
          telefono: data.telefono,
          direccion: data.direccion
        };
      },
      (error) => {
        console.error("Error al obtener el usuario:", error);
      }
    );
  }


  onSubmit(): void {
    // Llamar al método de actualización de usuario
    this.usuarioService.actualizarUsuario(this.usuarioUpdate.id, this.usuarioUpdate).subscribe(
      (response) => {
        console.log("Usuario actualizado exitosamente:", response);
        this.irAHome()
      },
      (error) => {
        console.error("Error al actualizar el usuario:", error);
      }
    );
  }

  irAHome(): void {
    if (this.rolUsuario === 'CONSUMER') {
      this.router.navigate(['/consumidores/home', this.usuario]);
    } else if (this.rolUsuario === 'PROVIDER') {
      this.router.navigate(['/ofertantes/home', this.usuario]);
    } else {
      console.error('Rol de usuario no reconocido');
    }
  }
}
