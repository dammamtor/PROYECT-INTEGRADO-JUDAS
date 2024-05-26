import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Consumidores } from '../models/Consumidores';
import { Observable } from 'rxjs';
import { SolicitudesActividades } from '../models/SolicitudesActividades';
import { Reseñas } from '../models/Reseñas';
import { ReviewPelado } from '../models/ReviewPelado';

@Injectable({
  providedIn: 'root'
})
export class ConsumidoresService {
  private url: string = "http://localhost:8080/api/v1/consumidores"
  constructor(private http: HttpClient) {
  }

  obtenerConsumidorPorUsername(user: string): Observable<Consumidores> {
    console.log("HORA DE OBTENER EL CONSUMIDOR");
    const url = `${this.url}/usuario/${user}`;
    return this.http.get<Consumidores>(url);
  }
  listarConsumidores(): Observable<Consumidores[]> {
    return this.http.get<Consumidores[]>(this.url);
  }
  obtenerConsumidorPorId(id: number): Observable<Consumidores> {
    const url = `${this.url}/${id}`;
    return this.http.get<Consumidores>(url);
  }
  enviarSolicitudActividad(idConsumidor: number, idActividad: number): Observable<SolicitudesActividades> {
    const url = `${this.url}/${idConsumidor}/solicitudes-actividades/${idActividad}`;
    return this.http.post<SolicitudesActividades>(url, null); 
  }
  obtenerSolicitudesActividadesPorConsumidor(idConsumidor: number): Observable<SolicitudesActividades[]> {
    const url = `${this.url}/${idConsumidor}/solicitudes-actividades`;
    return this.http.get<SolicitudesActividades[]>(url);
  }
  obtenerReseñaPorIDconsumidor(idConsumidor: number): Observable<Reseñas[]> {
    const url = `${this.url}/${idConsumidor}/opinion-actividades`;
    return this.http.get<Reseñas[]>(url);
  }
  borrarReseña(idReseña: number): Observable<{ eliminar: boolean }> {
    const url = `http://localhost:8080/api/v1/reseñas/${idReseña}`;
    return this.http.delete<{ eliminar: boolean }>(url);
  }
  publicarReview(idConsumidor: number, idActividad: number, review: ReviewPelado): Observable<ReviewPelado> {
    const url = `${this.url}/${idConsumidor}/opinion-actividades/${idActividad}`;
    return this.http.post<ReviewPelado>(url, review);
  }
}
