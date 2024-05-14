import { Reseñas } from "./Reseñas";

export interface Consumidores {
    id: number;
    nombre: string;
    apellidos: string;
    nif: string;
    telefono: string;
    correo: string;
    direccion: string;
    reviews: Reseñas[]
}
