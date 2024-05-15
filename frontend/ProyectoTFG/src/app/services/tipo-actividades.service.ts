import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TipoActividad } from '../models/TipoActividad';

@Injectable({
  providedIn: 'root'
})
export class TipoActividadesService {
  private url: string = "http://localhost:8080/api/v1/tipos"
  constructor(private http: HttpClient) {
  }

  listarTipoActividades(): Observable<TipoActividad[]> {
    return this.http.get<TipoActividad[]>(this.url);
  }

  obtenerTipoPorId(id: number): Observable<TipoActividad> {
    const url = `${this.url}/${id}`;
    return this.http.get<TipoActividad>(url);
  }

}
