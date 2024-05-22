import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ofertantes } from '../models/Ofertantes';
import { SolicitudesActividades } from '../models/SolicitudesActividades';
import { Reseñas } from '../models/Reseñas';

@Injectable({
  providedIn: 'root'
})
export class OfertantesService {
  private url: string = "http://localhost:8080/api/v1/ofertantes"
  constructor(private http: HttpClient) {
  }

  obtenerOfertantePorUsername(user: string): Observable<Ofertantes> {
    console.log("HORA DE OBTENER EL OFERTANTE");
    const url = `${this.url}/usuario/${user}`;
    return this.http.get<Ofertantes>(url);
  }

  obtenerSolicitudesActividadesPorOfertante(idOfertante: number): Observable<SolicitudesActividades[]> {
    const url = `${this.url}/${idOfertante}/solicitudes-actividades`;
    return this.http.get<SolicitudesActividades[]>(url);
  }

  listarOfertantes(): Observable<Ofertantes[]> {
    return this.http.get<Ofertantes[]>(this.url);
  }
  obtenerOfertantePorId(id: number): Observable<Ofertantes> {
    const url = `${this.url}/${id}`;
    return this.http.get<Ofertantes>(url);
  }
  obtenerReseñasPorOfertanteId(ofertanteId: number): Observable<Reseñas[]> {
    const url = `${this.url}/${ofertanteId}/opinion-actividades`;
    return this.http.get<Reseñas[]>(url);
  }
  aceptarSolicitudPorId(ofertanteId: number, idSolicitud: number): Observable<{ aceptado: boolean }> {
    const url = `${this.url}/${ofertanteId}/solicitudes-actividades/${idSolicitud}`;
    return this.http.put<{ aceptado: boolean }>(url, {});
  }
}
