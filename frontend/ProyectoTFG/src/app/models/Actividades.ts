import { Reseñas } from "./Reseñas";
import { TipoActividad } from "./TipoActividad";

export interface Actividades {
    id: number;
    nombre: string;
    descripcion: string;
    fec_inicio: Date;
    fec_final: Date;
    precio: string;
    materiales?: string; // El signo de interrogación indica que el campo es opcional
    tipoActividad: TipoActividad;
    reseñas: Reseñas
}