import { Actividades } from "./Actividades";
import { Usuarios } from "./Usuarios";

export interface Ofertantes {
    id: number;
    nombre: string;
    apellidos: string;
    nif: string;
    telefono: string;
    correo: string;
    direccion: string;
    actividades?: Actividades[]
    usuario?: Usuarios
}