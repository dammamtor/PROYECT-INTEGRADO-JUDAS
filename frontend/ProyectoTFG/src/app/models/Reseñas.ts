import { Consumidores } from "./Consumidores";
import { Actividades } from "./Actividades";

export interface Reseñas {
    id: number;
    puntos: number;
    comentario: string;
    fecha: Date;
    consumidor: Consumidores;
    actividad: Actividades;
}