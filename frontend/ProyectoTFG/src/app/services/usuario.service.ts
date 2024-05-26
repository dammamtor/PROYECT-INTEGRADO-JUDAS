import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuarios } from '../models/Usuarios';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private url: string = "http://localhost:8080/api/v1/usuarios"
  usuario: string | null = null;

  constructor(private http: HttpClient) {
  }

  getListaUsuarios(): Observable<Usuarios[]> {
    console.log("LLEGA AL GET LISTA");
    return this.http.get<Usuarios[]>(this.url);
  }

  addUsuario(usuario: Usuarios): Observable<Object> {
    console.log("USUARIO PARA AGREGAR: ", usuario);
    return this.http.post(this.url, usuario);
  }

  iniciarSesion(user: string, password: string, tipoSesion: string): Observable<any> {
    console.log("user: " + user + ", password: " + password + " ,tipoSesion: " + tipoSesion);
    const body = new FormData();
    body.append('user', user);
    body.append('password', password);
    body.append('tipoSesion', tipoSesion); 
    return this.http.post<any>(`${this.url}/iniciar-sesion`, body);
  }

  eliminarUsuario(id: number): Observable<any> {
    console.log("ID DE USUARIO PARA ELIMINAR: ", id);
    return this.http.delete(`${this.url}/${id}`);
  }

  actualizarUsuario(id: number, usuario: Usuarios): Observable<any> {
    console.log("ID DE USUARIO PARA ACTUALIZAR: ", id);
    console.log("DATOS DEL USUARIO PARA ACTUALIZAR: ", usuario);
    return this.http.put(`${this.url}/${id}`, usuario);
  }
  obtenerUsuarioPorUsername(username: string): Observable<Usuarios> {
    console.log("OBTENER USUARIO POR USERNAME: ", username);
    return this.http.get<Usuarios>(`${this.url}/${username}`);
  }
}
