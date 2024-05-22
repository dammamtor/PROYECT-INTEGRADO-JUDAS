import { Actividades } from "./Actividades";
import { Consumidores } from "./Consumidores";

export interface SolicitudesActividades {
    id: number;
    estado: string;
    fec_solicitud: Date;
    actividad: Actividades;
    consumidor: Consumidores
}
