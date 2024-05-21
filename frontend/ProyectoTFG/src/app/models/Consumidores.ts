import { Reseñas } from "./Reseñas";
import { Usuarios } from "./Usuarios";

export interface Consumidores {
    id: number;
    nombre: string;
    apellidos: string;
    nif: string;
    telefono: string;
    correo: string;
    direccion: string;
    reviews: Reseñas[];
    usuario?: Usuarios
}
