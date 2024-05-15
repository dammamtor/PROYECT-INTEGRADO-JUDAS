import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Actividades } from '../models/Actividades';

@Injectable({
  providedIn: 'root'
})
export class ActividadesService {
  private url: string = "http://localhost:8080/api/v1/actividades"
  constructor(private http: HttpClient) {
  }

  // Método para obtener todas las actividades
  listarActividades(): Observable<Actividades[]> {
    return this.http.get<Actividades[]>(`${this.url}`);
  }

  // Método para obtener actividades por tipo
  listarActividadesPorTipo(tipo: string): Observable<Actividades[]> {
    return this.http.get<Actividades[]>(`${this.url}/${tipo}`);
  }

  // Método para buscar actividades por nombre
  buscarActividadesPorNombre(busqueda: string): Observable<Actividades[]> {
    return this.http.get<Actividades[]>(`${this.url}/buscar/${busqueda}`);
  }

  // Método para eliminar una actividad por su ID
  eliminarActividad(id: number): Observable<{ eliminar: boolean }> {
    return this.http.delete<{ eliminar: boolean }>(`${this.url}/${id}`);
  }
}
