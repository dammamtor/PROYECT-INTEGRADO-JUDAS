import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Consumidores } from '../models/Consumidores';
import { Observable } from 'rxjs';

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
}
