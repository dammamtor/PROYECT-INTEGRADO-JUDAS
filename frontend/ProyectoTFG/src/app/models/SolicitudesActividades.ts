import { Actividades } from "./Actividades";

export interface SolicitudesActividades {
    id: number;
    estado: string;
    fec_solicitud: Date;
    actividad: Actividades
}
