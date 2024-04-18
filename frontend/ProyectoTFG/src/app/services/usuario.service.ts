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
}
